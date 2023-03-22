package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest(name = "비숍이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @CsvSource(value = {"1:1", "7:7", "1:3", "3:1", "3:3"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Bishop(PieceType.BISHOP, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(new Position(2, 2),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "비숍이 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @CsvSource(value = {"1:2", "0:8", "-8:0", "8:0", "0:-8", "0:1", "1:0", "8:8"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Bishop(PieceType.BISHOP, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(new Position(0, 0),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
