package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class Count extends Trigger {
    public final int targetCount;
    public final int targetNumber;
    public final boolean mode;
    protected Count(Set<Integer> groups, boolean multiTriggered, int runGroup, int targetCount, int targetNumber, boolean mode) {
        super(groups, multiTriggered, runGroup);
        this.targetCount = targetCount;
        this.targetNumber = targetNumber;
        this.mode = mode;
    }

    @Override
    public Object clone() {
        return new Count(groups, multiTriggered, targetGroup, targetCount, targetNumber, mode);
    }

    @Override
    public void run() {
        Thread.yield();
        int beforeNumber, nowNumber = gameMap.getCount(targetCount);
        while (!stop){

            beforeNumber = nowNumber;
            nowNumber = gameMap.getCount(targetCount);

            if(beforeNumber<targetNumber^nowNumber<targetNumber||(nowNumber==targetNumber && beforeNumber!=targetNumber)){
                gameMap.toggleTriggers(targetGroup, mode);
                break;
            }

        }
    }
}
