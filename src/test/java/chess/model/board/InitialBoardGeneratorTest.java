package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.PieceFixture;
import java.util.List;
import org.junit.jupiter.api.Test;

class InitialBoardGeneratorTest {
    private final BoardGenerator initialBoardGenerator = new InitialBoardGenerator();

    @Test
    void 보드_생성_시_각_기물을_시작_위치에_초기화한다() {
        // when, then
        Board board = initialBoardGenerator.create();
        List<String> boardLines = PieceFixture.mappingBoard(board);
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
