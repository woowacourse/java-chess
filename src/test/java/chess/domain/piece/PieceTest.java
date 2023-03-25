package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.state.Rook;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 말은_팀을_가진다() {
        //given
        final Team team = Team.BLACK;

        //then
        assertDoesNotThrow(() -> new Pawn(team));

        assertThat(new Pawn(team))
                .extracting("team")
                .isEqualTo(team);
    }

    @Test
    void 말은_다른_말을_받아서_같은_팀인지_검사할_수_있다() {
        //given
        final Rook rook = new Rook(Team.WHITE);
        final Bishop bishop = new Bishop(Team.BLACK);

        //when & then
        assertThat(rook.isSameTeam(bishop)).isFalse();
    }
}
