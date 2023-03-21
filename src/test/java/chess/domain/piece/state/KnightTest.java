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
class KnightTest {

    @Test
    void 나이트는_같은_랭크의_좌표로_움직일_수_있다() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        final Coordinate c3 = Coordinate.of("c3");
        final Coordinate a4 = Coordinate.of("a4");
        final Coordinate b1 = Coordinate.of("b1");
        final Coordinate d5 = Coordinate.of("d5");
        final Coordinate e2 = Coordinate.of("e2");

        //when & then
        Assertions.assertThat(knight.findRoute(c3, a4)).containsExactly(Coordinate.of("a4"));
        Assertions.assertThat(knight.findRoute(c3, b1)).containsExactly(Coordinate.of("b1"));
        Assertions.assertThat(knight.findRoute(c3, d5)).containsExactly(Coordinate.of("d5"));
        Assertions.assertThat(knight.findRoute(c3, e2)).containsExactly(Coordinate.of("e2"));

    }

    @Test
    void 나이트가_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        final Coordinate a1 = Coordinate.of("a1");
        final Coordinate b8 = Coordinate.of("b8");

        //when & then
        Assertions.assertThatThrownBy(() -> knight.findRoute(a1, b8)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 나이트는_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Knight knight = new Knight(team);
        final List<PieceState> route = List.of(new Pawn(team));

        //when & then
        Assertions.assertThatThrownBy(() -> knight.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 나이트는_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.BLACK;
        final Knight knight = new Knight(team);
        final List<PieceState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> knight.validateRoute(route));
    }

    @Test
    void 나이트는_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final Knight knight = new Knight(Team.BLACK);
        final List<PieceState> route = List.of(new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> knight.validateRoute(route));
    }
}
