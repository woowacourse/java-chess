package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;

public class BlackTurn extends InGame {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    protected PieceColor getCurrentPieceColor() {
        return PieceColor.BLACK;
    }

    @Override
    protected GameState getNextTurnState() {
        return new WhiteTurn(getBoard());
    }

    @Override
    public boolean isWhiteTurn() {
        return false;
    }
}
