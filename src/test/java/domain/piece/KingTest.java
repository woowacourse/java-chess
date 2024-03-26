package domain.piece;

import static domain.piece.PositionFixture.C3;
import static domain.piece.PositionFixture.C4;
import static domain.piece.PositionFixture.C5;
import static domain.piece.PositionFixture.D3;
import static domain.piece.PositionFixture.D5;
import static domain.piece.PositionFixture.E3;
import static domain.piece.PositionFixture.E4;
import static domain.piece.PositionFixture.E5;
import static domain.piece.PositionFixture.otherPositions;
import static domain.position.File.D;
import static domain.position.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Set;
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
    private static Set<Position> validPositions() {
        return Set.of(C3, C4, C5, D3, D5, E3, E4, E5);
    }

    private static Set<Position> invalidPositions() {
        return otherPositions(validPositions());
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
