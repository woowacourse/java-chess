package chess.domain;

import chess.domain.piece.Color;

public class Turn {

    private Color turn;

    Turn(){
        this.turn = Color.WHITE;
    }

    void changeTurn() {
        turn = turn.changeColor(turn);
    }

    public Color getTurn() {
        return turn;
    }
}
