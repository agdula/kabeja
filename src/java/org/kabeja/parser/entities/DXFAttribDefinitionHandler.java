/*
   Copyright 2008 Simon Mieth

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
package org.kabeja.parser.entities;

import org.kabeja.dxf.DXFAttribDefinition;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.parser.DXFValue;

public class DXFAttribDefinitionHandler extends DXFAttribHandler {

    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
        case GROUPCODE_ATTRIB_TEXT_LENGTH:
            ((DXFAttribDefinition)this.attrib).setTextFieldLength(value.getIntegerValue());
            break;
        }
     super.parseGroup(groupCode, value);
        
    }

    public String getDXFEntityName() {
        return DXFConstants.ENTITY_TYPE_ATTDEF;
    }

    public void startDXFEntity() {
        this.attrib = new DXFAttribDefinition();
        this.text = this.attrib;
        this.text.setDXFDocument(this.doc);
    }

}
