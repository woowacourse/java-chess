import chess.domain.Player;
import chess.domain.chesspieces.Bishop;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    private final Bishop bishop = new Bishop(Player.WHITE);

    @DisplayName("이동 가능한 방향 확인: 대각선")
    @Test
    void bishopDirectionsTest() {
        assertThat(bishop.getDirections()).containsExactly(Direction.DIAGONAL_DOWN_LEFT,
                Direction.DIAGONAL_DOWN_RIGHT,
                Direction.DIAGONAL_TOP_LEFT,
                Direction.DIAGONAL_TOP_RIGHT);
    }

    @DisplayName("이동 칸 수 확인: (가능) 1칸 이상 이동")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_1(Position from, Position to) {
        assertThat(bishop.validateMovableTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("b2")),
                Arguments.of(Positions.of("a1"), Positions.of("h7")));
    }
}