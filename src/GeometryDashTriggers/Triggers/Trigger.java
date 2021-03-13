package GeometryDashTriggers.Triggers;

import GeometryDashTriggers.GameMap;

import java.util.Set;

public abstract class Trigger extends Thread implements Cloneable{

    //상수
    public final Set<Integer> groups;
    public final boolean multiTriggered;
    public final int target;
    protected GameMap gameMap;//GameMap에서 직접 실행할때 전달

    //실행시 사용
    public boolean run = false;
    public boolean toggle = false;

    //실행후 사용
    protected boolean stop = false;

    protected Trigger(Set<Integer> groups, boolean multiTriggered, int target) {
        this.groups = groups;
        this.multiTriggered = multiTriggered;
        this.target = target;
    }


    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    public void stopTrigger(){
        stop = true;
    }

    public void toggleTrigger(boolean mode){
        toggle = mode;
    }

    @Override
    public abstract Object clone();

    @Override
    public abstract void run();
}
