package GeometryDashTriggers.Triggers;

import java.util.Set;

public class PickUp extends Trigger{
    private final int number;
    public PickUp(Set<Integer> groups, boolean multiTriggered, int targetID, int number) {
        super(groups, multiTriggered, targetID);
        this.number = number;
    }

    @Override
    public Object clone() {
        PickUp pickUp = new PickUp(groups, multiTriggered, target, number);
        pickUp.setGameMap(gameMap);
        return pickUp;
    }

    @Override
    public void run() {
        System.out.println("Debug: " + getClass().getName() + " target: "+ target + " number: " + number + "\n" + getName() + "before number: " + gameMap.getID(target));
        gameMap.addCount(target, number);
        System.out.println(getName() + "after number: " + gameMap.getID(target));
    }
}
