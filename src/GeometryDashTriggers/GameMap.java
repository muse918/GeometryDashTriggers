package GeometryDashTriggers;

import GeometryDashTriggers.Triggers.Trigger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {
    private Map<Integer, Vector<Trigger>> runningTriggersByGroup = new ConcurrentHashMap<>();//실행중인 트리거 일람(그룹별)
    private Map<Integer, LinkedList<Trigger>> triggerListByGroup = new HashMap<>();//그룹별 트리거(실행요청시 그 그룹에 해당한 리스트들의 트리거 실행)
    private LinkedList<Trigger> initTriggers;//그룹별 트리거(실행요청시 그 그룹에 해당한 리스트들의 트리거 실행)
    private Map<Integer, Integer> counts = new ConcurrentHashMap<>();//변수

    public GameMap(){

    }



    public void addTriggers(Trigger trigger, int group){
        trigger.setGameMap(this);
        if (!runningTriggersByGroup.containsKey(group)) {
            runningTriggersByGroup.put(group, new Vector<>());
        }
        triggerListByGroup.get(group).add(trigger);
    }

    public void addInitTriggers(Trigger trigger){
        initTriggers.add(trigger);
    }

    public void runTriggers(int group){
        if (!triggerListByGroup.containsKey(group)){
            return;
        }
        triggerListByGroup.get(group).forEach(trigger -> {
            if((trigger.multiTriggered || !trigger.run)&&trigger.toggle) {
                trigger.run = true;
                if (!runningTriggersByGroup.containsKey(group)) {
                    runningTriggersByGroup.put(group, new Vector<>());
                }
                Trigger clonedTrigger = (Trigger) trigger.clone();
                runningTriggersByGroup.get(group).add(clonedTrigger);
                clonedTrigger.start();
            }
        });
    }

    public void stopTriggers(int group){
        if (!triggerListByGroup.containsKey(group)){
            return;
        }
        runningTriggersByGroup.get(group).forEach(trigger -> {
            trigger.stopTrigger();
            runningTriggersByGroup.remove(group);
        });
    }

    public void toggleTriggers(int group, boolean toggleMode){
        if (!triggerListByGroup.containsKey(group)){
            return;
        }
        runningTriggersByGroup.get(group).forEach(trigger -> trigger.toggleTrigger(toggleMode));
        if (toggleMode){
            stopTriggers(group);
        }else {
            runTriggers(group);
        }
    }

    public void start(){
        initTriggers.forEach(Thread::start);
    }

    public int getCount(int index){
        if(counts.containsKey(index)){
            counts.put(index, 0);
            return 0;
        }else {
            return counts.get(index);
        }
    }

    public void addCount(int index, int number){
        if(counts.containsKey(index)){
            counts.put(index, number);
        }else {
            int count = counts.get(index) + number;
            counts.replace(index, count);
        }
    }
}