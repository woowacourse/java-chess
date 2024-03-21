package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Bishop(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("비숍은 대각으로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        Coordinate start = new Coordinate(4, 'd');
        Coordinate destination = new Coordinate(7, 'g');
        Bishop bishop = new Bishop(Team.WHITE);

        List<Coordinate> result = bishop.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(5, 'e'),
                new Coordinate(6, 'f'),
                new Coordinate(7, 'g'),
                new Coordinate(8, 'h')
        );
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("비숍이 목적지로 갈 수 없는 경우, 빈 컬렉션을 반환한다.")
    @Test
    void noPath() {
        Coordinate start = new Coordinate(1, 'a');
        Coordinate destination = new Coordinate(7, 'a');
        Bishop bishop = new Bishop(Team.WHITE);

        List<Coordinate> result = bishop.findMovablePath(start, destination);

        assertThat(result).isEmpty();
    }
}
