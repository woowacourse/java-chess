package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import chess.domain.board.Coordinate;
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
    void findMovablePathWhite() {
        Coordinate start = new Coordinate(2, 'c');
        Coordinate destination = new Coordinate(3, 'c');
        Pawn pawn = new Pawn(Team.WHITE);

        List<Coordinate> result = pawn.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(3, 'c'),
                new Coordinate(4, 'c'));
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findMovablePathBlack() {
        Coordinate start = new Coordinate(7, 'c');
        Coordinate destination = new Coordinate(3, 'c');
        Pawn pawn = new Pawn(Team.BLACK);

        List<Coordinate> result = pawn.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(6, 'c'),
                new Coordinate(5, 'c'));
        assertThat(result).containsExactlyElementsOf(expected);
    }
}
