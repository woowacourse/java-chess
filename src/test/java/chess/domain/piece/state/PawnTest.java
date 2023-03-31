package chess.domain.piece.state;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.SquareCoordinates.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Test
    void 흰색_폰은_양수방향으로_전진할_수_있다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);

        //when & then
        assertDoesNotThrow(() -> pawn.findRoute(C1, C2));
    }

    @Test
    void 흰색_폰은_음수방향으로_전진할_수_없다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);

        //when & then
        assertThatThrownBy(() -> pawn.findRoute(C2, C1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 검은색_폰은_음수방향으로_전진할_수_있다() {
        // given
        final Team team = Team.BLACK;
        final Pawn pawn = new Pawn(team);

        //when & then
        assertDoesNotThrow(() -> pawn.findRoute(C2, C1));
    }

    @Test
    void 검은색_폰은_양수방향으로_전진할_수_없다() {
        // given
        final Team team = Team.BLACK;
        final Pawn pawn = new Pawn(team);

        //when & then
        assertThatThrownBy(() -> pawn.findRoute(C1, C2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_직진할_떄_다른팀이_있으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Pawn(Team.BLACK));
        pawn.findRoute(C1, C2);

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_직진할_떄_다른팀이_없으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Empty());
        pawn.findRoute(C1, C2);

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_없으면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Empty());
        pawn.findRoute(C1, B2);

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰은_대각선으로갈_떄_다른팀이_있으면_예외가_발생하지않는다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Pawn(Team.BLACK));
        pawn.findRoute(C1, B2);

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }

    @Test
    void 폰은_한번_움직인_후_두칸_직진하면_예외가_발생한다() {
        // given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route1 = List.of(new Empty());

        //when
        pawn.findRoute(B1, B2);
        pawn.validateRoute(route1);

        //then
        assertThatThrownBy(() -> pawn.findRoute(B2, B4)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 폰는_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Pawn(team));

        //when & then
        assertThatThrownBy(() -> pawn.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰는_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.WHITE;
        final Pawn pawn = new Pawn(team);
        final List<SquareState> route = List.of(new Empty());

        //when & then
        assertDoesNotThrow(() -> pawn.validateRoute(route));
    }
}
