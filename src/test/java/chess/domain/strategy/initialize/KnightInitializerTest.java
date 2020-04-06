package chess.domain.strategy.initialize;

import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class KnightInitializerTest {
    @DisplayName("db에서 받아온 정보로 킹 초기화")
    @Test
    void webInitializeTest() {
        Map<String, String> pieceOnBoard = new HashMap<>();
        pieceOnBoard.put("ATWO", "n_white");
        pieceOnBoard.put("BTWO", "p_white");
        pieceOnBoard.put("FSIX", "P_black");
        pieceOnBoard.put("CEIGHT", "N_black");
        pieceOnBoard.put("DFOUR", "n_white");
        InitializeStrategy knightInitializer = new KnightInitializer();
        Map<Position, Piece> board = knightInitializer.webInitialize(pieceOnBoard);

        Assertions.assertThat(board.keySet()).contains(
                Position.of("a2"), Position.of("c8"), Position.of("d4")
        );
        for (Piece piece : board.values()) {
            Assertions.assertThat(piece).isInstanceOf(Knight.class);
        }
    }
}
