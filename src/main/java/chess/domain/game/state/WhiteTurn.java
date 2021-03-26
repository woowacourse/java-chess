package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

public class WhiteTurn extends Running {

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceByPosition(source);

        if (sourcePiece.isBlack()) {
            throw new IllegalStateException("백색 차례엔 백색 말만 이동 가능합니다.");
        }

        board().moveIfValidPosition(source, target);
    }

    @Override
    public State passTurn() {
        return new BlackTurn(board());
    }
}
