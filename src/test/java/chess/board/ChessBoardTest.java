package chess.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ChessBoardTest {
    @Test
    void 체스보드를_생성하면_8개의_행이_생성된다() {
        ChessBoard chessBoard = ChessBoard.create();
        Assertions.assertThat(chessBoard.chessBoard()).hasSize(8);
    }
}
