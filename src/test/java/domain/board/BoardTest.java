package domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static testAssistant.creationAssistant.createBoard;
import static testAssistant.creationAssistant.createPawn;

public class BoardTest {

    @Test
    void of() {
        Board board = createBoard(createPawn("white", "a1"));

        assertThat(board).isNotNull();
    }
}
