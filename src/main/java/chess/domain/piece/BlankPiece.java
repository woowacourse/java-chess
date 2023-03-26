package chess.domain.piece;

import chess.domain.piece.move_rule.BlankMoveRule;
import chess.domain.position.Position;

import java.util.List;

public class BlankPiece extends Piece {
    private static BlankPiece instance;

    private BlankPiece() {
        super(new BlankMoveRule(), null);
    }

    public static BlankPiece getInstance() {
        if (instance == null) {
            instance = new BlankPiece();
        }
        return instance;
    }

    @Override
    public List<Position> move(Position currentPosition, Position nextPosition) {
        throw new IllegalStateException("기물이 존재하지 않습니다. 움직일 수 없습니다.");
    }

    @Override
    public boolean isOpponent(Piece other) {
        throw new IllegalStateException("빈 기물입니다. 적군을 판단할 수 업습니다.");
    }

    @Override
    public boolean isSameColor(Color color) {
        return super.isSameColor(color);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

}
