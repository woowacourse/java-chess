package domain;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class RookTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FOUR),
                Arguments.arguments(File.D, Rank.FIVE),
                Arguments.arguments(File.B, Rank.FOUR),
                Arguments.arguments(File.D, Rank.SIX)
        );
    }

    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(File.C, Rank.FIVE),
                Arguments.arguments(File.E, Rank.FIVE)
        );
    }

//    @DisplayName("룩은 수직 또는 수평 방향으로 임의의 칸 수만큼 움직인다.")
//    @ParameterizedTest
//    @MethodSource("movableTargetPosition")
//    void canMoveTest(File targetFile, Rank targetRank) {
//        Rook rook = new Rook(Side.BLACK);
//
//        Position current = PositionFixture.d4();
//        Position target = new Position(targetFile, targetRank);
//
//        boolean actual = rook.canMove(current, target);
//
//        assertThat(actual).isTrue();
//    }
//
//    @DisplayName("룩은 대각선 방향으로 움직일 수 없다.")
//    @ParameterizedTest
//    @MethodSource("immovableTargetPosition")
//    void cantMoveTest(File targetFile, Rank targetRank) {
//        Rook rook = new Rook(Side.BLACK);
//
//        Position current = PositionFixture.d4();
//        Position target = new Position(targetFile, targetRank);
//
//        boolean actual = rook.canMove(current, target);
//
//        assertThat(actual).isFalse();
//    }
}
