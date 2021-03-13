package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class InstantCount extends Trigger {
    public final int targetID;
    public final int targetNumber;
    public final boolean toggleMode;
    public final CompareMode compareMode;

    public enum CompareMode{
        SMALLER,
        SAME,
        BIGGER
    }

    public InstantCount(Set<Integer> groups, boolean multiTriggered, int targetGroup, int targetID, int targetNumber, boolean toggleMode, CompareMode compareMode) {
        super(groups, multiTriggered, targetGroup);
        this.targetID = targetID;
        this.targetNumber = targetNumber;
        this.toggleMode = toggleMode;
        this.compareMode = compareMode;
    }

    @Override
    public Object clone() {
        InstantCount instantCount = new InstantCount(groups, multiTriggered, target, targetID, targetNumber, toggleMode, compareMode);
        instantCount.setGameMap(gameMap);
        return instantCount;
    }

    @Override
    public void run() {
        switch (compareMode){
            case SMALLER:
                if(gameMap.getID(targetID) < targetNumber)gameMap.toggleTriggers(target, toggleMode);
                break;
            case SAME:
                if(gameMap.getID(targetID) == targetNumber)gameMap.toggleTriggers(target, toggleMode);
                break;
            case BIGGER:
                if(gameMap.getID(targetID) > targetNumber)gameMap.toggleTriggers(target, toggleMode);
                break;
        }
    }
}
