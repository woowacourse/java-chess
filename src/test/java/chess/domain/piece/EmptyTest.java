package chess.domain.piece;

import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.Rook;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.SquareCoordinates.A1;
import static chess.domain.SquareCoordinates.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyTest {

    @Test
    void 빈칸은_항상_비어있다() {
        assertThat(new Empty().isEmpty()).isTrue();
    }

    @Test
    void 빈칸의_팀을_물으면_예외가_발생한다() {
        //given
        final Empty empty = new Empty();

        //when & then
        assertThatThrownBy(empty::getTeam).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 빈칸의_경로에_관해_물으면_예외가_발생한다() {
        //given
        final Empty empty = new Empty();

        //when & then
        assertThatThrownBy(() -> empty.findRoute(A1, A2)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> empty.validateRoute(List.of(new Empty()))).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 빈칸은_항상_다른팀이다() {
        //given
        final Rook rook = new Rook(Team.WHITE);
        final Bishop bishop = new Bishop(Team.BLACK);

        //when & then
        assertThat(new Empty().isSameTeam(rook)).isFalse();
        assertThat(new Empty().isSameTeam(bishop)).isFalse();
    }
}
