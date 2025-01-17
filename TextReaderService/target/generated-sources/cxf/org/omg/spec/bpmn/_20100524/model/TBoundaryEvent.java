
package org.omg.spec.bpmn._20100524.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tBoundaryEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tBoundaryEvent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tCatchEvent">
 *       &lt;attribute name="cancelActivity" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="attachedToRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tBoundaryEvent")
public class TBoundaryEvent
    extends TCatchEvent
{

    @XmlAttribute(name = "cancelActivity")
    protected Boolean cancelActivity;
    @XmlAttribute(name = "attachedToRef", required = true)
    protected QName attachedToRef;

    /**
     * Gets the value of the cancelActivity property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCancelActivity() {
        if (cancelActivity == null) {
            return true;
        } else {
            return cancelActivity;
        }
    }

    /**
     * Sets the value of the cancelActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelActivity(Boolean value) {
        this.cancelActivity = value;
    }

    /**
     * Gets the value of the attachedToRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getAttachedToRef() {
        return attachedToRef;
    }

    /**
     * Sets the value of the attachedToRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setAttachedToRef(QName value) {
        this.attachedToRef = value;
    }

}
