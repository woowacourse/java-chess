package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.File.C;
import static chess.domain.File.D;
import static chess.domain.File.E;
import static chess.domain.File.F;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.FIVE;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @Test
    @DisplayName("지나갈 경로를 얻는다.")
    void getPassingPathTest() {
        final Piece bishop = new Bishop(C, EIGHT, Color.BLACK);

        final List<Position> path = bishop.getPassingPositions(new Position(F, FIVE));

        assertThat(path).containsExactly(new Position(D, SEVEN), new Position(E, SIX));
    }

    @Test
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void getPassingPathFailTest() {
        final Piece bishop = new Bishop(C, EIGHT, Color.BLACK);

        assertThatThrownBy(() -> bishop.getPassingPositions(new Position(E, FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("말을 이동시킨다.")
    void moveTest() {
        final Piece originalBishop = new Bishop(F, EIGHT, BLACK);

        final Piece movedBishop = originalBishop.move(new BlankPiece(D, SIX));

        assertThat(movedBishop.getPosition()).isEqualTo(new Position(D, SIX));
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void throws_exception_if_there_is_same_color_piece_in_target_position() {
        final Piece originalBishop = new Bishop(F, EIGHT, BLACK);
        final Piece sameColorPiece = new Pawn(D, SIX, BLACK);

        assertThatThrownBy(() -> originalBishop.move(sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }
}
