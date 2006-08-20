/*
   Copyright 2005 Simon Mieth

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.kabeja.dxf;

import java.util.Map;

import org.kabeja.dxf.helpers.DXFTextParser;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.dxf.helpers.TextDocument;
import org.kabeja.svg.SVGConstants;
import org.kabeja.svg.SVGContext;
import org.kabeja.svg.SVGUtils;
import org.kabeja.tools.FontManager;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class DXFText extends DXFEntity {
    public static final double DEFAULT_FONT_SIZE = 8;
    public static final int VALIGN_BASELINE = 0;
    public static final int VALIGN_BOTTOM = 1;
    public static final int VALIGN_CENTER = 2;
    public static final int VALIGN_TOP = 3;
    public static final int ALIGN_MIDDLE = 4;
    protected double rotation = 0.0;
    protected double height = 0.0;
    protected double scale_x = 1.0;
    protected double oblique_angle = 0.0;
    protected double align_x = 0.0;
    protected double align_y = 0.0;
    protected double align_z = 0.0;

    // the horizontal align
    protected int align = 0;

    // the vertical align
    protected int valign = 0;
    protected String text = "";
    protected String textStyle = "";
    protected Point p;
    protected Point align_p1;
    protected Point align_p2;
    protected boolean upsideDown = false;
    protected boolean backward = false;
    protected boolean alignmentPointSet = false;
    protected boolean top = false;
    protected boolean bottom = false;
    protected boolean vertical_center = false;
    protected TextDocument textDoc = new TextDocument();

    /**
     *
     */
    public DXFText() {
        this.p = new Point();
        this.align_p1 = new Point();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.dxf.DXFEntity#setDXFDocument(org.dxf2svg.dxf.DXFDocument)
     */
    public void setDXFDocument(DXFDocument doc) {
        super.setDXFDocument(doc);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.dxf.DXFEntity#toSAX(org.xml.sax.ContentHandler)
     */
    public void toSAX(ContentHandler handler, Map svgContext)
        throws SAXException {
        AttributesImpl attr = new AttributesImpl();
        Point alignmentPoint = calculateAlignmentPoint();

        double height = getHeight();

        if (height == 0.0) {
            height = ((Bounds) svgContext.get(SVGContext.DRAFT_BOUNDS)).getHeight() * 0.005;
        }

        SVGUtils.addAttribute(attr, SVGConstants.SVG_ATTRIBUTE_FONT_SIZE,
            SVGUtils.formatNumberAttribute(height));

        // if there is a SVG font, we will use it
        if (doc.getDXFStyle(this.textStyle) != null) {
            DXFStyle style = doc.getDXFStyle(this.textStyle);
            FontManager manager = FontManager.getInstance();
            String fontID = null;

            if (manager.hasFontDescription(style.getBigFontFile())) {
                fontID = style.getBigFontFile();
            } else if (manager.hasFontDescription(style.getFontFile())) {
                fontID = style.getFontFile();
            }

            if (fontID != null) {
                fontID = fontID.toLowerCase();

                if (fontID.endsWith(".shx")) {
                    fontID = fontID.substring(0, fontID.indexOf(".shx"));
                }

                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_FONT_FAMILY, fontID);
            }
        } else {
            // Do we need to set a default?
            // SVGUtils.addAttribute(attr,SVGConstants.SVG_ATTRIBUTE_FONT_FAMILY,"simplex");
        }

        if (!isBackward()) {
            SVGUtils.addAttribute(attr, "writing-mode", "lr-tb");
        } else {
            SVGUtils.addAttribute(attr, "writing-mode", "rl-tb");
        }

        // set the alignment
        if (!isUpsideDown()) {
            switch (align) {
            case 0:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "start");

                break;

            case 1:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "middle");

                break;

            case 2:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "end");

                break;

            case 3:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "end");

                break;

            case 4:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "middle");

                break;

            case 5:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "end");

                break;

            default:
                SVGUtils.addAttribute(attr,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_ANCHOR, "start");

                break;
            }
        }

        // in order to get the right text-view
        StringBuffer transform = new StringBuffer();


        if (!isUpsideDown()) {
            transform.append("matrix(1 0 0 -1 0 ");
            transform.append(2 * alignmentPoint.getY());
            transform.append(')');
        }


        // rotation
        if (this.rotation != 0.0) {
            transform.append(" rotate(");
            transform.append((-1 * this.rotation));
            transform.append(' ');
            transform.append(alignmentPoint.getX());
            transform.append(' ');
            transform.append(alignmentPoint.getY());
            transform.append(' ');
            transform.append(')');

        }


        if (this.oblique_angle != 0.0) {
            transform.append(" skewX(");
            transform.append((-1*this.oblique_angle));
            transform.append(" )");

            transform.append(" translate( ");
            transform.append(alignmentPoint.getY()*Math.tan(Math.toRadians(1*this.oblique_angle)));
            transform.append(')');
         }



        SVGUtils.addAttribute(attr, "transform", transform.toString());



        SVGUtils.addAttribute(attr, "x", "" + alignmentPoint.getX());
        SVGUtils.addAttribute(attr, "y",
            "" +alignmentPoint.getY());

        SVGUtils.addAttribute(attr, "fill", "currentColor");
        super.setCommonAttributes(attr);
        SVGUtils.startElement(handler, SVGConstants.SVG_TEXT, attr);
        SVGUtils.textDocumentToSAX(handler, getTextDocument());
        SVGUtils.endElement(handler, SVGConstants.SVG_TEXT);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.dxf.DXFEntity#updateViewPort()
     */
    public Bounds getBounds() {
        double tl = getTextDocument().getText().length();

        if (tl > 0) {
            // TODO calculate the real values
            Point p = calculateAlignmentPoint();
            bounds.addToBounds(p);

            double h = getHeight();

            double w = tl * h * 0.75;

            if (!isUpsideDown()) {
                switch (this.align) {
                case 0:
                    bounds.addToBounds(p.getX() + w, p.getY() - h);

                    break;

                case 1:
                    bounds.addToBounds(p.getX() + (w / 2), p.getY() - h);
                    bounds.addToBounds(p.getX() - (w / 2), p.getY() - h);

                    break;

                case 2:
                    bounds.addToBounds(p.getX() - w, p.getY() - h);

                    break;

                case 3:
                    bounds.addToBounds(p.getX() - w, p.getY() - h);

                    break;

                case 4:
                    bounds.addToBounds(p.getX() + (w / 2), p.getY() - (h / 2));
                    bounds.addToBounds(p.getX() - (w / 2), p.getY() + (h / 2));

                    break;

                case 5:
                    bounds.addToBounds(p.getX() + (w / 2), p.getY() + h);
                    bounds.addToBounds(p.getX() - (w / 2), p.getY() + h);

                    break;
                }
            } else {
                bounds.addToBounds(p.getX(), p.getY() - (h * tl));
            }
        } else {
            bounds.setValid(false);
        }

        return bounds;
    }

    /**
     * @return Returns the align.
     */
    public int getAlign() {
        return align;
    }

    /**
     * @param align
     *            The align to set.
     */
    public void setAlign(int align) {
        this.align = align;
    }

    /**
     * @return Returns the align_x.
     */
    public double getAlignX() {
        return align_p1.getX();
    }

    /**
     * @param align_x
     *            The align_x to set.
     */
    public void setAlignX(double align_x) {
        align_p1.setX(align_x);
    }

    /**
     * @return Returns the align_y.
     */
    public double getAlignY() {
        return align_p1.getY();
    }

    /**
     * @param align_y
     *            The align_y to set.
     */
    public void setAlignY(double align_y) {
        align_p1.setY(align_y);
    }

    /**
     * @return Returns the align_z.
     */
    public double getAlignZ() {
        return align_p1.getZ();
    }

    /**
     * @param align_z
     *            The align_z to set.
     */
    public void setAlignZ(double align_z) {
        align_p1.setZ(align_z);
    }

    /**
     * @return Returns the height.
     */
    public double getHeight() {
        if (height != 0.0) {
            return height;
        } else if (doc.getDXFStyle(this.textStyle) != null) {
            return doc.getDXFStyle(this.textStyle).getTextHeight();
        } else {
            return 0.0;
        }
    }

    /**
     * @param height
     *            The height to set.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return Returns the oblique_angle.
     */
    public double getObliqueAngle() {
        return oblique_angle;
    }

    /**
     * @param oblique_angle
     *            The oblique_angle to set.
     */
    public void setObliqueAngle(double oblique_angle) {
        this.oblique_angle = oblique_angle;
    }

    /**
     * @return Returns the rotation.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * @param rotation
     *            The rotation to set.
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * @return Returns the scale_x.
     */
    public double getScaleX() {
        return scale_x;
    }

    /**
     * @param scale_x
     *            The scale_x to set.
     */
    public void setScaleX(double scale_x) {
        this.scale_x = scale_x;
    }

    /**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            The text to set.
     */
    public void setText(String text) {
        this.text = text;
        this.textDoc = DXFTextParser.parseDXFText(this);
    }

    /**
     * @return Returns the textStyle.
     */
    public String getTextStyle() {
        return textStyle;
    }

    /**
     * @param textStyle
     *            The textStyle to set.
     */
    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    /**
     * @return Returns the valign.
     */
    public int getValign() {
        return valign;
    }

    /**
     * @param valign
     *            The valign to set.
     */
    public void setValign(int valign) {
        this.valign = valign;
    }

    public void setX(double x) {
        p.setX(x);
    }

    public void setY(double y) {
        p.setY(y);
    }

    public void setZ(double z) {
        p.setZ(z);
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    public void setUpsideDown(boolean upsideDown) {
        this.upsideDown = upsideDown;
    }

    /**
     * @deprecated
     * @param str
     * @param handler
     * @throws SAXException
     */
    protected void processText(String str, ContentHandler handler)
        throws SAXException {
        boolean overline = false;
        boolean underline = false;
        boolean asciicontrol = false;
        StringBuffer buf = new StringBuffer();

        int marker = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '%') {
                marker++;
            } else if (c == '^') {
                asciicontrol = true;
            } else if (asciicontrol) {
                // ASCII-control sign map
                if (Character.isWhitespace(c)) {
                    buf.append('^');
                } else {
                    // filtering acsii controls here
                }

                asciicontrol = false;
            } else {
                if (marker == 0) {
                    buf.append(c);
                } else if (marker == 1) {
                    // single control charakter means itself
                    buf.append('%');
                    buf.append(c);
                    marker = 0;
                } else if (marker == 2) {
                    switch (c) {
                    case 'd':
                        buf.append("\uC2B1");

                        break;

                    case 'c':
                        buf.append("\u2205");

                        break;

                    case 'p':
                        buf.append("\uC2B1");

                        break;

                    case 'o':
                        emitTSpanElement(handler, buf.toString(), underline,
                            overline);
                        buf.delete(0, buf.length());
                        overline = !overline;

                        break;

                    case 'u':
                        emitTSpanElement(handler, buf.toString(), underline,
                            overline);
                        buf.delete(0, buf.length());
                        underline = !underline;

                        break;

                    default:

                        if (Character.isDigit(c) && ((i + 2) < str.length())) {
                            String code = "" + c + str.charAt(i + 1) +
                                str.charAt(i + 2);

                            try {
                                c = (char) Integer.parseInt(code);
                                buf.append(c);
                                i += 2;
                            } catch (NumberFormatException e) {
                                System.out.println("Text:" + str +
                                    " Character:" + c);

                                // TODO sometimes there are only one
                                // digit
                                buf.append('_');
                            }
                        }
                    }

                    marker = 0;
                } else if (marker == 3) {
                    buf.append('%');
                    marker = 0;
                }
            }
        }

        // something left over?
        if (buf.length() > 0) {
            emitTSpanElement(handler, buf.toString(), underline, overline);
        }
    }

    /**
     * @deprecated
     * @param handler
     * @param text
     * @param underline
     * @param overline
     * @throws SAXException
     */
    protected void emitTSpanElement(ContentHandler handler, String text,
        boolean underline, boolean overline) throws SAXException {
        if (text.length() > 0) {
            AttributesImpl atts = new AttributesImpl();
            String decoration = "";

            if (underline) {
                decoration += "underline ";
            }

            if (overline) {
                decoration += "overline ";
            }

            if (decoration.length() > 0) {
                SVGUtils.addAttribute(atts,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_DECORATION, decoration);
            }

            if (top) {
                SVGUtils.addAttribute(atts,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_BASELINE_SHIFT, "-100%");
            } else if (bottom) {
                SVGUtils.addAttribute(atts,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_BASELINE_SHIFT, "sub");
            } else if (vertical_center) {
                SVGUtils.addAttribute(atts,
                    SVGConstants.SVG_ATTRIBUTE_TEXT_BASELINE_SHIFT, "-40%");
            }

            SVGUtils.startElement(handler, SVGConstants.SVG_TSPAN, atts);
            SVGUtils.characters(handler, text);
            SVGUtils.endElement(handler, SVGConstants.SVG_TSPAN);
        }
    }



    public String getType() {
        return DXFConstants.ENTITY_TYPE_TEXT;
    }

    /**
     * @return Returns the alignmentPointSet.
     */
    public boolean isAlignmentPointSet() {
        return alignmentPointSet;
    }

    /**
     * @param alignmentPointSet
     *            The alignmentPointSet to set.
     */
    public void setAlignmentPoint(boolean alignmentPoint) {
        this.alignmentPointSet = alignmentPoint;
    }

    public TextDocument getTextDocument() {
        return this.textDoc;
    }

    public Point getInsertPoint() {
        return p;
    }

    public Point getAlignmentPoint() {
        return align_p1;
    }

    public Point calculateAlignmentPoint() {
        Point alignmentPoint = new Point(p.getX(), p.getY(), p.getZ());

        if (!isUpsideDown()) {
            switch (align) {
            case 1:

                if (alignmentPointSet) {
                    alignmentPoint.setX(align_p1.getX());
                }

                break;

            case 2:

                if (alignmentPointSet) {
                    alignmentPoint.setX(align_p1.getX());
                }

                break;

            case 3:

                if (alignmentPointSet) {
                    alignmentPoint.setX(align_p1.getX());
                }

                break;

            case 4:

                if (alignmentPointSet) {
                    alignmentPoint.setX(align_p1.getX());
                }

                break;

            case 5:

                if (alignmentPointSet) {
                    alignmentPoint.setX(align_p1.getX());
                }

                break;
            }

            if (alignmentPointSet) {
                alignmentPoint.setY(align_p1.getY());
            }
        }

        return alignmentPoint;
    }

    protected boolean isOmitLineType() {
        return true;
    }

	public double getLength() {
		
		return 0;
	}
    
    
}