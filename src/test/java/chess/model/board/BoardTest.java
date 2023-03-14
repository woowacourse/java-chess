package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("64개의 칸을 가지는 체스판을 생성한다.")
    void constructor_whenCall_thenSuccess() {
        final Board board = assertDoesNotThrow(Board::create);

        assertAll(
            () -> assertThat(board).isExactlyInstanceOf(Board.class),
            () -> assertThat(board)
                .extracting("squares", InstanceOfAssertFactories.list(Square.class))
                .hasSize(64)
        );
    }

}
