package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnMoveTest {

    @Test
    @DisplayName("source의 rank 위치보다 target의 rank 위치가 더 클 때 위로 이동할 수 있으면 true를 반환한다.")
    void canMoveSuccessUp() {
        // given
        final PawnMove pawnMove = new PawnMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawnMove.canMove(source, new Position(2, 1));

        // then
        assertThat(actual)
                .isTrue();
    }

    @Test
    @DisplayName("target의 rank 위치보다 source의 rank 위치가 더 클 때 아래로 이동할 수 있으면 true를 반환한다.")
    void canMoveSuccessDown() {
        // given
        final PawnMove pawnMove = new PawnMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawnMove.canMove(source, new Position(0, 1));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 폰이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"0:8", "-8:0", "8:0", "0:-8", "1:2", "1:0"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int targetRank, final int targetFile) {
        // given
        final PawnMove pawnMove = new PawnMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawnMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
