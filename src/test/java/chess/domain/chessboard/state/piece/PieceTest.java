package chess.domain.chessboard.state.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Rank;
import chess.domain.chessboard.state.Team;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTest {

    @Test
    void 폰은_팀을_가진다() {
        //given
        final Team team = Team.BLACK;

        //then
        assertDoesNotThrow(() -> new Pawn(team));

        assertThat(new Pawn(team))
                .extracting("team")
                .isEqualTo(team);
    }
}
