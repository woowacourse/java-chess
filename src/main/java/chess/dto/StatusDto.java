package chess.dto;

import chess.domain.game.Score;
import chess.domain.piece.Color;

public class StatusDto {
    private Score white;
    private Score black;
    private Color winner;

    public StatusDto(Score white, Score black, Color winner) {
        this.white = white;
        this.black = black;
        this.winner = winner;
    }

    public Score getWhite() {
        return white;
    }

    public void setWhite(Score white) {
        this.white = white;
    }

    public Score getBlack() {
        return black;
    }

    public void setBlack(Score black) {
        this.black = black;
    }

    public Color getWinner() {
        return winner;
    }

    public void setWinner(Color winner) {
        this.winner = winner;
    }
}
