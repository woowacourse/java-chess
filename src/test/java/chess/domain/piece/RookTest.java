package chess.domain.piece;

import static chess.domain.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.FileCoordinate;
import chess.domain.position.Position;
import chess.domain.position.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"B:SIX:false", "A:FOUR:true", "C:SIX:true", "B:FOUR:true"}, delimiter = ':')
    void 룩은_상하좌우로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Rook rook = new Rook(Color.WHITE);

        assertThat(rook.canMove(C_4, new Position(fileCoordinate, rankCoordinate), Color.EMPTY)).isEqualTo(expect);
    }

    @Test
    void 룩의_RoleType은_ROOK이다() {
        Rook rook = new Rook(Color.WHITE);

        assertThat(rook.isSameRoleType(RoleType.ROOK)).isTrue();
    }
}
