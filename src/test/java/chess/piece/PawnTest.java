package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("폰은 ")
public class PawnTest {

    @ParameterizedTest
    @MethodSource("isMovableTrueCase_white")
    @DisplayName("흰색 진영인 경우 위, 오른쪽 위, 왼쪽 위 방향으로 한 칸씩 이동할 수 있다.")
    void isMovable_white_true(Position movablePosition) {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);

        // when, then
        assertThat(pawn.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase_white() {
        return Stream.of(
                new Position(File.B, Rank.THREE),
                new Position(File.C, Rank.THREE),
                new Position(File.A, Rank.THREE)
        );
    }

    @ParameterizedTest
    @MethodSource("isMovableFalseCase_white")
    @DisplayName("앞, 오른쪽 앞, 왼쪽 앞을 제외한 방향으로 이동할 수 없다.")
    void isMovable_white_false(Position notMovablePosition) {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);

        // when, then
        assertThat(pawn.isMovable(notMovablePosition)).isFalse();
    }

    static Stream<Position> isMovableFalseCase_white() {
        return Stream.of(
                new Position(File.C, Rank.TWO),
                new Position(File.C, Rank.ONE),
                new Position(File.B, Rank.ONE),
                new Position(File.A, Rank.ONE),
                new Position(File.A, Rank.TWO)
        );
    }

    @Test
    @DisplayName("흰색 진영이고 시작 위치인 경우 두 칸 위로 이동 가능하다.")
    void isMovable_whiteStartPosition_twoUp() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);
        final Position upPosition = new Position(File.B, Rank.FOUR);

        // when, then
        assertThat(pawn.isMovable(upPosition)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovableTrueCase_black")
    @DisplayName("검은색 진영인 경우 아래, 왼쪽 아래, 오른쪽 아래 방향으로 한 칸씩 이동할 수 있다.")
    void isMovable_black_true(Position movablePosition) {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);

        // when, then
        assertThat(pawn.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase_black() {
        return Stream.of(
                new Position(File.B, Rank.SIX),
                new Position(File.C, Rank.SIX),
                new Position(File.A, Rank.SIX)
        );
    }

    @ParameterizedTest
    @MethodSource("isMovableFalseCase_black")
    @DisplayName("검은색 진영인 경우 아래, 왼쪽 아래, 오른쪽 아래를 제외한 방향으로 이동할 수 없다.")
    void isMovable_black_false(Position notMovablePosition) {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);

        // when, then
        assertThat(pawn.isMovable(notMovablePosition)).isFalse();
    }

    static Stream<Position> isMovableFalseCase_black() {
        return Stream.of(
                new Position(File.C, Rank.SEVEN),
                new Position(File.C, Rank.EIGHT),
                new Position(File.B, Rank.EIGHT),
                new Position(File.A, Rank.EIGHT),
                new Position(File.A, Rank.SEVEN)
        );
    }

    @Test
    @DisplayName("검은색 진영이고 시작 위치인 경우 두 칸 아래로 이동 가능하다.")
    void isMovable_blackStartPosition_twoDown() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);
        final Position downPosition = new Position(File.B, Rank.FIVE);

        // when, then
        assertThat(pawn.isMovable(downPosition)).isTrue();
    }

    @Test
    @DisplayName("시작 위치에서 두 칸 이동했을 떄의 경로를 반환한다.")
    void getPaths() {
        // given
        final Pawn pawn = new Pawn(new Position(File.A, Rank.TWO), Side.WHITE);
        final Position targetPosition = new Position(File.A, Rank.FOUR);
        List<Position> expectedPaths = List.of(new Position(File.A, Rank.THREE));

        // when
        List<Position> paths = pawn.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }

    @Test
    @DisplayName("한 칸만 이동했을 경우 빈 경로를 반환한다.")
    void getPaths_empty() {
        // given
        final Pawn pawn = new Pawn(new Position(File.A, Rank.TWO), Side.WHITE);
        final Position targetPosition = new Position(File.A, Rank.THREE);
        List<Position> expectedPaths = Collections.emptyList();

        // when
        List<Position> paths = pawn.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
