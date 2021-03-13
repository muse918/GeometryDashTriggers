package GeometryDashTriggers.Triggers;

import java.util.Set;

public class Stop extends Trigger{
    public Stop(Set<Integer> groups, boolean multiTriggered, int targetGroup) {
        super(groups, multiTriggered, targetGroup);
    }

    @Override
    public Object clone() {
        Stop stop = new Stop(groups, multiTriggered, target);
        stop.setGameMap(gameMap);
        return stop;
    }

    @Override
    public void run() {
        gameMap.stopTriggers(target);
    }
}
