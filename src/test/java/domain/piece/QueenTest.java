package domain.piece;

import domain.position.Position;
import fixture.PieceFixture;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(PositionFixture.c4()),
                Arguments.arguments(PositionFixture.d5()),
                Arguments.arguments(PositionFixture.c5()),
                Arguments.arguments(PositionFixture.e5()),
                Arguments.arguments(PositionFixture.b4()),
                Arguments.arguments(PositionFixture.d6()),
                Arguments.arguments(PositionFixture.b6()),
                Arguments.arguments(PositionFixture.f6())
        );
    }

    @DisplayName("퀸은 수직, 수평 또는 대각선 방향으로 임의의 칸 수만큼 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(Position target) {
        Queen queen = PieceFixture.blackQueen();

        Position current = PositionFixture.d4();

        boolean actual = queen.canMove(current, target, new LinkedHashMap<>());

        assertThat(actual).isTrue();
    }
}
