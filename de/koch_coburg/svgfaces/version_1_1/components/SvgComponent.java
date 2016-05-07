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
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "svgfaces/1.1/svgComponent")
public class SvgComponent extends AbstractComponent
{
  protected void writeAttributes(FacesContext context) throws IOException
  {
    writeAttribute(context, "xmlns", "http://www.w3.org/2000/svg");
    writeAttribute(context, "xmlns:xlink", "http://www.w3.org/1999/xlink");
    writeConditionalProcessingAttributes(context);
    writeCoreAttributes(context);
    writeDocumentEventAttributes(context);
    writeGraphicalEventAttributes(context);
    writePresentationAttributes(context);
    writeStyleAttributes(context);
    writeAttribute(context, "externalResourcesRequired");
    writeAttribute(context, "x");
    writeAttribute(context, "y");
    writeAttribute(context, "width");
    writeAttribute(context, "height");
    writeAttribute(context, "viewBox");
    writeAttribute(context, "preserveAspectRatio");
    writeAttribute(context, "zoomAndPan");
    writeAttribute(context, "version", "1.1");
    writeAttribute(context, "baseProfile");
    writeAttribute(context, "contentScriptType");
    writeAttribute(context, "contentStyleType");
  }

  @Override
  public Collection<String> getEventNames()
  {
    ArrayList<String> eventNames = new ArrayList<String>();
    eventNames.addAll(getDocumentEventNames());
    eventNames.addAll(getGraphicalEventNames());
    return eventNames;
  }
}