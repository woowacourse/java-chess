package domain.piece;

import static domain.piece.PositionFixture.A1;
import static domain.piece.PositionFixture.A4;
import static domain.piece.PositionFixture.A7;
import static domain.piece.PositionFixture.B2;
import static domain.piece.PositionFixture.B4;
import static domain.piece.PositionFixture.B6;
import static domain.piece.PositionFixture.C3;
import static domain.piece.PositionFixture.C4;
import static domain.piece.PositionFixture.C5;
import static domain.piece.PositionFixture.D1;
import static domain.piece.PositionFixture.D2;
import static domain.piece.PositionFixture.D3;
import static domain.piece.PositionFixture.D5;
import static domain.piece.PositionFixture.D6;
import static domain.piece.PositionFixture.D7;
import static domain.piece.PositionFixture.D8;
import static domain.piece.PositionFixture.E3;
import static domain.piece.PositionFixture.E4;
import static domain.piece.PositionFixture.E5;
import static domain.piece.PositionFixture.F2;
import static domain.piece.PositionFixture.F4;
import static domain.piece.PositionFixture.F6;
import static domain.piece.PositionFixture.G1;
import static domain.piece.PositionFixture.G4;
import static domain.piece.PositionFixture.G7;
import static domain.piece.PositionFixture.H4;
import static domain.piece.PositionFixture.H8;
import static domain.piece.PositionFixture.otherPositions;
import static domain.position.File.D;
import static domain.position.File.H;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class QueenTest {

    /*
        ...*...*
        *..*..*.
        .*.*.*..
        ..***...
        ***S****
        ..***...
        .*.*.*..
        *..*..*.
     */

    private static Set<Position> validPositions() {
        return Set.of(A4, B4, C4, D1, D2, D3, D5, D6, D7, D8, E4, F4, G4, H4,
                A1, B2, C3, E5, F6, G7, H8, A7, B6, C5, E3, F2, G1);
    }

    private static Set<Position> invalidPositions() {
        return otherPositions(validPositions());
    }

    @ParameterizedTest
    @MethodSource("validPositions")
    @DisplayName("목적지가 직선 또는 대각선 경로에 있는 경우 움직일 수 있다.")
    void canMove_StraightDiagonal_True(Position target) {
        Piece piece = new Queen(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidPositions")
    @DisplayName("목적지가 직선 또는 대각선 경로에 없는 경우 움직일 수 없다.")
    void canMove_StraightDiagonal_False() {
        Piece piece = new Queen(Color.WHITE);
        Position source = PositionGenerator.generate(D, FOUR);
        Position target = PositionGenerator.generate(H, THREE);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
