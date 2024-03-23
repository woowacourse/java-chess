package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class Empty extends AbstractPiece {

    public Empty() {
        super(PieceType.EMPTY, Team.EMPTY);
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        throw new UnsupportedOperationException();
    }
}
