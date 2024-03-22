package chess.domain.piece;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
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

    private static final Set<Position> WHITE_INITIAL_POSITIONS = Set.of(
            Position.of(A, TWO), Position.of(B, TWO), Position.of(C, TWO), Position.of(D, TWO),
            Position.of(E, TWO), Position.of(F, TWO), Position.of(G, TWO), Position.of(H, TWO)
    );
    private static final Set<Position> BLACK_INITIAL_POSITIONS = Set.of(
            Position.of(A, SEVEN), Position.of(B, SEVEN), Position.of(C, SEVEN), Position.of(D, SEVEN),
            Position.of(E, SEVEN), Position.of(F, SEVEN), Position.of(G, SEVEN), Position.of(H, SEVEN)
    );

    public StartingPawn(final Color color, final Position position) {
        super(color, position);
    }

    public static Set<Piece> ofInitialPositions(final Color color) {
        if (color.isBlack()) {
            return initialPiecesOf(BLACK_INITIAL_POSITIONS, BLACK, StartingPawn::new);
        }
        return initialPiecesOf(WHITE_INITIAL_POSITIONS, WHITE, StartingPawn::new);
    }

    @Override
    public Set<Position> move(final Position source, final Position target) {
        return null;
    }
}
