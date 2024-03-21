package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new King(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("킹은 가로, 세로 및 대각선으로도 1칸씩 움직일 수 있다.")
    @Test
    void findMovablePath() {
        Coordinate start = new Coordinate(4, 'e');
        Coordinate destination = new Coordinate(5, 'e');
        King king = new King(Team.WHITE);

        List<Coordinate> result = king.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(new Coordinate(5, 'e'));
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("킹이 목적지로 갈 수 없는 경우, 빈 컬렉션을 반환한다.")
    @Test
    void noPath() {
        Coordinate start = new Coordinate(1, 'a');
        Coordinate destination = new Coordinate(3, 'a');
        King king = new King(Team.WHITE);

        List<Coordinate> result = king.findMovablePath(start, destination);

        assertThat(result).isEmpty();
    }
}
