package chess.dto;

import chess.domain.piece.Piece;

public class TeamDTO {
    private String name;
    private String score;
    private boolean isTurn;

    public TeamDTO(String name, String score, boolean isTurn) {
        this.name = name;
        this.score = score;
        this.isTurn = isTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(final boolean turn) {
        isTurn = turn;
    }

    public String getScore() {
        return score;
    }

    public void setScore(final String score) {
        this.score = score;
    }
}
