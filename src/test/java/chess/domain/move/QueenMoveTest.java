package chess.domain.move;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenMoveTest {

    @ParameterizedTest(name = "source에서 target으로 퀸이 이동할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"0:1", "0:7", "1:0", "7:0", "1:1", "7:7"}, delimiter = ':')
    void canMoveSuccess(final int rank, final int file) {
        // given
        final QueenMove queenMove = new QueenMove();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = queenMove.canMove(source, new Position(sourceRank + rank, sourceFile + file));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 이동하는 방향이 퀸이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"1:2", "0:8", "-8:0", "8:0", "0:-8", "2:1", "-1:2", "1:-2", "8:8"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int rank, final int file) {
        // given
        final QueenMove queenMove = new QueenMove();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = queenMove.canMove(source, new Position(sourceRank + rank, sourceFile + file));

        // then
        assertThat(actual)
                .isFalse();
    }
}
