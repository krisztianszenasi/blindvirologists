package main.com.teamalfa.blindvirologists.agents.viruses;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.Agent;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;
import main.com.teamalfa.blindvirologists.city.fields.Field;


abstract public class Virus extends Agent {
    protected int priority;

    public Virus(GeneticCode code) {
        this.code = code;
    }

    public Field affectMovement(Field current) {
        return null;
    }

    public int getPriority(){
        return priority;
    }

    public void applyTo(Virologist virologist) {
        if(virologist.infectedBy(this)) {
            target = virologist;
        }
    }
}

