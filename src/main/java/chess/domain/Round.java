package chess.domain;

public class Round {
    private static final int NEXT_ROUND = 1;
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

    public Round nextRound() {
        return new Round(round + NEXT_ROUND);
    }

    public int getRound() {
        return round;
    }
}
