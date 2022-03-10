package test.com.teamalfa.blindvirologists.equipments;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCodeBank;
import main.com.teamalfa.blindvirologists.equipments.Cloak;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {
    // This junit test contains equipments related test.

    static Virologist virologist;
    static GeneticCodeBank codeBank;



    @BeforeAll
    static void setUp(){
        codeBank = GeneticCodeBank.getInstance();
        virologist = new Virologist();
    }

    @Test
    void cloakTest() {
        Cloak cloak = new Cloak();
        virologist.equip(cloak);

        for(GeneticCode code : codeBank.getCodes()) {
            code.createVirus().applyTo(virologist);
        }

        assertTrue(virologist.getActiveViruses().isEmpty());
    }
}