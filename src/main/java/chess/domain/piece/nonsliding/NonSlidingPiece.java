package chess.domain.piece.nonsliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.GeneralChessState;
import chess.domain.state.ChessState;
import java.util.Map;
import java.util.Set;

public abstract class NonSlidingPiece extends Piece {
    private final Set<Direction> directions;

    protected NonSlidingPiece(Color color, Set<Direction> directions) {
        super(color);
        this.directions = directions;
    }

    @Override
    public final Set<Position> findPath(Positions positions) {
        Position from = positions.from();
        Position to = positions.to();
        Set<Position> movable = from.findMovablePositions(directions);

        if (!movable.contains(to)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return Set.of();
    }

    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public final ChessState state(Map<Position, Piece> board) {
        return new GeneralChessState(board);
    }
}
