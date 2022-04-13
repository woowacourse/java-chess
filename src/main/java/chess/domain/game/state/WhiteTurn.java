package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;

public class WhiteTurn extends InGame {

    protected WhiteTurn() {
        super(Board.createInitializedBoard());
    }

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    protected PieceColor getCurrentPieceColor() {
        return PieceColor.WHITE;
    }

    @Override
    protected GameState getNextTurnState() {
        return new BlackTurn(getBoard());
    }

    @Override
    public boolean isWhiteTurn() {
        return true;
    }
}
