package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest(name = "나이트가 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @CsvSource(value = {"3:6", "7:6", "3:4", "7:4"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Knight(PieceType.KNIGHT, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(Position.of(5, 5),
                Position.of(targetRank, targetFile), null);

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "나이트가 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @CsvSource(value = {"0:7", "-7:0", "7:0", "0:-7", "7:7", "4:5", "5:7", "3:3", "0:0"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Knight(PieceType.KNIGHT, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(Position.of(5, 5),
                Position.of(targetRank, targetFile), null);

        // then
        assertThat(actual)
                .isFalse();
    }
}
