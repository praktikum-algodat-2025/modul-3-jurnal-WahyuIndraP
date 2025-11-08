public class TeamList {
    NodeTeam head, tail;

    public void addTeam(String teamName, int gamesPlayed, int wins, int losses, int totalPoints, int totalAssists, int totalRebounds) {
        NodeTeam newTeam = new NodeTeam(teamName, gamesPlayed, wins, losses, totalPoints, totalAssists, totalRebounds);
        if (head == null) head = tail = newTeam;
        else tail = tail.next = newTeam;
    }

    public void sortByWins(boolean ascending) {
        head = new TeamSort(head).quickSort(ascending);
        updateTail();
    }

    public void sortByLosses(boolean ascending) {
        head = new TeamSort(head).quickSort(!ascending); 
        updateTail();
    }

    public void sortByPPG(boolean ascending) {
        head = new TeamSort(head).quickSort(ascending); 
        updateTail();
    }

    private void updateTail() {
        if (head == null) { tail = null; return; }
        NodeTeam temp = head;
        while (temp.next != null) temp = temp.next;
        tail = temp;
    }

    public void showTeams() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.printf("%-25s | %-5s | %-5s | %-5s | %-8s | %-8s | %-8s%n",
                "TEAM NAME", "GP", "W", "L", "PPG", "APG", "RPG");
        System.out.println("------------------------------------------------------------------------------------");

        for (NodeTeam temp = head; temp != null; temp = temp.next)
            System.out.printf("%-25s | %-5d | %-5d | %-5d | %-8.2f | %-8.2f | %-8.2f%n",
                    temp.teamName, temp.gamesPlayed, temp.wins, temp.losses,
                    temp.avgPPG, temp.avgAPG, temp.avgRPG);

        System.out.println("------------------------------------------------------------------------------------");
    }

    public void showTeamsAndPlayers() {
        for (NodeTeam team = head; team != null; team = team.next) {
            System.out.println("\n" + team.teamName);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.printf("%-20s | %-9s | %-7s | %-7s | %-7s%n",
                    "PLAYER NAME", "JERSEY #", "PPG", "APG", "RPG");
            System.out.println("------------------------------------------------------------------------------------");

            for (NodePlayer p = team.players.head; p != null; p = p.next)
                System.out.printf("%-20s | %-9d | %-7.1f | %-7.1f | %-7.1f%n",
                        p.playerName, p.jersey, p.ppg, p.apg, p.rpg);

            System.out.println("------------------------------------------------------------------------------------");
        }
    }

    public PlayerList combineAllPlayers() {
        PlayerList allPlayers = new PlayerList();

        for (NodeTeam team = head; team != null; team = team.next)
            for (NodePlayer p = team.players.head; p != null; p = p.next)
                allPlayers.addPlayer(p.playerName, p.jersey, p.totalPoints, p.totalAssists, p.totalRebounds, p.games);
        return allPlayers;
    }
}
