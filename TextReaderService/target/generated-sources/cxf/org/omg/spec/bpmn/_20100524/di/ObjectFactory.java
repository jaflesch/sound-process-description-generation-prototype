
package org.omg.spec.bpmn._20100524.di;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.omg.spec.bpmn._20100524.di package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BPMNPlane_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNPlane");
    private final static QName _BPMNLabelStyle_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabelStyle");
    private final static QName _BPMNDiagram_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNDiagram");
    private final static QName _BPMNShape_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNShape");
    private final static QName _BPMNLabel_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabel");
    private final static QName _BPMNEdge_QNAME = new QName("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNEdge");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.omg.spec.bpmn._20100524.di
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BPMNEdge }
     * 
     */
    public BPMNEdge createBPMNEdge() {
        return new BPMNEdge();
    }

    /**
     * Create an instance of {@link BPMNLabel }
     * 
     */
    public BPMNLabel createBPMNLabel() {
        return new BPMNLabel();
    }

    /**
     * Create an instance of {@link BPMNDiagram }
     * 
     */
    public BPMNDiagram createBPMNDiagram() {
        return new BPMNDiagram();
    }

    /**
     * Create an instance of {@link BPMNShape }
     * 
     */
    public BPMNShape createBPMNShape() {
        return new BPMNShape();
    }

    /**
     * Create an instance of {@link BPMNPlane }
     * 
     */
    public BPMNPlane createBPMNPlane() {
        return new BPMNPlane();
    }

    /**
     * Create an instance of {@link BPMNLabelStyle }
     * 
     */
    public BPMNLabelStyle createBPMNLabelStyle() {
        return new BPMNLabelStyle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNPlane }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNPlane")
    public JAXBElement<BPMNPlane> createBPMNPlane(BPMNPlane value) {
        return new JAXBElement<BPMNPlane>(_BPMNPlane_QNAME, BPMNPlane.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNLabelStyle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNLabelStyle")
    public JAXBElement<BPMNLabelStyle> createBPMNLabelStyle(BPMNLabelStyle value) {
        return new JAXBElement<BPMNLabelStyle>(_BPMNLabelStyle_QNAME, BPMNLabelStyle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNDiagram }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNDiagram")
    public JAXBElement<BPMNDiagram> createBPMNDiagram(BPMNDiagram value) {
        return new JAXBElement<BPMNDiagram>(_BPMNDiagram_QNAME, BPMNDiagram.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNShape }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNShape", substitutionHeadNamespace = "http://www.omg.org/spec/DD/20100524/DI", substitutionHeadName = "DiagramElement")
    public JAXBElement<BPMNShape> createBPMNShape(BPMNShape value) {
        return new JAXBElement<BPMNShape>(_BPMNShape_QNAME, BPMNShape.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNLabel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNLabel")
    public JAXBElement<BPMNLabel> createBPMNLabel(BPMNLabel value) {
        return new JAXBElement<BPMNLabel>(_BPMNLabel_QNAME, BPMNLabel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BPMNEdge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/BPMN/20100524/DI", name = "BPMNEdge", substitutionHeadNamespace = "http://www.omg.org/spec/DD/20100524/DI", substitutionHeadName = "DiagramElement")
    public JAXBElement<BPMNEdge> createBPMNEdge(BPMNEdge value) {
        return new JAXBElement<BPMNEdge>(_BPMNEdge_QNAME, BPMNEdge.class, null, value);
    }

}
