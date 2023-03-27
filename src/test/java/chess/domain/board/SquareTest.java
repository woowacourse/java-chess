package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.board.File;
import domain.board.Rank;
import domain.board.Square;

class SquareTest {
    @Test
    @DisplayName("square의 좌표를 반환한다.")
    void toCoordinate() {
        Square square = Square.of(File.H, Rank.FIVE);

        List<Integer> coordinate = square.toCoordinate();

        assertThat(coordinate).containsExactly(7, 4);
    }
}
