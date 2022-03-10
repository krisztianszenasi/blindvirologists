package main.com.teamalfa.blindvirologists.agents.geneticCodes;

import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.viruses.AmnesiaVirus;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;

public class AmnesiaCode extends GeneticCode{
    @Override
    public AmnesiaVirus createVirus() {
        return new AmnesiaVirus(this);
    }

    @Override
    public Vaccine createVaccine() {
        return new Vaccine(this);
    }
}
