package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Test
    void 폰은_직진할_떄_다른팀이_있으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate c2 = Coordinate.of("c2");
        final List<PieceState> route = List.of(new Pawn(Team.BLACK));
        pawn.findRoute(c1, c2);

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_직진할_떄_다른팀이_없으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate c2 = Coordinate.of("c2");
        final List<PieceState> route = List.of(new Empty());
        pawn.findRoute(c1, c2);

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_없으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<PieceState> route = List.of(new Empty());
        pawn.findRoute(c1, b2);

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_있으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate c1 = Coordinate.of("c1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<PieceState> route = List.of(new Pawn(Team.BLACK));
        pawn.findRoute(c1, b2);

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }

    @Test
    void 폰은_한번_움직인_후_두칸_직진하면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final Coordinate b1 = Coordinate.of("b1");
        final Coordinate b2 = Coordinate.of("b2");
        final List<PieceState> route1 = List.of(new Empty());

        final Coordinate b4 = Coordinate.of("b4");

        //when
        pawn.findRoute(b1, b2);
        pawn.validateRoute(route1);

        //then
        assertThatThrownBy(() -> pawn.findRoute(b2, b4)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 폰는_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<PieceState> route = List.of(new Pawn(team));

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰는_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<PieceState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }
}
