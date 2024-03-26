package domain.piece;

import static domain.piece.PositionFixture.A4;
import static domain.piece.PositionFixture.B4;
import static domain.piece.PositionFixture.C4;
import static domain.piece.PositionFixture.D1;
import static domain.piece.PositionFixture.D2;
import static domain.piece.PositionFixture.D3;
import static domain.piece.PositionFixture.D5;
import static domain.piece.PositionFixture.D6;
import static domain.piece.PositionFixture.D7;
import static domain.piece.PositionFixture.D8;
import static domain.piece.PositionFixture.E4;
import static domain.piece.PositionFixture.F4;
import static domain.piece.PositionFixture.G4;
import static domain.piece.PositionFixture.H4;
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

public class RookTest {

    /*
        ...*....
        ...*....
        ...*....
        ...*....
        ***S****
        ...*....
        ...*....
        ...*....
     */

    private static Set<Position> validPositions() {
        return Set.of(A4, B4, C4, D1, D2, D3, D5, D6, D7, D8, E4, F4, G4, H4);
    }

    private static Set<Position> invalidPositions() {
        return otherPositions(validPositions());
    }

    @ParameterizedTest
    @MethodSource("validPositions")
    @DisplayName("목적지가 직선 경로에 있는 경우 움직일 수 있다.")
    void canMove_Vertical_True(Position target) {
        Piece piece = new Rook(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidPositions")
    @DisplayName("목적지가 직선 경로에 없는 경우 움직일 수 없다.")
    void canMove_Horizontal_False(Position target) {
        Piece piece = new Rook(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
