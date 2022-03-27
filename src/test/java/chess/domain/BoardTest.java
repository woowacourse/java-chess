package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.WHITE_SOURCE;
import static chess.domain.PositionFixture.WHITE_TARGET;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece1() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(WHITE_SOURCE, WHITE_TARGET);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(WHITE_TARGET)).isTrue();
    }

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece2() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(WHITE_SOURCE, WHITE_TARGET);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(WHITE_SOURCE)).isFalse();
    }

}
