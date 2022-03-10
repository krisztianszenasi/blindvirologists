package main.com.teamalfa.blindvirologists.agents.geneticCodes;

import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.viruses.ParalyzeVirus;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;

public class ParalyzeCode extends GeneticCode{

    @Override
    public ParalyzeVirus createVirus() {
        return new ParalyzeVirus(this);
    }

    @Override
    public Vaccine createVaccine() {
        return new Vaccine(this);
    }
}
