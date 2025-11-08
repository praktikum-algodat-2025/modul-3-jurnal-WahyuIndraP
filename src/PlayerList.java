public class PlayerList {
    NodePlayer head, tail;

    public void addPlayer(String name, int jersey, int totalPoints, int totalAssists, int totalRebounds, int games) {
        NodePlayer newPlayer = new NodePlayer(name, jersey, totalPoints, totalAssists, totalRebounds, games);
        if (head == null) head = tail = newPlayer;
        else tail = tail.next = newPlayer;
    }

    private void updateTail() {
        if (head == null) {
            tail = null;
            return;
        }
        NodePlayer temp = head;
        while (temp.next != null) temp = temp.next;
        tail = temp;
    }

    private void performSort(boolean ascending, String method) {
        long startTime = System.nanoTime();

        PlayerSort sorter = new PlayerSort(head);
        switch (method.toLowerCase()) {
            case "merge" -> sorter.mergeSort(ascending);
            case "quick" -> sorter.quickSort(ascending);
            default -> {
                System.out.println("Unknown sorting method: " + method);
                return;
            }
        }

        head = sorter.getHead();
        updateTail();

        long elapsedTime = System.nanoTime() - startTime;
        System.out.printf("%s Sort Elapsed Time: %.3f msec%n",
                method.substring(0, 1).toUpperCase() + method.substring(1).toLowerCase(),
                elapsedTime / 1_000_000.0);
    }

    public void sortByPPG(boolean ascending, String method) {
        performSort(ascending, method);
    }

    public void showAllPlayers() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-20s | %-9s | %-7s | %-7s | %-7s%n",
                "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
        System.out.println("-------------------------------------------------------------------------------");

        for (NodePlayer temp = head; temp != null; temp = temp.next) {
            System.out.printf("%-20s | %-9d | %-7.1f | %-7.1f | %-7.1f%n",
                    temp.playerName, temp.jersey, temp.ppg, temp.apg, temp.rpg);
        }

        System.out.println("-------------------------------------------------------------------------------");
    }
}
