package piece;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Rook;
import chess.domain.direction.Direction;
import chess.domain.game.Player;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("이동 가능한 방향: 전진, 전진 방향 대각선")
    @ParameterizedTest
    @MethodSource("generateDirection")
    void pawnDirectionsTest(Player player, Direction[] directions) {
        Pawn pawn = new Pawn(player);
        assertThat(pawn.getDirections()).containsExactly(directions);
    }

    static Stream<Arguments> generateDirection() {
        return Stream.of(
                Arguments.of(Player.WHITE, new Direction[]{Direction.NORTH, Direction.NORTH_WEST, Direction.NORTH_EAST}),
                Arguments.of(Player.BLACK, new Direction[]{Direction.SOUTH, Direction.SOUTH_WEST, Direction.SOUTH_EAST})
        );
    }


    @DisplayName("이동 칸 수: 초기 이동 (가능) 1칸, 2칸")
    @ParameterizedTest
    @MethodSource("generatePositions")
    void tileSize_1(Player player, Position from, Position to) {
        Pawn pawn = new Pawn(player);
        assertThat(pawn.validateTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("a2"), Positions.of("a3")),
                Arguments.of(Player.WHITE, Positions.of("a2"), Positions.of("a4")),
                Arguments.of(Player.BLACK, Positions.of("a7"), Positions.of("a6")),
                Arguments.of(Player.BLACK, Positions.of("a7"), Positions.of("a5"))
        );
    }

    @DisplayName("이동 칸 수: 초기 이동 (불가능) 2칸 초과)")
    @ParameterizedTest
    @MethodSource("generatePositions4")
    void tileSize_4(Player player, Position from, Position to) {
        Pawn pawn = new Pawn(player);
        assertThat(pawn.validateTileSize(from, to)).isFalse();
    }

    static Stream<Arguments> generatePositions4() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("a2"), Positions.of("a5")),
                Arguments.of(Player.BLACK, Positions.of("a7"), Positions.of("a4"))
        );
    }

    @DisplayName("이동 칸 수: 초기 이동이 아닐 때 (가능) 1칸")
    @ParameterizedTest
    @MethodSource("generatePositions2")
    void tileSize_2(Player player, Position from, Position to) {
        Pawn pawn = new Pawn(player);
        assertThat(pawn.validateTileSize(from, to)).isTrue();
    }

    static Stream<Arguments> generatePositions2() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("a3"), Positions.of("a4")),
                Arguments.of(Player.BLACK, Positions.of("a6"), Positions.of("a5"))
        );
    }

    @DisplayName("이동 칸 수: 초기 이동이 아닐 때 (불가능) 1칸 초과")
    @ParameterizedTest
    @MethodSource("generatePositions3")
    void tileSize_3(Player player, Position from, Position to) {
        Pawn pawn = new Pawn(player);
        assertThat(pawn.validateTileSize(from, to)).isFalse();
    }

    static Stream<Arguments> generatePositions3() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("a3"), Positions.of("a5")),
                Arguments.of(Player.BLACK, Positions.of("a6"), Positions.of("a4"))
        );
    }

    // (예외 상황) 대각선 공격 (1) 대각선이여야 하고, (2) 같은 편이 아니여야 한다.
    @DisplayName("대각선 공격: (가능) 이동할 위치에 상대 팀 기물이 있을 때")
    @ParameterizedTest
    @MethodSource("generatePositions5")
    void 대각선_공격_성공_다른_팀(Player sourcePlayer, Position from, Player targetPlayer, Position to) {
        Pawn source = new Pawn(sourcePlayer);
        Pawn target = new Pawn(targetPlayer);

        assertThat(source.validateDirection(Direction.getDirection(from, to), target)).isTrue();
    }

    static Stream<Arguments> generatePositions5() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("b2"), Player.BLACK, Positions.of("a3")),
                Arguments.of(Player.WHITE, Positions.of("b2"), Player.BLACK, Positions.of("c3")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Player.WHITE, Positions.of("f6")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Player.WHITE, Positions.of("d6"))
        );
    }


    @DisplayName("대각선 공격: (불가능) 이동할 위치에 아무 것도 없을 때")
    @ParameterizedTest
    @MethodSource("generatePositions6")
    void 대각선_공격시_empty(Player sourcePlayer, Position from, Position to) {
        Pawn source = new Pawn(sourcePlayer);
        assertThat(source.validateDirection(Direction.getDirection(from, to), null)).isFalse();
    }


    static Stream<Arguments> generatePositions6() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("a3")),
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("c3")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("f6")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("d6"))
        );
    }

    @DisplayName("대각선 공격: (비정상) 이동할 위치에 같은 팀이 있을 때")
    @ParameterizedTest
    @MethodSource("generatePositions7")
    void 대각선_공격_같은_팀(Player player, Position from, Position to) {
        Pawn source = new Pawn(player);
        Knight target = new Knight(player);
        assertThat(source.validateDirection(Direction.getDirection(from, to), target)).isFalse();
    }

    static Stream<Arguments> generatePositions7() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("a3")),
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("c3")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("f6")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("d6"))
        );
    }


    @DisplayName("전진: (가능) 이동할 위치에 아무 기물도 없을 때")
    @ParameterizedTest
    @MethodSource("generatePositions8")
    void 전진_성공_empty(Player player, Position from, Position to) {
        Pawn source = new Pawn(player);
        assertThat(source.validateDirection(Direction.getDirection(from, to), null));
    }

    static Stream<Arguments> generatePositions8() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("b3")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("e6"))
        );
    }


    @DisplayName("전진 확인: (불가능) 이동할 위치에 기물이 있을 때")
    @ParameterizedTest
    @MethodSource("generatePositions9")
    void 전진_실패_장애물(Player player, Position from, Position to) {
        Pawn source = new Pawn(player);
        Rook target = new Rook(player);
        assertThat(source.validateDirection(Direction.getDirection(from, to), target)).isFalse();
    }

    static Stream<Arguments> generatePositions9() {
        return Stream.of(
                Arguments.of(Player.WHITE, Positions.of("b2"), Positions.of("b3")),
                Arguments.of(Player.BLACK, Positions.of("e7"), Positions.of("e6"))
        );
    }
}

