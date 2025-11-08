public class NodeTeam {
    String teamName;
    int gamesPlayed;
    int wins;
    int losses;
    int totalPoints;
    int totalAssists;
    int totalRebounds;

    double avgPPG, avgAPG, avgRPG;

    NodeTeam next;
    PlayerList players;

    public NodeTeam(String teamName, int gamesPlayed, int wins, int losses, int totalPoints, int totalAssists, int totalRebounds) {
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.totalPoints = totalPoints;
        this.totalAssists = totalAssists;
        this.totalRebounds = totalRebounds;

        if (gamesPlayed > 0) {
            this.avgPPG = (double) totalPoints / gamesPlayed;
            this.avgAPG = (double) totalAssists / gamesPlayed;
            this.avgRPG = (double) totalRebounds / gamesPlayed;
        }
        
        players = new PlayerList();
        next = null;
    }
}
