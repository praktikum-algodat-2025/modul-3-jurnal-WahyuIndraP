public class NodePlayer {
    String playerName;
    int jersey;
    int totalPoints, totalAssists, totalRebounds, games;
    double ppg, apg, rpg;
    NodePlayer next;

    public NodePlayer(String playerName, int jersey, int totalPoints, int totalAssists, int totalRebounds, int games) {
        this.playerName = playerName;
        this.jersey = jersey;
        this.totalPoints = totalPoints;
        this.totalAssists = totalAssists;
        this.totalRebounds = totalRebounds;
        this.games = games;

        if (games <= 0) {
            this.ppg = this.apg = this.rpg = 0;
        } else {
            this.ppg = (double) totalPoints / games;
            this.apg = (double) totalAssists / games;
            this.rpg = (double) totalRebounds / games;
        }
    }
}