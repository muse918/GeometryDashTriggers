package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class InstantCount extends Trigger {
    public final int targetCount;
    public final int targetNumber;
    public final boolean toggleMode;
    public final CompareMode compareMode;

    public enum CompareMode{
        SMALLER,
        SAME,
        BIGGER
    }

    protected InstantCount(Set<Integer> groups, boolean multiTriggered, int targetGroup, int targetCount, int targetNumber, boolean toggleMode, CompareMode compareMode) {
        super(groups, multiTriggered, targetGroup);
        this.targetCount = targetCount;
        this.targetNumber = targetNumber;
        this.toggleMode = toggleMode;
        this.compareMode = compareMode;
    }

    @Override
    public Object clone() {
        return new InstantCount(groups, multiTriggered, targetGroup, targetCount, targetNumber, toggleMode, compareMode);
    }

    @Override
    public void run() {
        switch (compareMode){
            case SMALLER:
                if(gameMap.getCount(targetCount) < targetNumber)gameMap.runTriggers(targetGroup);
                break;
            case SAME:
                if(gameMap.getCount(targetCount) == targetNumber)gameMap.runTriggers(targetGroup);
                break;
            case BIGGER:
                if(gameMap.getCount(targetCount) > targetNumber)gameMap.runTriggers(targetGroup);
                break;
        }
    }
}
