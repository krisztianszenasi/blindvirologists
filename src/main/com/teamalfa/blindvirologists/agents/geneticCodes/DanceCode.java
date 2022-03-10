package main.com.teamalfa.blindvirologists.agents.geneticCodes;

import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.viruses.DanceVirus;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;

public class DanceCode extends GeneticCode{
    @Override
    public DanceVirus createVirus() {
        return new DanceVirus(this);
    }

    @Override
    public Vaccine createVaccine() {
        return new Vaccine(this);
    }
}
