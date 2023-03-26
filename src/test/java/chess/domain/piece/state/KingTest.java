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
class KingTest {

    public static final King KING_BLACK = new King(Team.BLACK);

    @Test
    void 킹은_한칸_위_아래_좌_우로_움직일_수_있다() {
        //when & then
        Assertions.assertThat(KING_BLACK.findRoute(B2, B3)).containsExactly(B3);
        Assertions.assertThat(KING_BLACK.findRoute(B2, B1)).containsExactly(B1);
        Assertions.assertThat(KING_BLACK.findRoute(B2, A2)).containsExactly(A2);
        Assertions.assertThat(KING_BLACK.findRoute(B2, C2)).containsExactly(C2);
    }

    @Test
    void 킹은_한칸_대각선으로_움직일_수_있다() {
        //when & then
        Assertions.assertThat(KING_BLACK.findRoute(B2, A1)).containsExactly(A1);
        Assertions.assertThat(KING_BLACK.findRoute(B2, A3)).containsExactly(A3);
        Assertions.assertThat(KING_BLACK.findRoute(B2, C1)).containsExactly(C1);
        Assertions.assertThat(KING_BLACK.findRoute(B2, C3)).containsExactly(C3);
    }


    @Test
    void 킹이_갈_수_없는_좌표이면_예외가_발생한다() {
        //when & then
        Assertions.assertThatThrownBy(() -> KING_BLACK.findRoute(A1, B3)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 킹은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final List<SquareState> route = List.of(new Pawn(Team.BLACK));

        //when & then
        Assertions.assertThatThrownBy(() -> KING_BLACK.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 킹은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> KING_BLACK.validateRoute(route));
    }

    @Test
    void 킹은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final List<SquareState> route = List.of(new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> KING_BLACK.validateRoute(route));
    }
}
