package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.Bishop;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("비숍은 ")
class BishopTest {

    @ParameterizedTest
    @MethodSource("isMovableTrueCase_diagonal")
    @DisplayName("대각 방향으로 칸 수 제한 없이 이동할 수 있다.")
    void isMovable_true(Position movablePosition) {
        // given
        final Bishop bishop = new Bishop(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(bishop.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase_diagonal() {
        return Stream.of(
                new Position(File.F, Rank.SIX),
                new Position(File.G, Rank.ONE),
                new Position(File.A, Rank.SEVEN),
                new Position(File.B, Rank.TWO)
        );
    }

    @Test
    @DisplayName("대각을 제외한 방향으로 이동할 수 없다.")
    void isMovable_false() {
        // given
        final Bishop bishop = new Bishop(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position verticalPosition = new Position(File.A, Rank.FIVE);
        final Position horizontalPosition = new Position(File.E, Rank.THREE);

        // when, then
        assertThat(bishop.isMovable(verticalPosition)).isFalse();
        assertThat(bishop.isMovable(horizontalPosition)).isFalse();
    }

    @Test
    @DisplayName("이동한 경로를 반환한다.")
    void getPaths() {
        // given
        final Bishop bishop = new Bishop(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position targetPosition = new Position(File.E, Rank.SEVEN);
        List<Position> expectedPaths = List.of(
                new Position(File.B, Rank.FOUR),
                new Position(File.C, Rank.FIVE),
                new Position(File.D, Rank.SIX)
        );

        // when
        List<Position> paths = bishop.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
