package GeometryDashTriggers.Triggers;

import java.util.Set;

public class Toggle extends Trigger{
    private final boolean mode;
    public Toggle(Set<Integer> groups, boolean multiTriggered, int targetGroup, boolean mode) {
        super(groups, multiTriggered, targetGroup);
        this.mode = mode;
    }

    @Override
    public Object clone() {
        Toggle toggle = new Toggle(groups, multiTriggered, target, mode);
        toggle.setGameMap(gameMap);
        return toggle;
    }

    @Override
    public void run() {
        gameMap.toggleTriggers(target, mode);
    }


}
