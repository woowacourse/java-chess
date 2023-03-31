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
class RookTest {
    private static final Rook ROOK_BLACK = new Rook(Team.BLACK);

    @Test
    void 룩이_갈_수_없는_좌표이면_예외가_발생한다() {
        //when & then
        Assertions.assertThatThrownBy(() -> ROOK_BLACK.findRoute(A1, B3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_같은_파일의_좌표로_움직일_수_있다() {
        //when & then
        Assertions.assertThat(ROOK_BLACK.findRoute(A1, A3)).containsExactly(A2, A3);
    }


    @Test
    void 룩은_같은_랭크의_좌표로_움직일_수_있다() {
        //when & then
        Assertions.assertThat(ROOK_BLACK.findRoute(A1, E1)).containsExactly(B1, C1, D1, E1);
    }

    @Test
    void 룩은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final List<SquareState> route = List.of(new Empty(), new Empty(), new Pawn(team));

        //when & then
        Assertions.assertThatThrownBy(() -> ROOK_BLACK.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_도착지를_가는_중간에_다른_기물이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final List<SquareState> route = List.of(new Empty(), new Pawn(team), new Empty());

        //when & then
        Assertions.assertThatThrownBy(() -> ROOK_BLACK.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 룩은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Empty(), new Empty(), new Empty());

        //when & then
        assertDoesNotThrow(() -> ROOK_BLACK.validateRoute(route));
    }

    @Test
    void 룩은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Empty(), new Empty(),
                new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> ROOK_BLACK.validateRoute(route));
    }
}
