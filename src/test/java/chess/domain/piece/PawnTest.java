package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    @Test
    @DisplayName("White 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void whitePawnNoMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(-1, -1))).isFalse();
    }

    @Test
    @DisplayName("Black 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void blackPawnNoMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovableDirection(Direction.of(1, 1))).isFalse();
    }

    @Test
    @DisplayName("White 폰에게 허용된 이동 명령을 했을 경우 true 반환 ")
    void whitePawnMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(0, 1))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("White 폰이 이동할 수 있는 거리인지 확인")
    @MethodSource("distanceParameter")
    void whitePawnMovableDistance(int xDiff, int yDiff, boolean result) {
        Pawn pawn = new Pawn(Team.WHITE);
        LocationDiff locationDiff = new LocationDiff(xDiff, yDiff);
        assertThat(pawn.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> distanceParameter() {
        return Stream.of(
                Arguments.arguments(0, 1, true),
                Arguments.arguments(0, 2, true),
                Arguments.arguments(1, 1, true),
                Arguments.arguments(0, 3, false),
                Arguments.arguments(2, 2, false)
        );
    }
}