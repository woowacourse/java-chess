package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.File.A;
import static chess.domain.File.B;
import static chess.domain.File.C;
import static chess.domain.File.D;
import static chess.domain.File.E;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.FIVE;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    @Test
    @DisplayName("지나갈 경로를 얻는다.")
    void getPassingPathTest() {
        final Piece queen = new Queen(D, EIGHT, Color.BLACK);

        final List<Position> path = queen.getPassingPositions(new Position(A, FIVE));

        assertThat(path).containsExactly(new Position(C, SEVEN), new Position(B, SIX));
    }

    @Test
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void getPassingPathFailTest() {
        final Piece queen = new Queen(D, EIGHT, Color.BLACK);

        assertThatThrownBy(() -> queen.getPassingPositions(new Position(E, FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }
}
