package main.com.teamalfa.blindvirologists;

import main.com.teamalfa.blindvirologists.agents.viruses.Virus;
import main.com.teamalfa.blindvirologists.agents.viruses.VirusComparator;
import main.com.teamalfa.blindvirologists.city.fields.Field;

import java.util.ArrayList;
import java.util.Collections;

public class Virologist {
    private ArrayList<Virus> activeViruses;
    private Field current;

    public Virologist() {
        activeViruses = new ArrayList<>();
    }

    public void move(Field destination) {
        // Variable that stores possible movement modification by viruses.
        Field modified = null;

        // If a virus wants to modify the destination then it returns the desired field in its affectMovement method.
        // Viruses which don't want to modify will return a NULL.
        // Since viruses near to the end of the list can override previous modifications, the activeViruses list
        // must be ordered by some kind of priority. (E.g. paralyzed vs random move)
        if(!activeViruses.isEmpty()){
            sortViruses();
            for(Virus virus : activeViruses)
                modified = virus.affectMovement(current);
        }

        // If the modified variable is not NULL then the movement is affected by a virus.
        if(modified != null)
            destination = modified;

        current.remove(this);
        destination.accept(this);
        current = destination;
    }

    public void infectedBy(Virus virus) {
        activeViruses.add(virus);
    }

    public Field getField() {
        return current;
    }

    private void sortViruses(){
        Collections.sort(activeViruses, new VirusComparator());
    }

    public void setField(Field current) {
        this.current = current;
    }

    public void removeViruses() {
        activeViruses.clear();
    }

    public ArrayList<Virus> getActiveViruses(){
        return activeViruses;
    }
}
