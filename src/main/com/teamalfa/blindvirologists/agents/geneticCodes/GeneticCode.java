package main.com.teamalfa.blindvirologists.agents.geneticCodes;

import main.com.teamalfa.blindvirologists.agents.Vaccine;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;

abstract public class GeneticCode {

    abstract public Virus createVirus();
    abstract public Vaccine createVaccine();
}
