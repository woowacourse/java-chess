package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.File.E;
import static chess.domain.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("지나갈 경로를 얻는다.")
    void getPassingPathTest() {
        final Piece king = new King(E, Rank.EIGHT, Color.BLACK);

        final List<Position> path = king.getPassingPositions(new Position(E, Rank.SEVEN));

        assertThat(path).isEmpty();
    }

    @Test
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void getPassingPathFailTest() {
        final Piece king = new King(E, Rank.EIGHT, Color.BLACK);

        assertThatThrownBy(() -> king.getPassingPositions(new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }
}
