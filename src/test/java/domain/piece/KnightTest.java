package domain.piece;

import static domain.position.File.B;
import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.File.F;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.SIX;
import static domain.position.Rank.THREE;
import static domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.fixture.PositionFixture;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;
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

    private static List<Position> validPositions() {
        return List.of(
                PositionFixture.get(B, THREE),
                PositionFixture.get(B, FIVE),
                PositionFixture.get(C, TWO),
                PositionFixture.get(C, SIX),
                PositionFixture.get(E, TWO),
                PositionFixture.get(E, SIX),
                PositionFixture.get(F, THREE),
                PositionFixture.get(F, FIVE)
        );
    }

    private static List<Position> invalidPositions() {
        List<Position> validPositions = validPositions();
        return PositionFixture.otherPositions(validPositions);
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
