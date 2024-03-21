package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Queen(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("퀸은 대각과 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        Coordinate start = new Coordinate(4, 'd');
        Coordinate destination = new Coordinate(7, 'a');
        Queen queen = new Queen(Team.WHITE);

        List<Coordinate> result = queen.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(5, 'c'),
                new Coordinate(6, 'b'),
                new Coordinate(7, 'a'));
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("퀸이 목적지로 갈 수 없는 경우, 빈 컬렉션을 반환한다.")
    @Test
    void noPath() {
        Coordinate start = new Coordinate(4, 'd');
        Coordinate destination = new Coordinate(6, 'e');
        Queen queen = new Queen(Team.WHITE);

        List<Coordinate> result = queen.findMovablePath(start, destination);

        assertThat(result).isEmpty();
    }
}
