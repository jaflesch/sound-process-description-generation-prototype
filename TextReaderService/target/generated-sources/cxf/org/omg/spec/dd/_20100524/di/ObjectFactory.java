
package org.omg.spec.dd._20100524.di;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.omg.spec.dd._20100524.di package. 
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

    private final static QName _Diagram_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Diagram");
    private final static QName _Shape_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Shape");
    private final static QName _DiagramElement_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "DiagramElement");
    private final static QName _LabeledShape_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "LabeledShape");
    private final static QName _Style_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Style");
    private final static QName _Node_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Node");
    private final static QName _LabeledEdge_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "LabeledEdge");
    private final static QName _Edge_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Edge");
    private final static QName _Plane_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Plane");
    private final static QName _Label_QNAME = new QName("http://www.omg.org/spec/DD/20100524/DI", "Label");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.omg.spec.dd._20100524.di
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DiagramElement.Extension }
     * 
     */
    public DiagramElement.Extension createDiagramElementExtension() {
        return new DiagramElement.Extension();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Diagram }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Diagram")
    public JAXBElement<Diagram> createDiagram(Diagram value) {
        return new JAXBElement<Diagram>(_Diagram_QNAME, Diagram.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Shape }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Shape")
    public JAXBElement<Shape> createShape(Shape value) {
        return new JAXBElement<Shape>(_Shape_QNAME, Shape.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiagramElement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "DiagramElement")
    public JAXBElement<DiagramElement> createDiagramElement(DiagramElement value) {
        return new JAXBElement<DiagramElement>(_DiagramElement_QNAME, DiagramElement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LabeledShape }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "LabeledShape")
    public JAXBElement<LabeledShape> createLabeledShape(LabeledShape value) {
        return new JAXBElement<LabeledShape>(_LabeledShape_QNAME, LabeledShape.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Style }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Style")
    public JAXBElement<Style> createStyle(Style value) {
        return new JAXBElement<Style>(_Style_QNAME, Style.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Node }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Node")
    public JAXBElement<Node> createNode(Node value) {
        return new JAXBElement<Node>(_Node_QNAME, Node.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LabeledEdge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "LabeledEdge")
    public JAXBElement<LabeledEdge> createLabeledEdge(LabeledEdge value) {
        return new JAXBElement<LabeledEdge>(_LabeledEdge_QNAME, LabeledEdge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Edge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Edge")
    public JAXBElement<Edge> createEdge(Edge value) {
        return new JAXBElement<Edge>(_Edge_QNAME, Edge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plane }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Plane")
    public JAXBElement<Plane> createPlane(Plane value) {
        return new JAXBElement<Plane>(_Plane_QNAME, Plane.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Label }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.omg.org/spec/DD/20100524/DI", name = "Label")
    public JAXBElement<Label> createLabel(Label value) {
        return new JAXBElement<Label>(_Label_QNAME, Label.class, null, value);
    }

}
