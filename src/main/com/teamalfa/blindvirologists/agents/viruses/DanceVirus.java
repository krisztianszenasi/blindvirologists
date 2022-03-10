package main.com.teamalfa.blindvirologists.agents.viruses;

import main.com.teamalfa.blindvirologists.city.fields.Field;

import java.util.ArrayList;
import java.util.Random;

public class DanceVirus extends Virus {

    public DanceVirus(){
        priority = 1;
    }

    @Override
    public Field affectMovement(Field current) {
        ArrayList<Field> neighbours = current.getNeighbours();
        return pickRandom(neighbours);
    }

    private Field pickRandom(ArrayList<Field> neighbours) {
        int size = neighbours.size();
        int idx = new Random().nextInt(size);
        return neighbours.get(idx);
    }
}

