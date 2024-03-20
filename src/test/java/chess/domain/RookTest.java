package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Rook(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("룩은 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findAllPossibleCoordinate() {
        Coordinate start = new Coordinate(4, 'd');
        Rook rook = new Rook(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(4, 'a'),
                new Coordinate(4, 'b'),
                new Coordinate(4, 'c'),
                new Coordinate(4, 'e'),
                new Coordinate(4, 'f'),
                new Coordinate(4, 'g'),
                new Coordinate(4, 'h'),
                new Coordinate(5, 'd'),
                new Coordinate(6, 'd'),
                new Coordinate(7, 'd'),
                new Coordinate(8, 'd'),
                new Coordinate(3, 'd'),
                new Coordinate(2, 'd'),
                new Coordinate(1, 'd')
        );

        List<Coordinate> result = rook.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("룩이 모서리칸에 존재하는 경우를 검증한다.")
    @Test
    void findAllPossibleCoordinate2() {
        Coordinate start = new Coordinate(1, 'a');
        Rook rook = new Rook(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(8, 'a'),
                new Coordinate(7, 'a'),
                new Coordinate(6, 'a'),
                new Coordinate(5, 'a'),
                new Coordinate(4, 'a'),
                new Coordinate(3, 'a'),
                new Coordinate(2, 'a'),
                new Coordinate(1, 'b'),
                new Coordinate(1, 'c'),
                new Coordinate(1, 'd'),
                new Coordinate(1, 'e'),
                new Coordinate(1, 'f'),
                new Coordinate(1, 'g'),
                new Coordinate(1, 'h')
        );

        List<Coordinate> result = rook.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
