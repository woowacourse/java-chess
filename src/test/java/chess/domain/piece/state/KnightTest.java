package chess.domain.piece.state;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.SquareCoordinates.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightTest {
    private static final Knight KNIGHT_BLACK = new Knight(Team.BLACK);

    @Test
    void 나이트는_같은_랭크의_좌표로_움직일_수_있다() {
        //when & then
        Assertions.assertThat(KNIGHT_BLACK.findRoute(C3, A4)).containsExactly(A4);
        Assertions.assertThat(KNIGHT_BLACK.findRoute(C3, B1)).containsExactly(B1);
        Assertions.assertThat(KNIGHT_BLACK.findRoute(C3, D5)).containsExactly(D5);
        Assertions.assertThat(KNIGHT_BLACK.findRoute(C3, E2)).containsExactly(E2);
    }

    @Test
    void 나이트가_갈_수_없는_좌표이면_예외가_발생한다() {
        //when & then
        Assertions.assertThatThrownBy(() -> KNIGHT_BLACK.findRoute(B1, B7)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 나이트는_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final List<SquareState> route = List.of(new Pawn(team));

        //when & then
        Assertions.assertThatThrownBy(() -> KNIGHT_BLACK.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 나이트는_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> KNIGHT_BLACK.validateRoute(route));
    }

    @Test
    void 나이트는_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> KNIGHT_BLACK.validateRoute(route));
    }
}
