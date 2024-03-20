package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.Coordinate;
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
    void findAllPossibleCoordinate() {
        Coordinate start = new Coordinate(4, 'd');
        Knight knight = new Knight(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(6, 'c'),
                new Coordinate(5, 'b'),
                new Coordinate(3, 'b'),
                new Coordinate(2, 'c'),
                new Coordinate(3, 'f'),
                new Coordinate(5, 'f'),
                new Coordinate(6, 'e'),
                new Coordinate(2, 'e')
        );

        List<Coordinate> result = knight.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("나이트가 모서리칸에 존재하는 경우를 검증한다.")
    @Test
    void findAllPossibleCoordinate2() {
        Coordinate start = new Coordinate(1, 'a');
        Knight knight = new Knight(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(3, 'b'),
                new Coordinate(2, 'c')
        );

        List<Coordinate> result = knight.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
