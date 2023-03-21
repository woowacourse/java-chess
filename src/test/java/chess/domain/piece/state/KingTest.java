package chess.domain.piece.state;

import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {

    @Test
    void 킹은_한칸_위_아래_좌_우로_움직일_수_있다() {
        //given
        final King king = new King(Team.BLACK);
        final SquareCoordinate b1 = SquareCoordinate.of("b1");
        final SquareCoordinate b2 = SquareCoordinate.of("b2");
        final SquareCoordinate b3 = SquareCoordinate.of("b3");
        final SquareCoordinate a2 = SquareCoordinate.of("a2");
        final SquareCoordinate c2 = SquareCoordinate.of("c2");

        //when & then
        Assertions.assertThat(king.findRoute(b2, b3)).containsExactly(SquareCoordinate.of("b3"));
        Assertions.assertThat(king.findRoute(b2, b1)).containsExactly(SquareCoordinate.of("b1"));
        Assertions.assertThat(king.findRoute(b2, a2)).containsExactly(SquareCoordinate.of("a2"));
        Assertions.assertThat(king.findRoute(b2, c2)).containsExactly(SquareCoordinate.of("c2"));
    }

    @Test
    void 킹은_한칸_대각선으로_움직일_수_있다() {
        //given
        final King king = new King(Team.BLACK);
        final SquareCoordinate a1 = SquareCoordinate.of("a1");
        final SquareCoordinate b2 = SquareCoordinate.of("b2");
        final SquareCoordinate a3 = SquareCoordinate.of("a3");
        final SquareCoordinate c1 = SquareCoordinate.of("c1");
        final SquareCoordinate c3 = SquareCoordinate.of("c3");

        //when & then
        Assertions.assertThat(king.findRoute(b2, a1)).containsExactly(SquareCoordinate.of("a1"));
        Assertions.assertThat(king.findRoute(b2, a3)).containsExactly(SquareCoordinate.of("a3"));
        Assertions.assertThat(king.findRoute(b2, c1)).containsExactly(SquareCoordinate.of("c1"));
        Assertions.assertThat(king.findRoute(b2, c3)).containsExactly(SquareCoordinate.of("c3"));
    }


    @Test
    void 킹이_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final King king = new King(Team.BLACK);
        final SquareCoordinate a1 = SquareCoordinate.of("a1");
        final SquareCoordinate b3 = SquareCoordinate.of("b3");

        //when & then
        Assertions.assertThatThrownBy(() -> king.findRoute(a1, b3)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 킹은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final King king = new King(team);
        final List<PieceState> route = List.of(new Pawn(team));

        //when & then
        Assertions.assertThatThrownBy(() -> king.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 킹은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.BLACK;
        final King king = new King(team);
        final List<PieceState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> king.validateRoute(route));
    }

    @Test
    void 킹은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final King king = new King(Team.BLACK);
        final List<PieceState> route = List.of(new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> king.validateRoute(route));
    }
}
