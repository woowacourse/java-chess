package chess.console.gamestate;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.single.King;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PromotionTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard(Map.of(
                Position.of('a', '8'), new Piece(WHITE, new WhitePawn()),
                Position.of('a', '1'), new Piece(WHITE, new King()),
                Position.of('a', '2'), new Piece(BLACK, new King())
        ));
    }

    @Test
    @DisplayName("otherState 호출 시 예외발생")
    void otherStateException() {
        Running promotion = new Promotion(new WhiteRunning(chessBoard));

        assertThatThrownBy(() -> promotion.otherState(chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Promotion은 상대 턴을 확인할 수 없습니다.");
    }

    @Test
    @DisplayName("run 실행 시 이전 상태의 다음 상태 반환")
    void run() {
        GameState promotion = new Promotion(new WhiteRunning(chessBoard));

        assertThat(promotion.run("Q")).isInstanceOf(BlackRunning.class);
    }
}
