package chess.domain.chessboard;

import chess.domain.piece.Team;
import chess.domain.piece.state.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SquareTest {

    @Test
    void 스퀘어는_비어있는_상태를_가질_수_있다() {
        //given
        Square square = new Square();

        //when&then
        Assertions.assertThat(square.isEmpty()).isTrue();
    }

    @Test
    void 스퀘어는_말을_가진_상태일_수_있다() {
        //given
        final Square square = new Square(new Pawn(Team.BLACK));

        //when&then
        Assertions.assertThat(square.isEmpty()).isFalse();
    }
}
