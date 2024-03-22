package chess.domain.piece.blank;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.strategy.BlankMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;
import java.util.Set;

public class Blank extends Piece {
    public Blank() {
        super(Color.NONE);
    }

    @Override
    public Set<Position> findPathTo(Position thisPosition, Position destination) {
        throw new UnsupportedOperationException("해당 위치에 말이 없습니다.");
    }

    @Override
    public MoveStrategy strategy(Map<Position, Piece> board) {
        return new BlankMoveStrategy(board);
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLANK;
    }
}
