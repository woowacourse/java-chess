package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookMoveTest {

    @ParameterizedTest(name = "source에서 target으로 룩이 이동할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"0:1", "1:0", "2:1", "1:2", "7:1", "1:7"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final RookMove rookMove = new RookMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = rookMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 룩이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"1:2", "0:8", "-8:0", "8:0", "0:-8", "1:1", "-1:-1", "8:8"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int targetRank, final int targetFile) {
        // given
        final RookMove rookMove = new RookMove();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = rookMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
