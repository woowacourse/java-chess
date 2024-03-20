package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.piece.King;
import chess.domain.piece.Team;
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
    void findAllPossibleCoordinate() {
        Coordinate start = new Coordinate(4, 'e');
        King king = new King(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(5, 'd'),
                new Coordinate(5, 'e'),
                new Coordinate(5, 'f'),
                new Coordinate(4, 'd'),
                new Coordinate(4, 'f'),
                new Coordinate(3, 'd'),
                new Coordinate(3, 'e'),
                new Coordinate(3, 'f')
        );

        List<Coordinate> result = king.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("킹이 모서리칸에 존재하는 경우를 검증한다.")
    @Test
    void findAllPossibleCoordinate2() {
        Coordinate start = new Coordinate(1, 'a');
        King king = new King(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(1, 'b'),
                new Coordinate(2, 'b'),
                new Coordinate(2, 'a')
        );

        List<Coordinate> result = king.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
