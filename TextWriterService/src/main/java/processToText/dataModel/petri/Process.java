package processToText.dataModel.petri;

import org.jbpt.algo.graph.TransitiveClosure;
import org.jbpt.petri.*;

import java.util.*;

/**
 * Process of a {@link NetSystem}.
 * A {@link Process} of a {@link NetSystem} is a causal net which represents a footprint of a concurrent run of the {@link NetSystem}.
 *
 * @author Artem Polyvyanyy
 */
public class Process {
    // Embedding of the causal net into the net system
    // Maps nodes of the causal net to nodes of the (originative) net system
    public Map<Node, Node> embedding = null;
    // Poiner to the original net system
    private NetSystem originativeNetSystem = null;
    // Clone of the original net system
    private NetSystem cloneNetSystem = null;
    // Mapping from nodes of the original net system to nodes of its clone object
    private Map<Node, Node> original2clone = null;
    // Mapping from nodes of the clone to nodes of original net system
    private Map<Node, Node> clone2original = null;
    // Process setup
    private ProcessSetup setup = null;
    // Causal net of the process (represents a concurrent run)
    private PetriNet causalNet = null;
    // Storage for the latest cut (maximal set of mutually concurrent places)
    // maxCut is a point to append new events
    private Map<Place, Place> maxCut = null;
    // Cutoff and corresponding transitions/events
    private Map<Transition, Transition> cutoff2corr = null;
    // Markings encoded by this process
    private Set<Marking> markings = null;
    // Maps marking of originative system to set of cosets of the causal net
    private Map<Marking, Set<Set<Place>>> marking2cosets = null;
    // Maps coset of the causal net to marking of the originative net system
    private Map<Set<Place>, Marking> coset2marking = null;
    // Set of duplicate markings stored in the causal net
    private Set<Marking> duplicateMarkings = null;

    /**
     * Empty constructor for technical issues.
     */
    private Process() {
    }

    /**
     * Constructor of a process of a net system.
     *
     * @param sys Originative net system.
     */
    public Process(NetSystem sys) {
        this(sys, new ProcessSetup());
    }

    public Process(NetSystem sys, ProcessSetup setup) {
        this.setup = setup;
        this.originativeNetSystem = sys;
        this.original2clone = new HashMap<Node, Node>();
        this.clone2original = new HashMap<Node, Node>();
        this.cloneNetSystem = (NetSystem) this.originativeNetSystem.clone(this.original2clone);
        for (Map.Entry<Node, Node> entry : this.original2clone.entrySet())
            this.clone2original.put(entry.getValue(), entry.getKey());
        this.maxCut = new HashMap<Place, Place>();
        this.causalNet = new PetriNet();
        this.embedding = new HashMap<Node, Node>();
        this.cutoff2corr = new HashMap<Transition, Transition>();

        this.markings = new HashSet<Marking>();
        this.marking2cosets = new HashMap<Marking, Set<Set<Place>>>();
        this.coset2marking = new HashMap<Set<Place>, Marking>();
        this.duplicateMarkings = new HashSet<Marking>();

        this.constructInitialProcess();
    }

    /**
     * Construct initial process of the net system.
     */
    private void constructInitialProcess() {
        for (Place p : this.originativeNetSystem.getMarking().toMultiSet()) {
            Place c = new Place(p.getName());
            this.causalNet.addPlace(c);
            this.embedding.put(c, p);
            this.maxCut.put(c, p);

            this.markings.add((Marking) this.originativeNetSystem.getMarking().clone());
        }
    }

    /**
     * Check if the process is initial.
     * A process of a net system is initial if it contains no transitions/events.
     *
     * @return <tt>true</tt> if process is initial; <tt>false</tt> otherwise.
     */
    public boolean isInitial() {
        return this.causalNet.getTransitions().isEmpty();
    }

    /**
     * Get originative net system.
     *
     * @return Originative net system.
     */
    public NetSystem getOriginativeNetSystem() {
        return this.originativeNetSystem;
    }

