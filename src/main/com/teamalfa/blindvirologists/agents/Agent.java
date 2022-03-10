package main.com.teamalfa.blindvirologists.agents;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;

abstract public class Agent {
    protected Virologist target = null;
    protected GeneticCode code = null;

    abstract public void applyTo(Virologist virologist);
    public GeneticCode getCode() {
        return code;
    }
}
