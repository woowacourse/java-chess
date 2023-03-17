package chess.domain.chessboard.state.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Test
    void 폰은_직진할_떄_다른팀이_있으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate c2 = Coordinate.of("c2");
        final List<Square> route = List.of(new Square(new Pawn(Team.BLACK)));
        pawn.findRoute(c1, c2);

        //when & then
        assertThatThrownBy(() -> pawn.canMove(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_직진할_떄_다른팀이_없으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate c2 = Coordinate.of("c2");
        final List<Square> route = List.of(new Square());
        pawn.findRoute(c1, c2);

        //when & then
        assertDoesNotThrow(() -> pawn.canMove(route));
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_없으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<Square> route = List.of(new Square());
        pawn.findRoute(c1, b2);

        //when & then
        assertThatThrownBy(() -> pawn.canMove(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_있으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<Square> route = List.of(new Square(new Pawn(Team.BLACK)));
        pawn.findRoute(c1, b2);

        //when & then
        assertDoesNotThrow(() -> pawn.canMove(route));
    }

    @Test
    void 폰은_한번_움직인_후_두칸_직진하면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate b1 = Coordinate.of("b1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<Square> route1 = List.of(new Square());

        final Coordinate b4 = Coordinate.of("b4");
        final List<Square> route2 = List.of(new Square(), new Square());

        //when
        pawn.findRoute(b1, b2);
        pawn.canMove(route1);

        //then
        assertThatThrownBy(() -> pawn.findRoute(b2, b4)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 폰는_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<Square> route = List.of(new Square(new Pawn(team)));

        //when & then
        assertThatThrownBy(() -> pawn.canMove(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰는_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<Square> route = List.of(new Square());

        //when & then
        assertDoesNotThrow(() -> pawn.canMove(route));
    }
}
