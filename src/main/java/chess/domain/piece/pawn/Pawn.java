package chess.domain.piece.pawn;

import chess.domain.score.Score;
import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.ChessState;
import chess.domain.state.PawnChessState;
import java.util.Map;
import java.util.Set;

public abstract class Pawn extends Piece {
    private final Set<Direction> directions;

    protected Pawn(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    public final boolean isCaptureMove(Positions positions) {
        Position from = positions.from();
        Position to = positions.to();
        Direction direction = from.findDirectionTo(to);
        return sameWithCaptureMove(direction);
    }

    protected abstract boolean sameWithCaptureMove(Direction direction);

    public abstract Piece update();

    @Override
    public final Set<Position> findPath(Positions positions) {
        Position from = positions.from();
        Position to = positions.to();
        Set<Position> movable = from.findMovablePositions(directions);

        if (!movable.contains(to)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return from.findCourses(from.findDirectionTo(to), to);
    }

    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public final ChessState state(Map<Position, Piece> board) {
        return new PawnChessState(board);
    }

    @Override
    public Score score() {
        return new Score(1);
    }
}
