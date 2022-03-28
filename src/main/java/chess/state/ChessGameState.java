package chess.state;

import chess.ChessBoard;
import chess.piece.Color;

public abstract class ChessGameState implements State {

    private final ChessBoard chessBoard;
    private final Color color;

    protected ChessGameState(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    @Override
    public ChessBoard chessBoard() {
        return chessBoard;
    }

    @Override
    public Color currentColor() {
        return color;
    }

    @Override
    public Color changeTurn() {
        return color.reverse();
    }
}

