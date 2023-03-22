package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @ParameterizedTest(name = "폰이 시작 위치에서 타겟 위치로 이동 가능하면 true를 반환한다")
    @CsvSource(value = {"2:1:WHITE", "0:1:BLACK"}, delimiter = ':')
    void canMove(final int targetRank, final int targetFile, final TeamColor campType) {
        // given
        final Piece piece = new Pawn(PieceType.PAWN, campType);

        // when
        boolean actual = piece.canMove(new Position(1, 1),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "폰이 시작 위치에서 타겟 위치로 이동 불가능하면 false를 반환한다")
    @CsvSource(value = {"2:2", "0:8", "-8:0", "8:0", "0:-8", "1:2", "1:0"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Piece piece = new Pawn(PieceType.PAWN, TeamColor.WHITE);

        // when
        boolean actual = piece.canMove(new Position(1, 1),
                new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
