package domain.piece;

import static domain.piece.PositionFixture.B3;
import static domain.piece.PositionFixture.B5;
import static domain.piece.PositionFixture.C2;
import static domain.piece.PositionFixture.C6;
import static domain.piece.PositionFixture.E2;
import static domain.piece.PositionFixture.E6;
import static domain.piece.PositionFixture.F3;
import static domain.piece.PositionFixture.F5;
import static domain.position.File.D;
import static domain.position.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    /*
        ........
        ........
        ..*.*...
        .*...*..
        ...S....
        .*...*..
        ..*.*...
        ........
     */

    private static Set<Position> validPositions() {
        return Set.of(B3, B5, C2, C6, E2, E6, F3, F5);
    }

    private static Set<Position> invalidPositions() {
        return PositionFixture.otherPositions(validPositions());
    }

    @ParameterizedTest
    @MethodSource("validPositions")
    @DisplayName("목적지가 해당 경로 위에 있는 경우 움직일 수 있다.")
    void canMove_True(Position target) {
        Piece piece = new Knight(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidPositions")
    @DisplayName("목적지가 해당 경로 위에 없는 경우 움직일 수 없다.")
    void canMove_False(Position target) {
        Piece piece = new Knight(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
