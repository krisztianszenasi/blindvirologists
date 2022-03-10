package main.com.teamalfa.blindvirologists.agents.viruses;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.Agent;
import main.com.teamalfa.blindvirologists.city.fields.Field;


abstract public class Virus extends Agent {
    protected int priority;

    public Field affectMovement(Field current) {
        return null;
    }

    public int getPriority(){
        return priority;
    }

    public void applyTo(Virologist virologist) {
        virologist.infectedBy(this);
    }
}

