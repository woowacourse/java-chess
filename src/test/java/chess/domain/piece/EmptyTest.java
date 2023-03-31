package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class EmptyTest {

    @Test
    void Empty인지_알_수_있다() {
        final var piece = Empty.create(C_4);
        assertThat(piece.isEmpty()).isTrue();
    }

    @Test
    void Empty는_움직일_수_없다_move() {
        assertThatThrownBy(() -> Empty.create(C_4).move(new Position(FileCoordinate.A, RankCoordinate.TWO), Team.WHITE, Team.EMPTY))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("빈칸은 움직일 수 없습니다.");
    }

    @Test
    void Empty는_움직일_수_없다_canMove() {
        assertThatThrownBy(() -> Empty.create(C_4).canMove(new Position(FileCoordinate.A, RankCoordinate.TWO), Team.EMPTY))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("빈칸은 움직일 수 없습니다.");
    }
}
