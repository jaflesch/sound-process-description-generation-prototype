
package org.omg.spec.bpmn._20100524.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.omg.spec.bpmn._20100524.di.BPMNDiagram;


/**
 * <p>Java class for tDefinitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDefinitions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}import" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}extension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}rootElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/DI}BPMNDiagram" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.omg.org/spec/BPMN/20100524/MODEL}relationship" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="targetNamespace" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="expressionLanguage" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.w3.org/1999/XPath" />
 *       &lt;attribute name="typeLanguage" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.w3.org/2001/XMLSchema" />
 *       &lt;attribute name="exporter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="exporterVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDefinitions", propOrder = {
    "_import",
    "extension",
    "rootElement",
    "bpmnDiagram",
    "relationship"
})
public class TDefinitions {

    @XmlElement(name = "import")
    protected List<TImport> _import;
    protected List<TExtension> extension;
    @XmlElementRef(name = "rootElement", namespace = "http://www.omg.org/spec/BPMN/20100524/MODEL", type = JAXBElement.class)
    protected List<JAXBElement<? extends TRootElement>> rootElement;
    @XmlElement(name = "BPMNDiagram", namespace = "http://www.omg.org/spec/BPMN/20100524/DI")
    protected List<BPMNDiagram> bpmnDiagram;
    protected List<TRelationship> relationship;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "targetNamespace", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String targetNamespace;
    @XmlAttribute(name = "expressionLanguage")
    @XmlSchemaType(name = "anyURI")
    protected String expressionLanguage;
    @XmlAttribute(name = "typeLanguage")
    @XmlSchemaType(name = "anyURI")
    protected String typeLanguage;
    @XmlAttribute(name = "exporter")
    protected String exporter;
    @XmlAttribute(name = "exporterVersion")
    protected String exporterVersion;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the import property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the import property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TImport }
     * 
     * 
     */
    public List<TImport> getImport() {
        if (_import == null) {
            _import = new ArrayList<TImport>();
        }
        return this._import;
    }

    /**
     * Gets the value of the extension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TExtension }
     * 
     * 
     */
    public List<TExtension> getExtension() {
        if (extension == null) {
            extension = new ArrayList<TExtension>();
        }
        return this.extension;
    }

    /**
     * Gets the value of the rootElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rootElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRootElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TGlobalManualTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TPartnerRole }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalConversation }{@code >}
     * {@link JAXBElement }{@code <}{@link TLinkEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TDataStore }{@code >}
     * {@link JAXBElement }{@code <}{@link TError }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionalEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TCompensateEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TErrorEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TPartnerEntity }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalScriptTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TChoreography }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalUserTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TMessage }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalChoreographyTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TSignal }{@code >}
     * {@link JAXBElement }{@code <}{@link TInterface }{@code >}
     * {@link JAXBElement }{@code <}{@link TResource }{@code >}
     * {@link JAXBElement }{@code <}{@link TCancelEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TTerminateEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TEscalation }{@code >}
     * {@link JAXBElement }{@code <}{@link TEscalationEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TTimerEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TItemDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TCollaboration }{@code >}
     * {@link JAXBElement }{@code <}{@link TRootElement }{@code >}
     * {@link JAXBElement }{@code <}{@link TCorrelationProperty }{@code >}
     * {@link JAXBElement }{@code <}{@link TProcess }{@code >}
     * {@link JAXBElement }{@code <}{@link TMessageEventDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link TEndPoint }{@code >}
     * {@link JAXBElement }{@code <}{@link TCategory }{@code >}
     * {@link JAXBElement }{@code <}{@link TGlobalBusinessRuleTask }{@code >}
     * {@link JAXBElement }{@code <}{@link TSignalEventDefinition }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends TRootElement>> getRootElement() {
        if (rootElement == null) {
            rootElement = new ArrayList<JAXBElement<? extends TRootElement>>();
        }
        return this.rootElement;
    }

    /**
     * Gets the value of the bpmnDiagram property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bpmnDiagram property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBPMNDiagram().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BPMNDiagram }
     * 
     * 
     */
    public List<BPMNDiagram> getBPMNDiagram() {
        if (bpmnDiagram == null) {
            bpmnDiagram = new ArrayList<BPMNDiagram>();
        }
        return this.bpmnDiagram;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationship property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationship().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRelationship }
     * 
     * 
     */
    public List<TRelationship> getRelationship() {
        if (relationship == null) {
            relationship = new ArrayList<TRelationship>();
        }
        return this.relationship;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the expressionLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpressionLanguage() {
        if (expressionLanguage == null) {
            return "http://www.w3.org/1999/XPath";
        } else {
            return expressionLanguage;
        }
    }

    /**
     * Sets the value of the expressionLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpressionLanguage(String value) {
        this.expressionLanguage = value;
    }

    /**
     * Gets the value of the typeLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeLanguage() {
        if (typeLanguage == null) {
            return "http://www.w3.org/2001/XMLSchema";
        } else {
            return typeLanguage;
        }
    }

    /**
     * Sets the value of the typeLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeLanguage(String value) {
        this.typeLanguage = value;
    }

    /**
     * Gets the value of the exporter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExporter() {
        return exporter;
    }

    /**
     * Sets the value of the exporter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExporter(String value) {
        this.exporter = value;
    }

    /**
     * Gets the value of the exporterVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExporterVersion() {
        return exporterVersion;
    }

    /**
     * Sets the value of the exporterVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExporterVersion(String value) {
        this.exporterVersion = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
