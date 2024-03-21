package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Knight(Team.WHITE))
                .doesNotThrowAnyException();

    }

    @DisplayName("나이트는 사방중 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직이는 이동 패턴을 가지고 있다.")
    @Test
    void findMovablePath() {
        Coordinate start = new Coordinate(4, 'd');
        Coordinate destination = new Coordinate(3, 'f');
        Knight knight = new Knight(Team.WHITE);

        List<Coordinate> result = knight.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(new Coordinate(3, 'f'));
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("나이트가 목적지로 갈 수 없는 경우, 빈 컬렉션을 반환한다.")
    @Test
    void noPath() {
        Coordinate start = new Coordinate(4, 'd');
        Coordinate destination = new Coordinate(2, 'f');
        Knight knight = new Knight(Team.WHITE);

        List<Coordinate> result = knight.findMovablePath(start, destination);

        assertThat(result).isEmpty();
    }
}
