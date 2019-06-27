package model.game;

import model.piece.PieceColor;

import static model.piece.PieceColor.BLACK;
import static model.piece.PieceColor.WHITE;

public class Turn {
    private int count;
    private PieceColor team;

    Turn() {
        count = 1;
        team = WHITE;
    }

    public Turn(int turnCount) {
        this.count = turnCount;
        resetTeam();
    }

    private PieceColor resetTeam() {
        team = (count % 2 == 0) ? WHITE : BLACK;
        return team;
    }

    void endTurn() {
        count++;
        if (team == WHITE) {
            team = BLACK;
            return;
        }
        team = WHITE;
    }

    PieceColor team() {
        return team;
    }

    public int count() {
        return count;
    }
}