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
class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"B:FIVE:true", "D:THREE:true", "E:SIX:false", "E:FOUR:false"}, delimiter = ':')
    void 킹은_상하좌우_대각선으로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        King king = new King(Color.WHITE);

        assertThat(king.canMove(C_4, new Position(fileCoordinate, rankCoordinate), Color.EMPTY)).isEqualTo(expect);
    }

    @Test
    void 킹의_RoleType은_KING이다() {
        King king = new King(Color.WHITE);

        assertThat(king.isSameRoleType(RoleType.KING)).isTrue();
    }
}
