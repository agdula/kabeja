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

package org.kabeja.entities.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 * 
 */
public class TextDocument {

	protected List<StyledTextParagraph> paragraphs = new ArrayList<StyledTextParagraph>();

	/**
	 * Return the pure text content.
	 * 
	 * @return the text content
	 */
	public String getText() {

		StringBuffer buf = new StringBuffer();

		for (StyledTextParagraph para : this.paragraphs) {
			buf.append(para.getText());

			if (para.isNewline()) {
				buf.append('\n');
			}
		}

		return buf.toString();
	}

	public void addStyledParagraph(StyledTextParagraph para) {
		this.paragraphs.add(para);
	}

	public List<StyledTextParagraph> getStyledParagraphs() {
		return this.paragraphs;
	}

	public int getParagraphCount() {
		return this.paragraphs.size();
	}

	public StyledTextParagraph getStyleTextParagraph(int i) {
		return (StyledTextParagraph) this.paragraphs.get(i);
	}

	public int getLineCount() {
		int count = 1;
		for (StyledTextParagraph para : this.paragraphs) {

			if (para.isNewline()) {
				count++;
			}
		}

		return count;
	}

	public int getMaximumLineLength() {
		int count = 0;
		int max = 0;
		for (StyledTextParagraph para : this.paragraphs) {
			if (!para.isNewline()) {
				count += para.getLength();
			} else {
				if (count > max) {
					max = count;
				}
				count = para.getLength();
			}
		}
		//last check for single lines
		if (count > max) {
			max = count;
		}
		
		return max;
	}
}
