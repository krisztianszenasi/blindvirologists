package main.com.teamalfa.blindvirologists.agents.geneticCodes;

import java.util.ArrayList;

public class GeneticCodeBank {
    private static GeneticCodeBank instance = null;
    private ArrayList<GeneticCode> codes;

    private GeneticCodeBank() {
        codes = new ArrayList<>();
        codes.add(new ParalyzeCode());
        codes.add(new DanceCode());
        codes.add(new AmnesiaCode());
    }

    static {
        instance = new GeneticCodeBank();
    }

    public ArrayList<GeneticCode> getCodes() {
        return codes;
    }

    public static GeneticCodeBank getInstance() {
        return instance;
    }

}
