package GeometryDashTriggers;

import GeometryDashTriggers.Triggers.Trigger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {
    private Map<Integer, Vector<Trigger>> runningTriggersByGroup = new ConcurrentHashMap<>();//실행중인 트리거 일람(그룹별)
    private Map<Integer, LinkedList<Trigger>> triggerListByGroup = new HashMap<>();//그룹별 트리거(실행요청시 그 그룹에 해당한 리스트들의 트리거 실행)
    private List<Trigger> initTriggers = new LinkedList<>();//그룹별 트리거(실행요청시 그 그룹에 해당한 리스트들의 트리거 실행)
    private Map<Integer, CountInteger> counts = new ConcurrentHashMap<>();//변수

    public GameMap(){
    }



    public void addTriggers(Trigger trigger){
        trigger.setGameMap(this);
        trigger.groups.forEach(group -> {
            if (!triggerListByGroup.containsKey(group)) {
                triggerListByGroup.put(group, new LinkedList<>());
            }
            triggerListByGroup.get(group).add(trigger);
        });
    }

    public void addInitTriggers(Trigger trigger){
        initTriggers.add(trigger);
    }

    public void runTriggers(int group){
        if (!triggerListByGroup.containsKey(group)){
//            System.out.println("Debug: nothing in group "+group);
            return;
        }
//        System.out.println("Debug: size of group "+triggerListByGroup.get(group).size());
        triggerListByGroup.get(group).forEach(trigger -> {
            if((trigger.multiTriggered || !trigger.run)&&!trigger.toggle) {
//                System.out.println("Debug: run "+trigger.getClass().getName());
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
        if (!runningTriggersByGroup.containsKey(group)){
            return;
        }
        runningTriggersByGroup.get(group).forEach(Trigger::stopTrigger);
        runningTriggersByGroup.remove(group);
    }

    public void toggleTriggers(int group, boolean toggleMode){
        if (!triggerListByGroup.containsKey(group)){
            return;
        }
        triggerListByGroup.get(group).forEach(trigger -> trigger.toggleTrigger(toggleMode));

        if(toggleMode){
            stopTriggers(group);
        }else {
            runTriggers(group);
        }
    }

    public void start(){
        initTriggers.forEach(trigger -> {
            trigger.setGameMap(this);
            trigger.start();
        });
    }

    public int getID(int ID){
        if (counts.containsKey(ID)) {
            return counts.get(ID).countInt;
        } else {
            counts.put(ID, new CountInteger(0));
            return 0;
        }
    }

    public void addCount(int ID, int number){
        if (counts.containsKey(ID)) {
            counts.get(ID).add(number);
        } else {
            counts.put(ID, new CountInteger(number));
        }
    }
}