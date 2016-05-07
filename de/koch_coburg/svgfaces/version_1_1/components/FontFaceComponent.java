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
import java.util.Arrays;
import java.util.Collection;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

@FacesComponent(value = "svgfaces/1.1/fontFaceComponent")
public class FontFaceComponent extends AbstractComponent
{
  @Override
  protected void writeAttributes(FacesContext context) throws IOException
  {
    writeCoreAttributes(context);
    writeAttribute(context, "font-family");
    writeAttribute(context, "font-style");
    writeAttribute(context, "font-variant");
    writeAttribute(context, "font-weight");
    writeAttribute(context, "font-stretch");
    writeAttribute(context, "font-size");
    writeAttribute(context, "unicode-range");
    writeAttribute(context, "units-per-em");
    writeAttribute(context, "panose-1");
    writeAttribute(context, "stemv");
    writeAttribute(context, "stemh");
    writeAttribute(context, "slope");
    writeAttribute(context, "cap-height");
    writeAttribute(context, "x-height");
    writeAttribute(context, "accent-height");
    writeAttribute(context, "ascent");
    writeAttribute(context, "descent");
    writeAttribute(context, "widths");
    writeAttribute(context, "bbox");
    writeAttribute(context, "ideographic");
    writeAttribute(context, "alphabetic");
    writeAttribute(context, "mathematical");
    writeAttribute(context, "hanging");
    writeAttribute(context, "v-ideographic");
    writeAttribute(context, "v-alphabetic");
    writeAttribute(context, "v-mathematical");
    writeAttribute(context, "v-hanging");
    writeAttribute(context, "underline-position");
    writeAttribute(context, "underline-thickness");
    writeAttribute(context, "strikethrough-position");
    writeAttribute(context, "strikethrough-thickness");
    writeAttribute(context, "overline-position");
    writeAttribute(context, "overline-thickness");
  }

  @Override
  public Collection<String> getEventNames()
  {
    return Arrays.asList();
  }
}