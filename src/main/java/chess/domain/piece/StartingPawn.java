package chess.domain.piece;

import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.TWO;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class StartingPawn extends AbstractPawn {

    protected static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.of(A, TWO), Position.of(B, TWO), Position.of(C, TWO), Position.of(D, TWO),
            Position.of(E, TWO), Position.of(F, TWO), Position.of(G, TWO), Position.of(H, TWO)
    );
    protected static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.of(A, SEVEN), Position.of(B, SEVEN), Position.of(C, SEVEN), Position.of(D, SEVEN),
            Position.of(E, SEVEN), Position.of(F, SEVEN), Position.of(G, SEVEN), Position.of(H, SEVEN)
    );

    public StartingPawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
