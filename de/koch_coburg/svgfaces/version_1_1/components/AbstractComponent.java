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

import java.beans.Introspector;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public abstract class AbstractComponent extends UIComponentBase implements
    ClientBehaviorHolder
{

  private String tagName;

  @Override
  public String getFamily()
  {
    return "svgfaces/1.1";
  }

  @Override
  public void encodeBegin(FacesContext context) throws IOException
  {
    ResponseWriter responseWriter = context.getResponseWriter();
    responseWriter.startElement(this.tagName, null);
    writeAttributes(context);
  }

  @Override
  public void encodeEnd(FacesContext context) throws IOException
  {
    ResponseWriter responseWriter = context.getResponseWriter();
    responseWriter.endElement(this.tagName);
  }

  @Override
  public void decode(FacesContext context)
  {
    Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
    if (behaviors.isEmpty())
    {
      return;
    }

    ExternalContext external = context.getExternalContext();
    Map<String, String> params = external.getRequestParameterMap();
    String behaviorEvent = params.get("javax.faces.behavior.event");

    if (behaviorEvent != null)
    {
      List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

      if (behaviors.size() > 0)
      {
        String behaviorSource = params.get("javax.faces.source");
        String clientId = getClientId(context);
        if (behaviorSource != null && behaviorSource.equals(clientId))
        {
          for (ClientBehavior behavior : behaviorsForEvent)
          {
            behavior.decode(context, this);
          }
        }
      }
    }
  }

  protected void writeEventAttribute(FacesContext context, String eventName)
      throws IOException
  {
    ResponseWriter responseWriter = context.getResponseWriter();
    ClientBehaviorContext behaviorContext = ClientBehaviorContext
        .createClientBehaviorContext(context, this, eventName,
            getClientId(context), null);
    Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
    if (behaviors.containsKey(eventName))
    {
      String eventScript = behaviors.get(eventName).get(0)
          .getScript(behaviorContext);
      // ACHTUNG: Der Parameter event muss in Anführungszeichen gesetzt werden,
      // da es sonst Namenskonflikte im Browser gibt!!!
      // Über die Methode getScript wird event nicht in Anführungszeichen
      // gesetzt, weshalb dies nachträglich geschehen muss!!!
      eventScript = eventScript.replaceAll(",event,", ",\'event\',") + ";";
      responseWriter.writeAttribute("on" + eventName, eventScript, null);
    }
  }

  protected void writeXLinkAttribute(FacesContext context, String attributeName)
      throws IOException
  {
    if (this.getAttributes().get(attributeName) != null)
    {
      writeAttribute(context, "xlink:"
          + attributeName.substring(4).toLowerCase(),
          this.getAttributes().get(attributeName));
    }
  }

  protected void writeAttribute(FacesContext context, String attributeName)
      throws IOException
  {
    if (this.getAttributes().get(attributeName) != null)
    {
      writeAttribute(context, attributeName,
          this.getAttributes().get(attributeName));
    }
  }

  protected void writeAttribute(FacesContext context, String attributeName,
      Object attributeValue) throws IOException
  {
    ResponseWriter responseWriter = context.getResponseWriter();
    responseWriter.writeAttribute(attributeName, attributeValue, null);
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeXLinkAttributes(FacesContext context) throws IOException
  {
    writeXLinkAttribute(context, "linkHref");
    writeXLinkAttribute(context, "linkType");
    writeXLinkAttribute(context, "linkRole");
    writeXLinkAttribute(context, "linkArcrole");
    writeXLinkAttribute(context, "linkTitle");
    writeXLinkAttribute(context, "linkShow");
    writeXLinkAttribute(context, "linkActuate");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeAnimationAttributeTargetAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "attributeType");
    writeAttribute(context, "attributeName");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeAnimationTimingAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "begin");
    writeAttribute(context, "dur");
    writeAttribute(context, "end");
    writeAttribute(context, "min");
    writeAttribute(context, "max");
    writeAttribute(context, "restart");
    writeAttribute(context, "repeatCount");
    writeAttribute(context, "repeatDur");
    writeAttribute(context, "fill");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeAnimationValueAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "calcMode");
    writeAttribute(context, "values");
    writeAttribute(context, "keyTimes");
    writeAttribute(context, "keySplines");
    writeAttribute(context, "from");
    writeAttribute(context, "to");
    writeAttribute(context, "by");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeAnimationAdditionAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "additive");
    writeAttribute(context, "accumulate");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeAnimationEventAttributes(FacesContext context)
      throws IOException
  {
    writeEventAttribute(context, "begin");
    writeEventAttribute(context, "end");
    writeEventAttribute(context, "load");
    writeEventAttribute(context, "repeat");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeDocumentEventAttributes(FacesContext context)
      throws IOException
  {
    writeEventAttribute(context, "abort");
    writeEventAttribute(context, "error");
    writeEventAttribute(context, "resize");
    writeEventAttribute(context, "scroll");
    writeEventAttribute(context, "unload");
    writeEventAttribute(context, "zoom");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeGraphicalEventAttributes(FacesContext context)
      throws IOException
  {
    writeEventAttribute(context, "activate");
    writeEventAttribute(context, "click");
    writeEventAttribute(context, "focusin");
    writeEventAttribute(context, "focusout");
    writeEventAttribute(context, "load");
    writeEventAttribute(context, "mousedown");
    writeEventAttribute(context, "mousemove");
    writeEventAttribute(context, "mouseout");
    writeEventAttribute(context, "mouseover");
    writeEventAttribute(context, "mouseup");
  }

  protected void writeTransferFunctionElementAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "type");
    writeAttribute(context, "tableValues");
    writeAttribute(context, "slope");
    writeAttribute(context, "intercept");
    writeAttribute(context, "amplitude");
    writeAttribute(context, "exponent");
    writeAttribute(context, "offset");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeFilterPrimitiveAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "x");
    writeAttribute(context, "y");
    writeAttribute(context, "width");
    writeAttribute(context, "height");
    writeAttribute(context, "result");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writePresentationAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "alignment-baseline");
    writeAttribute(context, "baseline-shift");
    writeAttribute(context, "clip");
    writeAttribute(context, "clip-path");
    writeAttribute(context, "clip-rule");
    writeAttribute(context, "color");
    writeAttribute(context, "color-interpolation");
    writeAttribute(context, "color-interpolation-filters");
    writeAttribute(context, "color-profile");
    writeAttribute(context, "color-rendering");
    writeAttribute(context, "cursor");
    writeAttribute(context, "direction");
    writeAttribute(context, "display");
    writeAttribute(context, "dominant-baseline");
    writeAttribute(context, "enable-background");
    writeAttribute(context, "fill");
    writeAttribute(context, "fill-opacity");
    writeAttribute(context, "fill-rule");
    writeAttribute(context, "filter");
    writeAttribute(context, "flood-color");
    writeAttribute(context, "flood-opacity");
    writeAttribute(context, "font-family");
    writeAttribute(context, "font-size");
    writeAttribute(context, "font-size-adjust");
    writeAttribute(context, "font-stretch");
    writeAttribute(context, "font-style");
    writeAttribute(context, "font-variant");
    writeAttribute(context, "font-weight");
    writeAttribute(context, "glyph-orientation-horizontal");
    writeAttribute(context, "glyph-orientation-vertical");
    writeAttribute(context, "image-rendering");
    writeAttribute(context, "kerning");
    writeAttribute(context, "letter-spacing");
    writeAttribute(context, "lighting-color");
    writeAttribute(context, "marker-end");
    writeAttribute(context, "marker-mid");
    writeAttribute(context, "marker-start");
    writeAttribute(context, "mask");
    writeAttribute(context, "opacity");
    writeAttribute(context, "overflow");
    writeAttribute(context, "pointer-events");
    writeAttribute(context, "shape-rendering");
    writeAttribute(context, "stop-color");
    writeAttribute(context, "stop-opacity");
    writeAttribute(context, "stroke");
    writeAttribute(context, "stroke-dasharray");
    writeAttribute(context, "stroke-dashoffset");
    writeAttribute(context, "stroke-linecap");
    writeAttribute(context, "stroke-linejoin");
    writeAttribute(context, "stroke-miterlimit");
    writeAttribute(context, "stroke-opacity");
    writeAttribute(context, "stroke-width");
    writeAttribute(context, "text-anchor");
    writeAttribute(context, "text-decoration");
    writeAttribute(context, "text-rendering");
    writeAttribute(context, "unicode-bidi");
    writeAttribute(context, "visibility");
    writeAttribute(context, "word-spacing");
    writeAttribute(context, "writing-mode");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeConditionalProcessingAttributes(FacesContext context)
      throws IOException
  {
    writeAttribute(context, "requiredExtensions");
    writeAttribute(context, "requiredFeatures");
    writeAttribute(context, "systemLanguage");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeCoreAttributes(FacesContext context) throws IOException
  {
    writeAttribute(context, "id", getClientId(context));
    writeAttribute(context, "xml:base");
    writeAttribute(context, "xml:lang");
    writeAttribute(context, "xml:space");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected void writeStyleAttributes(FacesContext context) throws IOException
  {
    if (this.getAttributes().get("styleClass") != null)
    {
      writeAttribute(context, "class", this.getAttributes().get("styleClass"));
    }
    writeAttribute(context, "style");
  }

  /**
   * 
   * @param context
   * @throws IOException
   */
  protected abstract void writeAttributes(FacesContext context)
      throws IOException;

  /**
   * 
   */
  protected AbstractComponent()
  {
    String tagName = Introspector.decapitalize(this.getClass().getSimpleName());
    tagName = tagName.substring(0, tagName.length() - 9);
    this.tagName = tagName;
  }

  /**
   * 
   * @return
   */
  protected Collection<String> getDocumentEventNames()
  {
    return Arrays
        .asList("unload", "abort", "error", "resize", "scroll", "zoom");
  }

  /**
   * 
   * @return
   */
  protected Collection<String> getGraphicalEventNames()
  {
    return Arrays.asList("focusin", "focusout", "activate", "click",
        "mousedown", "mouseup", "mouseover", "mousemove", "mouseout", "load");
  }

  /**
   * 
   * @return
   */
  protected Collection<String> getAnimationEventNames()
  {
    return Arrays.asList("begin", "end", "repeat", "load");
  }

  @Override
  public Collection<String> getEventNames()
  {
    return getGraphicalEventNames();
  }

  @Override
  public String getDefaultEventName()
  {
    return "click";
  }

}
