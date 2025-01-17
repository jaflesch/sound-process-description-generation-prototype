package de.hpi.bpt.process.epc;


/**
 * EPC process interface interface :)
 *
 * @author Artem Polyvyanyy
 */
public interface IProcessInterface extends IFlowObject {

    /**
     * Get linked EPC
     *
     * @return EPC linked to this process interface, null if no process is linked
     */
    @SuppressWarnings("unchecked")
    IEPC getProcess();

    /**
     * Link a process interface to an EPC
     *
     * @param epc EPC to link
     */
    @SuppressWarnings("unchecked")
    void setProcess(IEPC epc);
}
