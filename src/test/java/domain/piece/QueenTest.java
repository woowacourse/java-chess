package domain.piece;

import static domain.position.File.A;
import static domain.position.File.B;
import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.File.F;
import static domain.position.File.G;
import static domain.position.File.H;
import static domain.position.Rank.EIGHT;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.ONE;
import static domain.position.Rank.SEVEN;
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

    private static List<Position> validPositions() {
        return List.of(
                PositionFixture.get(A, FOUR),
                PositionFixture.get(B, FOUR),
                PositionFixture.get(C, FOUR),
                PositionFixture.get(D, ONE),
                PositionFixture.get(D, TWO),
                PositionFixture.get(D, THREE),
                PositionFixture.get(D, FIVE),
                PositionFixture.get(D, SIX),
                PositionFixture.get(D, SEVEN),
                PositionFixture.get(D, EIGHT),
                PositionFixture.get(E, FOUR),
                PositionFixture.get(F, FOUR),
                PositionFixture.get(G, FOUR),
                PositionFixture.get(H, FOUR),
                PositionFixture.get(A, ONE),
                PositionFixture.get(B, TWO),
                PositionFixture.get(C, THREE),
                PositionFixture.get(E, FIVE),
                PositionFixture.get(F, SIX),
                PositionFixture.get(G, SEVEN),
                PositionFixture.get(H, EIGHT),
                PositionFixture.get(A, SEVEN),
                PositionFixture.get(B, SIX),
                PositionFixture.get(C, FIVE),
                PositionFixture.get(E, THREE),
                PositionFixture.get(F, TWO),
                PositionFixture.get(G, ONE)
        );
    }

    private static List<Position> invalidPositions() {
        List<Position> validPositions = validPositions();
        return PositionFixture.otherPositions(validPositions);
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
