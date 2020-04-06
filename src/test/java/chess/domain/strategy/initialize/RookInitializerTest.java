package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RookInitializerTest {
    @DisplayName("db에서 받아온 정보로 킹 초기화")
    @Test
    void webInitializeTest() {
        Map<String, String> pieceOnBoard = new HashMap<>();
        pieceOnBoard.put("a2", "r_white");
        pieceOnBoard.put("b2", "p_white");
        pieceOnBoard.put("f6", "P_black");
        pieceOnBoard.put("c8", "R_black");
        pieceOnBoard.put("d4", "r_white");
        InitializeStrategy rookInitializer = new RookInitializer();
        Map<Position, Piece> board = rookInitializer.webInitialize(pieceOnBoard);

        Assertions.assertThat(board.keySet()).contains(
                Position.of("a2"), Position.of("c8"), Position.of("d4")
        );
        for (Piece piece : board.values()) {
            Assertions.assertThat(piece).isInstanceOf(Rook.class);
        }
    }
}
