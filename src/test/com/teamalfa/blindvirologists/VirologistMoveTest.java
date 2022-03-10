package test.com.teamalfa.blindvirologists;

import main.com.teamalfa.blindvirologists.Virologist;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.DanceCode;
import main.com.teamalfa.blindvirologists.agents.geneticCodes.ParalyzeCode;
import main.com.teamalfa.blindvirologists.agents.viruses.DanceVirus;
import main.com.teamalfa.blindvirologists.agents.viruses.ParalyzeVirus;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VirologistMoveTest {
    // This junit test contains move specific test for the virologist.

    static Virologist virologist;
    static Field current;
    static ParalyzeVirus paralyzeVirus;
    static DanceVirus danceVirus;

    @BeforeAll
    static void setUp(){
        // create virologist
        virologist = new Virologist();

        // create current field and its neighbours
        current = new Field();
        ArrayList<Field> neighbours = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            neighbours.add(new Field());
        }
        current.setNeighbours(neighbours);

        // create viruses which affect movement
        paralyzeVirus = new ParalyzeCode().createVirus();
        danceVirus = new DanceCode().createVirus();
    }

    @BeforeEach
    void resetConditions(){
        for(Field field : current.getNeighbours()) {
            field.remove(virologist);
        }
        current.accept(virologist);
        virologist.setField(current);
        virologist.removeViruses();
    }

    @Test
    void normalMove() {
        // in normal a condition the virologist should move to the destination
        Field destination = current.getNeighbours().get(1);
        virologist.move(destination);

        assertEquals(virologist.getField(), destination,
                "Virologist should move to its destination.");
        assertFalse(current.getVirologists().contains(virologist),
                "Current field should no longer contain the virologist.");
        assertTrue(destination.getVirologists().contains(virologist),
                "Destination field should contain the virologist.");
    }

    @Test
     void paralyzedMove() {
        // if the virologist is paralyzed it should stay at its current field
        Field destination = current.getNeighbours().get(2);
        paralyzeVirus.applyTo(virologist);
        virologist.move(destination);

        assertEquals(virologist.getField(), current,
                "Virologist should stay put because of the paralyze virus");
        assertTrue(current.getVirologists().contains(virologist),
                "Current field should still contain the virologist.");
        assertFalse(destination.getVirologists().contains(virologist),
                "Destination should not contain the virologist.");
    }

    @Test
    void randomMove() {
        // If the virologist is affected by the dance virus it should move randomly. The way it is tested is
        //  that we force the virologist to step 20 times and check if it landed at a different
        // location (other than the current field) more than 0 times.
        Field destination = current.getNeighbours().get(3);
        danceVirus.applyTo(virologist);
        int randomDestCounter = 0;

        for(int i = 0; i < 20; i++) {
            virologist.move(destination);
            if(virologist.getField() != destination)
                randomDestCounter++;

            // check if landed somewhere other than current
            assertNotEquals(destination, current, "Virologist should move to one of its neighbours.");

            // reset to current field
            virologist.getField().getVirologists().remove(virologist);
            virologist.setField(current);
            current.getVirologists().add(virologist);
        }

        assertTrue(randomDestCounter > 0,
                "Virologist should have moved to a random field at least once.");
    }

    @Test
    void testSortViruses() {
        // Virologist shouldn't move, because paralyzeVirus has higher priority than danceVirus
        Field destination = current.getNeighbours().get(4);
        paralyzeVirus.applyTo(virologist);
        danceVirus.applyTo(virologist);

        virologist.move(destination);

        assertEquals(virologist.getField(), current,
                "Virologist should not move because the ParalyzeVirus has higher priority than the DanceVirus.");
        assertTrue(current.getVirologists().contains(virologist),
                "Virologist should not leave the current field," +
                        "the ParalyzeVirus has higher priority than the DanceVirus.");
        assertFalse(destination.getVirologists().contains(virologist),
                "Virologist should not leave the current field, " +
                        "the ParalyzeVirus has higher priority than the DanceVirus.");
    }

}