package chess.domain;

public class Round {
    private int round;

    public Round(int round) {
        this.round = round;
    }

    public Team getTeam() {
        if (round % 2 == 0) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public void nextRound() {
        round++;
    }

    public int getRound() {
        return round;
    }
}
