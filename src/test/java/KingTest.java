import chess.domain.Player;
import chess.domain.chesspieces.King;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    private final King king = new King(Player.WHITE);

    @DisplayName("이동 가능한 방향 - 모든 방향")
    @Test
    void kingDirectionsTest() {
        List<Direction> directions = king.getDirections();
        Assertions.assertThat(directions).containsExactly(Direction.values());
    }

    @DisplayName("이동 칸 수 확인: (정상) 1칸일 때")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_1(Position from, Position to) {
        assertThat(king.validateMovableTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a2")),
                Arguments.of(Positions.of("a8"), Positions.of("a7")),
                Arguments.of(Positions.of("h8"), Positions.of("g7")));
    }

    @DisplayName("잘못된 이동 확인: 1칸 이상 이동")
    @ParameterizedTest
    @MethodSource("generatePositions2")
    void test2(Position from, Position to) {
        assertThat(king.validateMovableTileSize(from, to)).isFalse();
    }

    static Stream<Arguments> generatePositions2() {
        return Stream.of(
                Arguments.of(Positions.of("a1"), Positions.of("a3")),
                Arguments.of(Positions.of("a8"), Positions.of("a1")),
                Arguments.of(Positions.of("h1"), Positions.of("g7")));
    }
//
//    @Test
//    @DisplayName("잘못된 이동 확인 : 같은 편이 있는 곳으로 이동했을 경우")
//    void test3() {
//    }
//
//    @DisplayName("올바른 이동 확인 : 적 기물이 있는 곳으로 이동했을 경우 공격 성공 테스트")
//    void test4() {
//    }
//
//    @DisplayName("올바른 이동 확인 : 빈칸이 있는 곳으로 이동 했을 경우 이동 성공 테스트")
//    void test5() {
//    }
}
