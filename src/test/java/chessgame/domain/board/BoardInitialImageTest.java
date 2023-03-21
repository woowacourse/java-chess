package chessgame.domain.board;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardInitialImageTest {

    @Test
    @DisplayName("생성 시 64개의 초기 값을 반환해야 한다")
    void generateBoardInitialImage() {
        final Map<Coordinate, Square> board = BoardInitialImage.generate();

        assertThat(board).hasSize(64);
    }
}
