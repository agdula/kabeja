/*******************************************************************************
 * Copyright 2010 Simon Mieth
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.kabeja.dxf.parser.entities;

import org.kabeja.dxf.parser.DXFValue;
import org.kabeja.entities.Entity;
import org.kabeja.entities.MText;
import org.kabeja.util.Constants;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFMTextHandler extends AbstractEntityHandler {
  
    public static final int TEXT_VALUE_END = 1;
    public static final int TEXT_VALUE = 3;
    public static final int TEXT_HEIGHT = 40;
    public static final int TEXT_REF_WIDTH = 41;
    public static final int TEXT_REF_HEIGHT = 43;
    public static final int TEXT_DRAWING_DIRECTION_FLAG = 72;
    public static final int TEXT_ATTACHMENT_POINT = 71;
    public static final int TEXT_VALIGN = 73;
    public static final int TEXT_ALIGN_X = 11;
    public static final int TEXT_ALIGN_Y = 21;
    public static final int TEXT_ALIGN_Z = 31;
    public static final int TEXT_STYLE = 7;
    public static final int TEXT_OBLIQUEANGLE = 51;
    public static final int TEXT_ROTATION = 50;
    private MText mtext;
    private StringBuffer buf = new StringBuffer();
    private int insert = 0;

    /**
     *
     */
    public DXFMTextHandler() {
        super();
    }


    public void endDXFEntity() {
        mtext.setText(buf.toString());
        buf.delete(0, buf.length());
    }


    public Entity getDXFEntity() {
        return mtext;
    }

    public String getDXFEntityType() {
        return Constants.ENTITY_TYPE_MTEXT;
    }


    public boolean isFollowSequence() {
       
        return false;
    }


    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
        case TEXT_VALUE:

            String part = value.getValue();
            buf.insert(insert, part);
            insert += part.length();

            break;

        case TEXT_VALUE_END:
            buf.insert(insert, value.getValue());

            break;

        case TEXT_ATTACHMENT_POINT:
            mtext.setAttachmentPoint(value.getIntegerValue());

            break;

        case GROUPCODE_START_X:
            mtext.getInsertPoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_START_Y:
            mtext.getInsertPoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_START_Z:
            mtext.getInsertPoint().setZ(value.getDoubleValue());

            break;

        case TEXT_ALIGN_X:
            mtext.getAlignmentPoint().setX(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_ALIGN_Y:
            mtext.getAlignmentPoint().setY(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_ALIGN_Z:
            mtext.getAlignmentPoint().setZ(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_HEIGHT:
            mtext.setHeight(value.getDoubleValue());

            break;

        case TEXT_DRAWING_DIRECTION_FLAG:

            switch (value.getIntegerValue()) {
            case 2:
                mtext.setBackward(true);

                break;

            case 4:
                mtext.setUpsideDown(true);

                break;
            }

            break;

        case TEXT_STYLE:
            mtext.setTextStyle(value.getValue());

            break;

        case TEXT_ROTATION:
            mtext.setRotation(value.getDoubleValue());

            break;

        case TEXT_REF_WIDTH:
            mtext.setReferenceWidth(value.getDoubleValue());

            break;

        case TEXT_REF_HEIGHT:
            mtext.setReferenceHeight(value.getDoubleValue());

            break;

        case TEXT_OBLIQUEANGLE:
            mtext.setObliqueAngle(value.getDoubleValue());
            break;

        default:
            super.parseCommonProperty(groupCode, value, mtext);
            break;
        }
    }


    public void startDXFEntity() {
        mtext = new MText();
        insert = 0;
    }
}
