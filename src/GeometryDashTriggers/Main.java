package GeometryDashTriggers;

import GeometryDashTriggers.Triggers.PickUp;
import GeometryDashTriggers.Triggers.SpawnTriggers.InstantCount;
import GeometryDashTriggers.Triggers.SpawnTriggers.Spawn;
import GeometryDashTriggers.Triggers.Stop;
import GeometryDashTriggers.Triggers.Toggle;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();

        gameMap.addInitTriggers(new PickUp(new HashSet<>(Arrays.asList()), false, 1, 5));
        gameMap.addInitTriggers(new Spawn(new HashSet<>(Arrays.asList()), false, 1, 1));
        gameMap.addTriggers(new Spawn(new HashSet<>(Arrays.asList(2)), true, 1, 0.1));
        gameMap.addTriggers(new Spawn(new HashSet<>(Arrays.asList(1)), true, 2, 0.1));
        gameMap.addTriggers(new InstantCount(new HashSet<>(Arrays.asList(2)), true, 3, 1, 0, false, InstantCount.CompareMode.BIGGER));
        gameMap.addTriggers(new InstantCount(new HashSet<>(Arrays.asList(2)), true, 4, 1, 0, false, InstantCount.CompareMode.SAME));
        gameMap.addTriggers(new InstantCount(new HashSet<>(Arrays.asList(2)), true, 5, 1, 0, false, InstantCount.CompareMode.SMALLER));



        gameMap.addTriggers(new PickUp(new HashSet<>(Arrays.asList(3)), true, 2, 1));
        gameMap.addTriggers(new PickUp(new HashSet<>(Arrays.asList(3)), true, 1, -1));
        gameMap.addTriggers(new Toggle(new HashSet<>(Arrays.asList(4)), true, 1, true));
        gameMap.addTriggers(new Toggle(new HashSet<>(Arrays.asList(4)), true, 2, true));
        gameMap.addTriggers(new PickUp(new HashSet<>(Arrays.asList(5)), true, 2, -1));
        gameMap.addTriggers(new PickUp(new HashSet<>(Arrays.asList(5)), true, 1, 1));




        gameMap.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(gameMap.getID(2));
    }
}
