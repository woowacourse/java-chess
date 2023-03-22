package chess.domain.piece;

import chess.domain.piece.move.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest(name = "source에서 target으로 퀸이 이동할 수 있는지 판단한다.")
    @CsvSource(value = {"0:1:true", "0:7:true", "1:0:true", "7:0:true", "1:1:true", "7:7:true",
            "1:2:false", "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "2:1:false",
            "-1:2:false", "1:-2:false", "8:8:false"}, delimiter = ':')
    void canMove(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final Queen queen = new Queen();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = queen.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "source에서 target으로 퀸이 공격할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"0:1:true", "0:7:true", "1:0:true", "7:0:true", "1:1:true", "7:7:true",
            "1:2:false", "0:8:false", "-8:0:false", "8:0:false", "0:-8:false", "2:1:false",
            "-1:2:false", "1:-2:false", "8:8:false"}, delimiter = ':')
    void canAttack(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final Queen queen = new Queen();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = queen.canAttack(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