    /**
     * Get possible extensions of this process.
     *
     * @return Transitions of the originative net system that can be used to extend this process.
     */
    public Set<Transition> getPossibleExtensions() {
        return this.clone2original(this.cloneNetSystem.getEnabledTransitions());
    }

    /**
     * Check if transition of the causal net is a cutoff transition.
     *
     * @param t Transition of the causal net.
     * @return <tt>true</tt> if transition is a cutoff transition of the causal net; <tt>false</tt> otherwise.
     */
    public boolean isCutoff(Transition t) {
        return this.cutoff2corr.containsKey(t);
    }

    /**
     * Append transition of the originative system to this process.
     *
     * @param t Transition to append to this process.
     * @return Fresh appended transition-event of the causal net or <tt>null</tt> if no transition-event was appended.
     */
    public Transition appendEvent(Transition t) {
        //if (this.isComplete()) return null;
        if (this.causalNet.getTransitions().size() >= this.setup.MAX_EVENTS) return null;

        Transition tt = (Transition) this.original2clone.get(t);

        if (this.cloneNetSystem.fire(tt)) { // one must be able to fire transition in the net system
            // CONSTRUCT FRESH EVENT
            // create fresh event-transition
            Transition e = new Transition(t.getName());
            this.embedding.put(e, t);

            // handle preset of t
            Collection<Place> toRemove = new ArrayList<Place>();
            for (Place p : this.originativeNetSystem.getPreset(t)) {
                for (Place c : this.maxCut.keySet()) {
                    if (this.maxCut.get(c).equals(p)) {
                        this.causalNet.addFlow(c, e);
                        toRemove.add(c);
                    }
                }
            }
            for (Place c : toRemove) this.maxCut.remove(c);

            // handle postset of t
            for (Place p : this.originativeNetSystem.getPostset(t)) {
                Place c = new Place(p.getLabel());
                this.embedding.put(c, p);
                this.causalNet.addFlow(e, c);
                maxCut.put(c, p);
            }

            // STORE MARKINGS
            this.storeMarkings(e);

            return e;
        }

        return null;
    }

