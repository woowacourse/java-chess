package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveTestController {

    @ParameterizedTest(name = "source에서 target으로 나이트가 이동할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"3:6", "7:6", "3:4", "7:4"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final KnightMove knightMove = new KnightMove();
        final int sourceRank = 5, sourceFile = 5;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = knightMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 나이트가 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"0:8", "-8:0", "8:0", "0:-8", "8:8", "4:5", "5:7", "3:3", "0:0"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int targetRank, final int targetFile) {
        // given
        final KnightMove knightMove = new KnightMove();
        final int sourceRank = 5, sourceFile = 5;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = knightMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
