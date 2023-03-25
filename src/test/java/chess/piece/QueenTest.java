package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.type.Queen;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("퀸은 ")
class QueenTest {
    @ParameterizedTest
    @MethodSource("isMovableTrueCase")
    @DisplayName("어떤 방향으로든 칸 수 제한 없이 갈 수 있다.")
    void isMovable_true(Position movablePostion) {
        // given
        final Queen queen = new Queen(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(queen.isMovable(movablePostion)).isTrue();
    }

    static Stream<Position> isMovableTrueCase() {
        return Stream.of(
                new Position(File.D, Rank.SEVEN),
                new Position(File.F, Rank.SIX),
                new Position(File.H, Rank.FOUR),
                new Position(File.G, Rank.ONE),
                new Position(File.D, Rank.THREE),
                new Position(File.B, Rank.TWO),
                new Position(File.A, Rank.FOUR),
                new Position(File.C, Rank.FIVE)
        );
    }

    @Test
    @DisplayName("타겟 위치까지 가는 경로들을 반환한다.")
    void getPaths() {
        // given
        final Queen queen = new Queen(new Position(File.D, Rank.ONE), Side.WHITE);
        final Position targetPosition = new Position(File.H, Rank.FIVE);
        List<Position> expectedPaths = List.of(
                new Position(File.E, Rank.TWO),
                new Position(File.F, Rank.THREE),
                new Position(File.G, Rank.FOUR)
        );

        // when
        List<Position> paths = queen.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
