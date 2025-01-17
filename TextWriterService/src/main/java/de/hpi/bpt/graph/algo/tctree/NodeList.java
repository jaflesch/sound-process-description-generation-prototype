package de.hpi.bpt.graph.algo.tctree;

import de.hpi.bpt.hypergraph.abs.IVertex;

import java.util.ArrayList;

/**
 * This NodeList is an abstraction of the underlying list type, which stores vertices.
 *
 * @param Implementation of IVertex
 * @author Christian Wiggert
 */
public class NodeList<V extends IVertex> extends ArrayList<V> {

    /**
     *
     */
    private static final long serialVersionUID = -748791916008781735L;

}