    private void storeMarkings(Transition e) {
        Collection<Place> cs = new ArrayList<Place>(this.causalNet.getPlaces());
        cs.removeAll(this.causalNet.getPostset(e));

        TransitiveClosure<Flow, Node> tc = new TransitiveClosure<Flow, Node>(this.causalNet);
        Collection<Place> toRemove = new ArrayList<Place>();
        for (Place p : cs) {
            boolean flag = true;
            for (Place q : this.causalNet.getPostset(e)) {
                if (tc.hasPath(p, q) || tc.hasPath(q, p)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) toRemove.add(p);
        }
        cs.removeAll(toRemove);

        if (cs.isEmpty()) {
            Set<Place> coset = new HashSet<Place>(this.causalNet.getPostset(e));
            // coset is ready
            this.storeMarking(coset);
        } else {
            for (Place p : cs) {
                Set<Place> coset = new HashSet<Place>(this.causalNet.getPostset(e));
                coset.add(p);
                for (Place q : cs) {
                    if (q.equals(p)) continue;
                    boolean flag = true;
                    for (Place r : coset) {
                        if (tc.hasPath(q, r) || tc.hasPath(r, q)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        coset.add(q);
                    }
                }
                // coset is ready
                this.storeMarking(coset);
            }
        }
    }

    private void storeMarking(Set<Place> coset) {
        Marking M = new Marking(this.originativeNetSystem);
        for (Place p : coset) {
            M.put((Place) this.embedding.get(p), M.get(this.embedding.get(p)) + 1);
        }

        this.coset2marking.put(coset, M);
        if (this.marking2cosets.get(M) == null) {
            Set<Set<Place>> cosets = new HashSet<Set<Place>>();
            cosets.add(coset);
            this.marking2cosets.put(M, cosets);
        } else {
            int size = this.marking2cosets.get(M).size();
            this.marking2cosets.get(M).add(coset);
            if (size != this.marking2cosets.get(M).size())
                this.duplicateMarkings.add(M);
        }

        this.markings.add(M);
    }

    /**
     * Get causal net.
     *
     * @return Causal net of this {@link Process}.
     */
    public PetriNet getCausalNet() {
        return this.causalNet;
    }

    /**
     * Get cosets of enabled transitions.
     *
     * @return Cosets of enabled transitions.
     */
    public Set<Set<Transition>> getCosetsOfEnabledTransitions() {
        Set<Set<Transition>> cosets = new HashSet<Set<Transition>>();

        // enabled clone transitions
        Set<Transition> enabled = new HashSet<Transition>(this.original2clone(this.getPossibleExtensions()));

        Collection<Place> ps = new ArrayList<Place>();
        for (Transition t : enabled)
            ps.addAll(this.cloneNetSystem.getPreset(t));
        if (this.cloneNetSystem.getPresetPlaces(enabled).size() == ps.size()) {
            cosets.add(this.clone2original(enabled));
            return cosets;
        }

        Set<Transition> coset = new HashSet<Transition>();
        Map<Place, Integer> M = new HashMap<Place, Integer>();

        this.getCosetsOfEnabledTransitionsHelper(coset, M, enabled, cosets);

        return cosets;
    }

    private void getCosetsOfEnabledTransitionsHelper(Set<Transition> coset, Map<Place, Integer> M, Set<Transition> enabled, Set<Set<Transition>> cosets) {
        if (enabled.isEmpty()) {
            cosets.add(this.clone2original(coset));
        } else {
            for (Transition t : enabled) {
                // new coset
                Set<Transition> newCoset = new HashSet<Transition>(coset);
                newCoset.add(t);

                // new M
                Map<Place, Integer> newM = new HashMap<Place, Integer>(M);
                for (Place p : this.cloneNetSystem.getPreset(t))
                    if (newM.get(p) == null)
                        newM.put(p, 1);
                    else
                        newM.put(p, newM.get(p) + 1);

                // new enabled
                Set<Transition> newEnabled = new HashSet<Transition>();
                for (Transition tt : enabled) {
                    if (t.equals(tt)) continue;

                    boolean flag = true;
                    for (Place p : this.cloneNetSystem.getPreset(tt)) {
                        int ps = this.cloneNetSystem.getTokens(p);
                        int pm = newM.get(p) == null ? 0 : newM.get(p);
                        if (ps <= pm) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag)
                        newEnabled.add(tt);
                }

                this.getCosetsOfEnabledTransitionsHelper(newCoset, newM, newEnabled, cosets);
            }
        }
    }

    /**
     * Check if this process is deadlock free.
     * It is deadlock free if it either has an extension, or places from the maximal cut correspond to sink places of the originative system.
     *
     * @return <tt>true</tt> if this process is deadlock free; <tt>false</tt> otherwise.
     */
    public boolean isDeadlockFree() {
        return this.isComplete() || !this.getPossibleExtensions().isEmpty();
    }

    /**
     * Check if this process is safe.
     * It is safe if it either has no pair of concurrent conditions which refer to the same place of the originative system.
     *
     * @return <tt>true</tt> if this process is safe; <tt>false</tt> otherwise.
     */
    public boolean isSafe() {
        TransitiveClosure<Flow, Node> tc = new TransitiveClosure<Flow, Node>(this.causalNet);
        for (Place p1 : this.causalNet.getPlaces()) {
            for (Place p2 : this.causalNet.getPlaces()) {
                if (p1.equals(p2)) continue;
                if (!(tc.hasPath(p1, p2) || tc.hasPath(p2, p1)) && this.embedding.get(p1).equals(this.embedding.get(p2)))
                    return false;
            }
        }

        return true;
    }

    public boolean isComplete() {
        if (this.originativeNetSystem.getSinkPlaces().containsAll(this.maxCut.values()))
            return true;

        return !this.duplicateMarkings.isEmpty();

    }

    @Override
    public Process clone() {
        Process clone = new Process();

        clone.originativeNetSystem = this.originativeNetSystem;
        clone.original2clone = new HashMap<Node, Node>();
        clone.clone2original = new HashMap<Node, Node>();
        Map<Node, Node> this2clone = new HashMap<Node, Node>();
        clone.cloneNetSystem = (NetSystem) this.cloneNetSystem.clone(this2clone);
        for (Map.Entry<Node, Node> entry : this2clone.entrySet()) {
            clone.original2clone.put(this.clone2original.get(entry.getKey()), entry.getValue());
            clone.clone2original.put(entry.getValue(), this.clone2original.get(entry.getKey()));
        }

        clone.setup = this.setup;

        // causal net
        Map<Node, Node> causal2clone = new HashMap<Node, Node>();
        clone.causalNet = (PetriNet) this.causalNet.clone(causal2clone);
        Map<Node, Node> clone2causal = new HashMap<Node, Node>();
        for (Map.Entry<Node, Node> entry : causal2clone.entrySet())
            clone2causal.put(entry.getValue(), entry.getKey());

        // embedding
        clone.embedding = new HashMap<Node, Node>();
        for (Node n : clone.causalNet.getNodes()) {
            clone.embedding.put(n, this.embedding.get(clone2causal.get(n)));
        }

        // maxcut
        clone.maxCut = new HashMap<Place, Place>();
        for (Place p : clone.causalNet.getPlaces()) {
            if (this.maxCut.containsKey(clone2causal.get(p))) {
                clone.maxCut.put(p, (Place) this.embedding.get(clone2causal.get(p)));
            }
        }

        // cutoffs
        clone.cutoff2corr = new HashMap<Transition, Transition>();
        for (Map.Entry<Transition, Transition> entry : this.cutoff2corr.entrySet())
            clone.cutoff2corr.put((Transition) clone2causal.get(entry.getKey()), (Transition) clone2causal.get(entry.getValue()));

        // duplicateMarkings
        clone.duplicateMarkings = new HashSet<Marking>(this.duplicateMarkings);
        // coset2marking
        clone.coset2marking = new HashMap<Set<Place>, Marking>();
        for (Map.Entry<Set<Place>, Marking> entry : this.coset2marking.entrySet()) {
            Set<Place> coset = new HashSet<Place>();
            for (Place p : entry.getKey()) coset.add((Place) causal2clone.get(p));
            clone.coset2marking.put(coset, entry.getValue());
        }
        // marking2cosets
        clone.marking2cosets = new HashMap<Marking, Set<Set<Place>>>();
        for (Map.Entry<Marking, Set<Set<Place>>> entry : this.marking2cosets.entrySet()) {
            Set<Set<Place>> cosets = new HashSet<Set<Place>>();
            for (Set<Place> coset : entry.getValue()) {
                Set<Place> cloneCoset = new HashSet<Place>();
                for (Place p : coset) cloneCoset.add((Place) causal2clone.get(p));
                cosets.add(cloneCoset);
            }
            clone.marking2cosets.put(entry.getKey(), cosets);
        }

        clone.markings = new HashSet<Marking>(this.markings);

        return clone;
    }

    public String toDOT() {
        String result = "digraph G {\n";
        result += "graph [fontname=\"Helvetica\" fontsize=10 nodesep=0.35 ranksep=\"0.25 equally\"];\n";
        result += "node [fontname=\"Helvetica\" fontsize=10 fixedsize style=filled penwidth=\"2\"];\n";
        result += "edge [fontname=\"Helvetica\" fontsize=10 arrowhead=normal color=black];\n";
        result += "\n";
        result += "node [shape=circle];\n";

        for (Place p : this.causalNet.getPlaces()) {
            result += String.format("\tn%s[label=\"%s\" width=\".5\" height=\".5\" fillcolor=white];\n", p.getId().replace("-", ""), p.getName());
        }

        result += "\n";
        result += "node [shape=box];\n";

        for (Transition t : this.causalNet.getTransitions()) {
            if (this.isCutoff(t)) {
                if (t.getName() == "")
                    result += String.format("\tn%s[label=\"%s\" width=\".5\" height=\".1\" fillcolor=orange];\n", t.getId().replace("-", ""), t.getName());
                else
                    result += String.format("\tn%s[label=\"%s\" width=\".5\" height=\".5\" fillcolor=orange];\n", t.getId().replace("-", ""), t.getName());
            } else {
                if (t.getName() == "")
                    result += String.format("\tn%s[label=\"%s\" width=\".5\" height=\".1\" fillcolor=white];\n", t.getId().replace("-", ""), t.getName());
                else
                    result += String.format("\tn%s[label=\"%s\" width=\".5\" height=\".5\" fillcolor=white];\n", t.getId().replace("-", ""), t.getName());
            }
        }

        result += "\n";
        for (Flow f : this.causalNet.getFlow()) {
            result += String.format("\tn%s->n%s;\n", f.getSource().getId().replace("-", ""), f.getTarget().getId().replace("-", ""));
        }

        if (!this.cutoff2corr.isEmpty())
            result += "\tedge [fontname=\"Helvetica\" fontsize=8 arrowhead=normal color=orange];\n";

        for (Map.Entry<Transition, Transition> entry : this.cutoff2corr.entrySet()) {
            result += String.format("\tn%s->n%s;\n", entry.getKey().getId().replace("-", ""), entry.getValue().getId().replace("-", ""));
        }

        result += "}\n";

        return result;
    }


    private Set<Transition> clone2original(Set<Transition> ts) {
        Set<Transition> result = new HashSet<Transition>();
        for (Transition t : ts)
            result.add((Transition) clone2original.get(t));

        return result;
    }

    private Set<Transition> original2clone(Set<Transition> ts) {
        Set<Transition> result = new HashSet<Transition>();
        for (Transition t : ts)
            result.add((Transition) original2clone.get(t));

        return result;
    }

    public Collection<Marking> getMarkings() {
        return this.markings;
    }

    public Collection<Marking> getDuplicateMarkings() {
        return this.duplicateMarkings;
    }

    public boolean isComplete(Set<Transition> coset) {
        Marking M = new Marking(this.originativeNetSystem);
        for (Place p : this.maxCut.keySet()) {
            M.put((Place) this.embedding.get(p), M.get(this.embedding.get(p)) + 1);
        }

        if (!this.duplicateMarkings.contains(M))
            return false;

        for (Set<Place> ps : this.marking2cosets.get(M)) {
            Set<Transition> ts = new HashSet<Transition>();
            for (Transition t : this.causalNet.getPostsetTransitions(ps)) {
                ts.add((Transition) this.embedding.get(t));
            }

            if (ts.equals(coset))
                return true;
        }

        return false;
    }

    public Set<Transition> getCoveredTransitions() {
        Set<Transition> result = new HashSet<Transition>();
        for (Transition t : this.causalNet.getTransitions()) {
            result.add((Transition) this.embedding.get(t));
        }
        return result;
    }

    public Marking getCurrentMarking() {
        Marking M = new Marking(this.originativeNetSystem);
        for (Place p : this.maxCut.keySet()) {
            M.put((Place) this.embedding.get(p), M.get(this.embedding.get(p)) + 1);
        }

        return M;
    }

    public void removeTransitions(Set<Transition> ts) {
        Set<Transition> toRemove = new HashSet<Transition>();
        for (Node n : this.causalNet.getNodes()) {
            if (ts.contains(this.embedding.get(n)))
                toRemove.add((Transition) n);
        }
        Set<Place> places = new HashSet<Place>();
        places.addAll(this.causalNet.getPresetPlaces(toRemove));
        places.addAll(this.causalNet.getPostsetPlaces(toRemove));
        this.causalNet.removeTransitions(toRemove);
        for (Place p : places) {
            if (this.causalNet.getAdjacent(p).size() == 0)
                this.causalNet.removePlace(p);
        }
    }
}
