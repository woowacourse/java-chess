package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.Coordinate;
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
    void findAllPossibleCoordinate() {
        Coordinate start = new Coordinate(4, 'd');
        Bishop bishop = new Bishop(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(1, 'a'),
                new Coordinate(2, 'b'),
                new Coordinate(3, 'c'),
                new Coordinate(3, 'e'),
                new Coordinate(2, 'f'),
                new Coordinate(1, 'g'),
                new Coordinate(5, 'e'),
                new Coordinate(6, 'f'),
                new Coordinate(7, 'g'),
                new Coordinate(8, 'h'),
                new Coordinate(5, 'c'),
                new Coordinate(6, 'b'),
                new Coordinate(7, 'a')
        );

        List<Coordinate> result = bishop.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("비숍이 모서리칸에 존재하는 경우를 검증한다.")
    @Test
    void findAllPossibleCoordinate2() {
        Coordinate start = new Coordinate(1, 'a');
        Bishop bishop = new Bishop(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(2, 'b'),
                new Coordinate(3, 'c'),
                new Coordinate(4, 'd'),
                new Coordinate(5, 'e'),
                new Coordinate(6, 'f'),
                new Coordinate(7, 'g'),
                new Coordinate(8, 'h')
        );

        List<Coordinate> result = bishop.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
