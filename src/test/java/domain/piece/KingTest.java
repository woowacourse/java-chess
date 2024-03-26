package domain.piece;

import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.fixture.PositionFixture;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    /*
        ........
        ........
        ........
        ..***...
        ..*S*...
        ..***...
        ........
        ........
     */
    private static List<Position> validPositions() {
        return List.of(
                PositionFixture.get(C, THREE),
                PositionFixture.get(C, FOUR),
                PositionFixture.get(C, FIVE),
                PositionFixture.get(D, THREE),
                PositionFixture.get(D, FIVE),
                PositionFixture.get(E, THREE),
                PositionFixture.get(E, FOUR),
                PositionFixture.get(E, FIVE)
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
        Piece piece = new King(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidPositions")
    @DisplayName("목적지가 해당 경로 위에 없는 경우 움직일 수 없다.")
    void canMove_False(Position target) {
        Piece piece = new King(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
