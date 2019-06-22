package chess.domain;

import chess.domain.pieces.Color;

public class ChessGame {

    private Color colorOfTurn;

    public ChessGame() {
        this.colorOfTurn = Color.WHITE;
    }

    public Color changeColorOfTurn() {
        return (this.colorOfTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
}
