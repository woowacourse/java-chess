package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class InitialBoardGeneratorTest {
    private BoardGenerator initialBoardGenerator = new InitialBoardGenerator();

    @Test
    void 보드_생성_시_각_기물을_시작_위치에_초기화한다() {
        Board board = initialBoardGenerator.create();
        List<String> boardLines = board.getLines().stream()
                .map(line -> String.join("", line))
                .toList();
        assertThat(boardLines).containsExactly(
                "RNBQKBNR",
                "PPPPPPPP",
                "........",
                "........",
                "........",
                "........",
                "pppppppp",
                "rnbqkbnr"
        );
    }
}
