package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

public class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceByPosition(source);

        if (sourcePiece.isWhite()) {
            throw new IllegalStateException("흑색 차례엔 흑색 말만 이동 가능합니다.");
        }

        board().moveIfValidPosition(source, target);
    }

    @Override
    public State passTurn() {
        if (board().isAliveBothKings()) {
            return new WhiteTurn(board());
        }

        return new BlackWin(board());
    }
}
