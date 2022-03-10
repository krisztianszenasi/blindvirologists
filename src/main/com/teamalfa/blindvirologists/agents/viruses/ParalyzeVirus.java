package main.com.teamalfa.blindvirologists.agents.viruses;

import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;
import main.com.teamalfa.blindvirologists.city.fields.Field;

public class ParalyzeVirus extends Virus{

    public ParalyzeVirus(GeneticCode code) {
        super(code); priority = 2;
    }

    @Override
    public Field affectMovement(Field current) {
        return current;
    }
}
