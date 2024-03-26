package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.GeneralChessState;
import chess.domain.state.ChessState;
import java.util.Map;
import java.util.Set;

public abstract class SlidingPiece extends Piece {
    private final Set<Direction> directions;

    protected SlidingPiece(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    @Override
    public final Set<Position> findPath(Positions positions) {
        Position from = positions.from();
        Position to = positions.to();
        Direction direction = from.findDirectionTo(to);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return from.findCourses(direction, to);
    }
    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public final ChessState state(Map<Position, Piece> board) {
        return new GeneralChessState(board);
    }
}
