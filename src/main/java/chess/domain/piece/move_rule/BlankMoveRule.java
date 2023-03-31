package chess.domain.piece.move_rule;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;

public class BlankMoveRule implements MoveRule {
    @Override
    public List<Position> move(Position currentPosition, Position nextPosition) {
        throw new IllegalStateException("빈 기물입니다. 움직일 수 없습니다.");
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLANK;
    }

    @Override
    public boolean isPawnMove() {
        return false;
    }

    @Override
    public boolean isKingMove() {
        return false;
    }

}
