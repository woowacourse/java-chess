package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest(name = "퀸이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @CsvSource(value = {"0:1", "0:7", "1:0", "7:0", "1:1", "7:7"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Queen(PieceType.QUEEN, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(new Position(0, 0),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 퀸이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"1:2", "0:8", "-8:0", "8:0", "0:-8", "2:1", "-1:2", "1:-2", "8:8"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Queen(PieceType.QUEEN, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(new Position(0, 0),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
