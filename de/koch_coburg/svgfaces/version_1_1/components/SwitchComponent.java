/*
  Copyright 2016 Michael Koch (http://www.koch-coburg.de)

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
package de.koch_coburg.svgfaces.version_1_1.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "svgfaces/1.1/switchComponent")
public class SwitchComponent extends AbstractComponent
{
  @Override
  protected void writeAttributes(FacesContext context) throws IOException
  {
    writeConditionalProcessingAttributes(context);
    writeCoreAttributes(context);
    writeGraphicalEventAttributes(context);
    writePresentationAttributes(context);
    writeStyleAttributes(context);
    writeAttribute(context, "externalResourcesRequired");
    writeAttribute(context, "transform");
  }
}