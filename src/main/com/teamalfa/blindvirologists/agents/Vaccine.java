package main.com.teamalfa.blindvirologists.agents;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;

public class Vaccine extends Agent {
    private GeneticCode code;

    public Vaccine(GeneticCode code) {
        this.code = code;
    }


    @Override
    public void applyTo(Virologist virologist) {
        virologist.protectedBy(code);
    }
}
