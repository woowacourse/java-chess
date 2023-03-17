package chess.domain.chessboard.state.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.Team;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BishopTest {

    @Test
    void 비숍이_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b3 = Coordinate.of("b3");

        //when & then
        Assertions.assertThatThrownBy(() -> bishop.findRoute(a1, b3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비숍은_우상향_대각선의_좌표로_움직일_수_있다() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate c3 = Coordinate.of("c3");

        //when & then
        Assertions.assertThat(bishop.findRoute(a1, c3)).containsExactly(Coordinate.of("b2"), Coordinate.of("c3"));
    }


    @Test
    void 비숍은_좌상향_대각선의_좌표로_움직일_수_있다() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        final Coordinate c3 = Coordinate.of("c3");
        final Coordinate e1 = Coordinate.of("e1");

        //when & then
        Assertions.assertThat(bishop.findRoute(c3, e1)).containsExactly(Coordinate.of("d2"), Coordinate.of("e1"));
    }

    @Test
    void 비숍은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Bishop bishop = new Bishop(team);
        final List<Square> route = List.of(new Square(), new Square(), new Square(new Pawn(team)));

        //when & then
        Assertions.assertThatThrownBy(() -> bishop.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비숍은_도착지를_가는_중간에_다른_기물이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Bishop bishop = new Bishop(team);
        final List<Square> route = List.of(new Square(), new Square(new Pawn(team)), new Square());

        //when & then
        Assertions.assertThatThrownBy(() -> bishop.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비숍은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.BLACK;
        final Bishop bishop = new Bishop(team);
        final List<Square> route = List.of(new Square(), new Square(), new Square());

        //when & then
        assertDoesNotThrow(() -> bishop.validateRoute(route));
    }

    @Test
    void 비숍은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final Bishop bishop = new Bishop(Team.BLACK);
        final List<Square> route = List.of(new Square(), new Square(),
                new Square(new Pawn(Team.WHITE)));

        //when & then
        assertDoesNotThrow(() -> bishop.validateRoute(route));
    }
}
