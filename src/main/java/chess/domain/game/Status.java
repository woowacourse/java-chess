package chess.domain.game;

import chess.domain.piece.Color;

public class Status {
    private final Score white;
    private final Score black;
    private final Color winner;

    public Status(Score white, Score black, Color winner) {
        this.white = white;
        this.black = black;
        this.winner = winner;
    }

    public Color getWinner() {
        return winner;
    }

    public Score getWhiteScore() {
        return white;
    }

    public Score getBlackScore() {
        return black;
    }
}
