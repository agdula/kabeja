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
package org.kabeja.dxf.generator.table;

import org.kabeja.DraftDocument;
import org.kabeja.common.LineType;
import org.kabeja.dxf.generator.DXFGenerationContext;
import org.kabeja.dxf.generator.DXFOutput;
import org.kabeja.dxf.generator.DXFTableGenerator;
import org.kabeja.dxf.generator.conf.DXFProfile;
import org.kabeja.io.GenerationException;
import org.kabeja.util.Constants;

public class DXFLineTypeTableHandler implements DXFTableGenerator{

	public String getTableType() {
		return Constants.TABLE_KEY_LTYPE;
	}

	public void output(DraftDocument doc, DXFOutput output, DXFGenerationContext context, DXFProfile type)
			throws GenerationException {
		 output.output(70, doc.getLineTypes().size());
		 
	for(LineType ltype:doc.getLineTypes()){
		
			 
		 }
		
	}

	

}
