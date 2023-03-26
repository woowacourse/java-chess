package chess.domain.piece;

import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.state.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 말은_팀을_가진다() {
        //given
        final Team team = Team.BLACK;

        //then
        assertDoesNotThrow(() -> new Pawn(team));

        assertThat(new Pawn(team).isSameTeam(team)).isTrue();
    }

    @Test
    void 말은_다른_말을_받아서_같은_팀인지_검사할_수_있다() {
        //given
        final Rook rook = new Rook(Team.WHITE);
        final Bishop bishop = new Bishop(Team.BLACK);

        //when & then
        Assertions.assertThat(rook.isSameTeam(bishop)).isFalse();
    }
}
