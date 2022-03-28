package chess.console.game.playstate;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.single.King;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    @DisplayName("프로모션 진행 후 반대 색상으로 넘어간다.")
    void runPromotion() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                Position.of('a', '8'), new WhitePawn(),
                Position.of('a', '1'), new King(WHITE),
                Position.of('a', '2'), new King(BLACK)
        ));
        Promotion promotion = new Promotion(chessBoard, WHITE);

        assertThat(promotion.run("Q").color()).isEqualTo(BLACK);
    }
}
