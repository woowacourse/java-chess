package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {

        assertThatCode(() -> new Pawn(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findAllPossibleCoordinateCaseWhite() {
        Coordinate start = new Coordinate(2, 'c');
        Pawn pawn = new Pawn(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(3, 'b'),
                new Coordinate(3, 'c'),
                new Coordinate(4, 'c'),
                new Coordinate(3, 'd')

        );

        List<Coordinate> result = pawn.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("흰색 폰이 모서리칸에 존재하는 경우를 검증한다.")
    @Test
    void findAllPossibleCoordinateCaseWhite2() {
        Coordinate start = new Coordinate(1, 'a');
        Pawn pawn = new Pawn(Team.WHITE);
        List<Coordinate> expected = List.of(
                new Coordinate(2, 'a'),
                new Coordinate(3, 'a'),
                new Coordinate(2, 'b')
        );

        List<Coordinate> result = pawn.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findAllPossibleCoordinateCaseBlack() {
        Coordinate start = new Coordinate(7, 'g');
        Pawn pawn = new Pawn(Team.BLACK);
        List<Coordinate> expected = List.of(
                new Coordinate(6, 'f'),
                new Coordinate(6, 'g'),
                new Coordinate(5, 'g'),
                new Coordinate(6, 'h')
        );

        List<Coordinate> result = pawn.findAllPossibleCoordinate(start);

        assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
