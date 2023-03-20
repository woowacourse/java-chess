package chess.domain.game;

import boardStrategy.EmptyBoardStrategy;
import chess.boardstrategy.InitialBoardStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ChessBoardTest {

    private static final EmptyBoardStrategy emptyBoardStrategy = new EmptyBoardStrategy();
    private static final InitialBoardStrategy initialBoardStrategy = new InitialBoardStrategy();
    private ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 체스보드가_초기화_되었으면_true를_반환한다() {
        chessBoard.initialize(initialBoardStrategy.generate());
        assertThat(chessBoard.isInitialized())
                .isTrue();
    }

    @Test
    void 체스보드가_초기화_되지않았으면_false를_반환한다() {
        assertThat(chessBoard.isInitialized())
                .isFalse();
    }
}