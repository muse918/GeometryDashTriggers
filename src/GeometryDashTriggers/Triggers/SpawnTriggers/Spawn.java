package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class Spawn extends Trigger {
    private int delay;//millisecond
    protected Spawn(Set<Integer> groups, boolean multiTriggered, int runGroup, double delay) {
        super(groups, multiTriggered, runGroup);
        this.delay = (int)delay*1000;
    }

    @Override
    public void run() {
        try {
            sleep(delay);
        }catch (Exception e){}
        if(!stop) {
            gameMap.runTriggers(targetGroup);
        }
    }

    @Override
    public Object clone() {
        Spawn spawn = new Spawn(groups, multiTriggered, targetGroup, (double) delay/1000);
        return spawn;
    }
}
