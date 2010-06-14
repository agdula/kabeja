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

package org.kabeja;

import java.util.Map;

import org.kabeja.tools.Component;


/**
 * Main application service for starting and stopping the 
 * application.
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public interface Application extends Component{
    public final static String SERVICE = Application.class.getName();

    /**
     * Starts the application.
     * @param properties 
     */
    public void start(Map properties);

    /**
     * Stops the application
     */
    public void stop();
}
