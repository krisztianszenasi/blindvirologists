package test.com.teamalfa.blindvirologists;

import main.com.teamalfa.blindvirologists.Virologist;
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

        // create field and its neighbours
        current = new Field();
        current.accept(virologist);
        virologist.setField(current);
        ArrayList<Field> neighbours = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            neighbours.add(new Field());
        current.setNeighbours(neighbours);

        // create viruses
        paralyzeVirus = new ParalyzeVirus();
        danceVirus = new DanceVirus();
    }

    @BeforeEach
    void resetVirologist(){
        virologist.move(current);
        virologist.removeViruses();
    }

    @Test
    void normalMove() {
        // in normal a condition the virologist should move to the destination
        Field destination = current.getNeighbours().get(1);
        virologist.move(destination);

        assertEquals(virologist.getField(), destination);
        assertFalse(current.getVirologists().contains(virologist));
        assertTrue(destination.getVirologists().contains(virologist));
    }

    @Test
     void paralyzedMove() {
        // if the virologist is paralyzed it should stay at its current field
        Field destination = current.getNeighbours().get(2);
        paralyzeVirus.applyTo(virologist);
        virologist.move(destination);

        assertEquals(virologist.getField(), current);
        assertTrue(current.getVirologists().contains(virologist));
        assertFalse(destination.getVirologists().contains(virologist));
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
            assertFalse(destination == current);

            // reset to current field
            virologist.getField().getVirologists().remove(virologist);
            virologist.setField(current);
            current.getVirologists().add(virologist);
        }

        assertTrue(randomDestCounter > 0);
    }

    @Test
    void testSortViruses() {
        // Virologist shouldn't move, because paralyzeVirus has higher priority than danceVirus
        Field destination = current.getNeighbours().get(4);
        paralyzeVirus.applyTo(virologist);
        danceVirus.applyTo(virologist);

        virologist.move(destination);

        assertEquals(virologist.getField(), current);
        assertTrue(current.getVirologists().contains(virologist));
        assertFalse(destination.getVirologists().contains(virologist));
    }

}