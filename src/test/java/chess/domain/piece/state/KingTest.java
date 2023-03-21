package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
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
        final Coordinate b1 = Coordinate.of("b1");
        final Coordinate b2 = Coordinate.of("b2");
        final Coordinate b3 = Coordinate.of("b3");
        final Coordinate a2 = Coordinate.of("a2");
        final Coordinate c2 = Coordinate.of("c2");

        //when & then
        Assertions.assertThat(king.findRoute(b2, b3)).containsExactly(Coordinate.of("b3"));
        Assertions.assertThat(king.findRoute(b2, b1)).containsExactly(Coordinate.of("b1"));
        Assertions.assertThat(king.findRoute(b2, a2)).containsExactly(Coordinate.of("a2"));
        Assertions.assertThat(king.findRoute(b2, c2)).containsExactly(Coordinate.of("c2"));
    }

    @Test
    void 킹은_한칸_대각선으로_움직일_수_있다() {
        //given
        final King king = new King(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b2 = Coordinate.of("b2");
        final Coordinate a3 = Coordinate.of("a3");
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate c3 = Coordinate.of("c3");

        //when & then
        Assertions.assertThat(king.findRoute(b2, a1)).containsExactly(Coordinate.of("a1"));
        Assertions.assertThat(king.findRoute(b2, a3)).containsExactly(Coordinate.of("a3"));
        Assertions.assertThat(king.findRoute(b2, c1)).containsExactly(Coordinate.of("c1"));
        Assertions.assertThat(king.findRoute(b2, c3)).containsExactly(Coordinate.of("c3"));
    }


    @Test
    void 킹이_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final King king = new King(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b3 = Coordinate.of("b3");

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
