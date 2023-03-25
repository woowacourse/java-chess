package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.type.Rook;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("룩은 ")
class RookTest {

    @ParameterizedTest
    @MethodSource("isMovableTrueCase")
    @DisplayName("수평, 수직 방향으로 칸 수 제한 없이 이동할 수 있다.")
    void isMovable_true(Position movablePosition) {
        // given
        final Rook rook = new Rook(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(rook.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase() {
        return Stream.of(
                new Position(File.D, Rank.SEVEN),
                new Position(File.H, Rank.FOUR),
                new Position(File.D, Rank.THREE),
                new Position(File.A, Rank.FOUR)
        );
    }

    @ParameterizedTest
    @MethodSource("isMovableFalseCase")
    @DisplayName("대각 방향으로 이동할 수 없다.")
    void isMovable_false(Position notMovablePosition) {
        // given
        final Rook rook = new Rook(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(rook.isMovable(notMovablePosition)).isFalse();
    }

    static Stream<Position> isMovableFalseCase() {
        return Stream.of(
                new Position(File.F, Rank.SIX),
                new Position(File.G, Rank.ONE),
                new Position(File.B, Rank.TWO),
                new Position(File.C, Rank.FIVE)
        );
    }

    @Test
    @DisplayName("이동 경로를 반환한다.")
    void getPaths() {
        // given
        final Rook rook = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position targetPosition = new Position(File.E, Rank.ONE);
        List<Position> expectedPaths = List.of(
                new Position(File.B, Rank.ONE),
                new Position(File.C, Rank.ONE),
                new Position(File.D, Rank.ONE)
        );

        // when
        List<Position> paths = rook.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
