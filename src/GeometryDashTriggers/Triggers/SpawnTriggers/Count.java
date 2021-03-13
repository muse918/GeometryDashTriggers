package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class Count extends Trigger {
    public final int targetID;
    public final int targetNumber;
    public final boolean toggleMode;
    public Count(Set<Integer> groups, boolean multiTriggered, int runGroup, int targetID, int targetNumber, boolean toggleMode) {
        super(groups, multiTriggered, runGroup);
        this.targetID = targetID;
        this.targetNumber = targetNumber;
        this.toggleMode = toggleMode;
    }

    @Override
    public Object clone() {
        Count count = new Count(groups, multiTriggered, target, targetID, targetNumber, toggleMode);
        count.setGameMap(gameMap);
        return count;
    }

    @Override
    public void run() {
        Thread.yield();
        int beforeNumber, nowNumber = gameMap.getID(targetID);
        while (!stop){
            beforeNumber = nowNumber;
            nowNumber = gameMap.getID(targetID);

            if(beforeNumber<targetNumber^nowNumber<targetNumber||(nowNumber==targetNumber && beforeNumber!=targetNumber)){
                gameMap.toggleTriggers(target, toggleMode);
                break;
            }

        }
    }
}
