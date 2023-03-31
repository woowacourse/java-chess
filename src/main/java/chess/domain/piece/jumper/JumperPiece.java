package chess.domain.piece.jumper;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

import static chess.domain.board.position.Movement.D;
import static chess.domain.board.position.Movement.DDL;
import static chess.domain.board.position.Movement.DDR;
import static chess.domain.board.position.Movement.DL;
import static chess.domain.board.position.Movement.DR;
import static chess.domain.board.position.Movement.L;
import static chess.domain.board.position.Movement.LLD;
import static chess.domain.board.position.Movement.LLU;
import static chess.domain.board.position.Movement.R;
import static chess.domain.board.position.Movement.RRD;
import static chess.domain.board.position.Movement.RRU;
import static chess.domain.board.position.Movement.U;
import static chess.domain.board.position.Movement.UL;
import static chess.domain.board.position.Movement.UR;
import static chess.domain.board.position.Movement.UUL;
import static chess.domain.board.position.Movement.UUR;

public abstract class JumperPiece extends Piece {

    protected static final Map<Class<? extends JumperPiece>, List<Movement>> map = Map.of(
            King.class, List.of(U, D, R, L, UR, UL, DR, DL),
            Knight.class, List.of(
                    UUR, UUL, RRU, RRD,
                    DDR, DDL, LLU, LLD
            )
    );

    protected JumperPiece(final Color color) {
        super(color);
    }

    @Override
    public boolean canNotMoveToLocatedPiece(final Movement movement) {
        return !map.get(getClass()).contains(movement);
    }

    @Override
    public Path moveToLocatedPiece(final Position from, final Position to, final Movement movement,
                                   final Piece locatedPiece) {

        if (canNotMovable(from, to, movement)) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "은 한 칸만 이동할 수 있습니다.");
        }

        return new Path();
    }

    protected abstract boolean canNotMovable(final Position from, final Position to, final Movement movement);
}
