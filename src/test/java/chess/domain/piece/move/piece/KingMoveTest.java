package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingMoveTest {

    @ParameterizedTest(name = "source에서 target으로 킹이 이동할 수 있는지 판단한다.")
    @CsvSource(value = {"0:1:true", "1:0:true", "1:2:true", "2:1:true", "0:2:true",
            "0:0:true", "2:0:true", "2:2:true", "1:3:false", "0:8:false", "-8:0:false",
            "8:0:false", "0:-8:false", "3:1:false", "-1:1:false",
            "1:-2:false", "8:8:false"}, delimiter = ':')
    void canMove(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final KingMove kingMove = new KingMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = kingMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "source에서 target으로 킹이 공격할 수 있는지 판단한다.")
    @CsvSource(value = {"0:1:true", "1:0:true", "1:2:true", "2:1:true", "0:2:true", "0:0:true",
            "2:0:true", "2:2:true", "1:3:false", "0:8:false", "-8:0:false", "8:0:false", "0:-8:false",
            "3:1:false", "-1:1:false", "1:-2:false", "8:8:false"}, delimiter = ':')
    void canAttack(final int targetRank, final int targetFile, final boolean expected) {
        // given
        final KingMove kingMove = new KingMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = kingMove.canAttack(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
