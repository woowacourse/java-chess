package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveTest {

    @ParameterizedTest(name = "source에서 target으로 나이트가 이동할 수 있는지 판단한다.")
    @CsvSource(value = {"3:6:true", "7:6:true", "3:4:true", "7:4:true",
            "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "8:8:false", "4:5:false",
            "5:7:false", "3:3:false", "0:0:false"}, delimiter = ':')
    void canMove(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final KnightMove knightMove = new KnightMove();
        final int sourceRank = 5, sourceFile = 5;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = knightMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "source에서 target으로 나이트가 공격할 수 있는지 판단한다.")
    @CsvSource(value = {"3:6:true", "7:6:true", "3:4:true", "7:4:true",
            "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "8:8:false", "4:5:false",
            "5:7:false", "3:3:false", "0:0:false"}, delimiter = ':')
    void canAttack(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final KnightMove knightMove = new KnightMove();
        final int sourceRank = 5, sourceFile = 5;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = knightMove.canAttack(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
