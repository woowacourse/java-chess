package chess.domain.piece;

import chess.domain.piece.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("source의 rank 위치보다 target의 rank 위치가 더 클 때 위로 이동할 수 있으면 true를 반환한다.")
    void canMoveSuccessUp() {
        // given
        final Pawn pawn = new Pawn();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawn.canMove(source, new Position(2, 1));

        // then
        assertThat(actual)
                .isTrue();
    }

    @Test
    @DisplayName("target의 rank 위치보다 source의 rank 위치가 더 클 때 아래로 이동할 수 있으면 true를 반환한다.")
    void canMoveSuccessDown() {
        // given
        final Pawn pawn = new Pawn();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawn.canMove(source, new Position(0, 1));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 폰이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"0:8", "-8:0", "8:0", "0:-8", "1:2", "1:0"}, delimiter = ':')
    void canMoveFail(final int targetRank, final int targetFile) {
        // given
        final Pawn pawn = new Pawn();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawn.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }

    @ParameterizedTest(name = "source에서 target으로 폰이 공격할 수 있는 위치인지 판단한다.")
    @CsvSource(value = {"2:2:true", "1:2:false", "0:2:true", "1:3:false"}, delimiter = ':')
    void canAttack(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final Pawn pawn = new Pawn();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = pawn.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
