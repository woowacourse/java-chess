package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest(name = "킹이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @CsvSource(value = {"0:1", "1:0", "1:2", "2:1", "0:2", "0:0", "2:0", "2:2"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new King(PieceType.KING, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(targetRank, targetFile), null);

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "킹이 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @CsvSource(value = {"1:3", "0:7", "-7:0", "7:0", "0:-7", "3:1", "-1:1", "1:-2", "7:7"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new King(PieceType.KING, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(Position.of(1, 1),
                Position.of(targetRank, targetFile), null);

        // then
        assertThat(actual)
                .isFalse();
    }
}
