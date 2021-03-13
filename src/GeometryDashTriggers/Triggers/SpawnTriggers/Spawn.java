package GeometryDashTriggers.Triggers.SpawnTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.Set;

public class Spawn extends Trigger {
    private int delay;//millisecond

    public Spawn(Set<Integer> groups, boolean multiTriggered, int runGroup, double delay) {
        super(groups, multiTriggered, runGroup);
        this.delay = (int)delay*1000;
    }

    @Override
    public void run() {
        try {
            sleep(delay);
        }catch (Exception e){}
        if(!stop) {
            System.out.println("run: "+target);
            gameMap.runTriggers(target);
        }
    }

    @Override
    public Object clone() {
        Spawn spawn = new Spawn(groups, multiTriggered, target, (double) delay/1000);
        spawn.setGameMap(gameMap);
        return spawn;
    }
}
