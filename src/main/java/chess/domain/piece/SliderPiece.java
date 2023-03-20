package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.domain.board.position.Movement.D;
import static chess.domain.board.position.Movement.DL;
import static chess.domain.board.position.Movement.DR;
import static chess.domain.board.position.Movement.L;
import static chess.domain.board.position.Movement.R;
import static chess.domain.board.position.Movement.U;
import static chess.domain.board.position.Movement.UL;
import static chess.domain.board.position.Movement.UR;

public abstract class SliderPiece extends Piece {

    protected static final Map<Class<? extends SliderPiece>, List<Movement>> map = Map.of(
            Queen.class, List.of(U, D, R, L, UR, UL, DR, DL),
            Rook.class, List.of(U, D, R, L),
            Bishop.class, List.of(UR, UL, DR, DL)
    );

    protected SliderPiece(final Color color) {
        super(color);
    }

    @Override
    protected Path moveToLocatedPiece(final Position from, final Position to, final Movement movement) {
        Position next = from;
        List<Position> positions = new ArrayList<>();

        while (true) {
            next = next.moveBy(movement);
            if (next.equals(to)) {
                break;
            }
            positions.add(next);
        }

        return new Path(positions);
    }

    @Override
    protected boolean canNotMoveToLocatedPiece(final Movement movement) {
        return !map.get(getClass()).contains(movement);
    }
}
