package chess.domain.piece.blank;

import chess.domain.Score;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.strategy.BlankMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public final class Blank extends Piece {
    public Blank() {
        super(Color.NONE);
    }

    @Override
    public Set<Position> findPath(Positions positions) {
        throw new UnsupportedOperationException("해당 위치에 말이 없습니다.");
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLANK;
    }

    @Override
    public MoveStrategy strategy(Map<Position, Piece> board) {
        return new BlankMoveStrategy(board);
    }

    @Override
    public Score score() {
        return new Score(0);
    }
}
