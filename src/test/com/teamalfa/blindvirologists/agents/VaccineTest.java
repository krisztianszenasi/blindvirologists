package test.com.teamalfa.blindvirologists.agents;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCodeBank;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {
    // This junit test contains vaccine related test.

    static Virologist vaccinatedVirologist;
    static Virologist unVaccinatedVirologist;
    static GeneticCodeBank codeBank;



    @BeforeAll
    static void setUp(){
        codeBank = GeneticCodeBank.getInstance();

        unVaccinatedVirologist = new Virologist();

        vaccinatedVirologist = new Virologist();
        for(GeneticCode code : codeBank.getCodes()) {
            code.createVaccine().applyTo(vaccinatedVirologist);
        }
    }

    @Test
    void unVaccinatedTest() {
        for(GeneticCode code : codeBank.getCodes()) {
            code.createVirus().applyTo(unVaccinatedVirologist);
        }

        assertEquals(unVaccinatedVirologist.getActiveViruses().size(), codeBank.getCodes().size());
    }

    @Test
    void vaccinatedTest() {
        for(GeneticCode code : codeBank.getCodes()) {
            code.createVirus().applyTo(vaccinatedVirologist);
        }

        assertTrue(unVaccinatedVirologist.getActiveViruses().isEmpty());
    }


}