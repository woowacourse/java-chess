package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BishopMoveTest {

    @ParameterizedTest(name = "source에서 target으로 비숍이 이동할 수 있는지 판단한다.")
    @CsvSource(value = {"1:1:true", "7:7:true", "1:3:true", "3:1:true", "3:3:true",
            "1:2:false", "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "0:1:false",
            "1:0:false", "8:8:false"}, delimiter = ':')
    void canMove(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final BishopMove bishopMove = new BishopMove();
        final int sourceRank = 2, sourceFile = 2;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = bishopMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "source에서 target으로 비숍이 공격할 수 있는지 판단한다.")
    @CsvSource(value = {"1:1:true", "7:7:true", "1:3:true", "3:1:true", "3:3:true",
            "1:2:false", "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "0:1:false",
            "1:0:false", "8:8:false"}, delimiter = ':')
    void canAttack(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final BishopMove bishopMove = new BishopMove();
        final int sourceRank = 2, sourceFile = 2;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = bishopMove.canAttack(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
