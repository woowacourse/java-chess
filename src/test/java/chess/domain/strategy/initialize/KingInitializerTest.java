package chess.domain.strategy.initialize;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class KingInitializerTest {
    @DisplayName("db에서 받아온 정보로 킹 초기화")
    @Test
    void webInitializeTest() {
        Map<String, String> pieceOnBoard = new HashMap<>();
        pieceOnBoard.put("a2", "kwhite");
        pieceOnBoard.put("b2", "pwhite");
        pieceOnBoard.put("f6", "Pblack");
        pieceOnBoard.put("c8", "Kblack");
        pieceOnBoard.put("d4", "kwhite");
        InitializeStrategy kingInitializer = new KingInitializer();
        Map<Position, Piece> board = kingInitializer.initialize(pieceOnBoard);

        Assertions.assertThat(board.keySet()).contains(
                Position.of("a2"), Position.of("c8"), Position.of("d4")
        );
        for (Piece piece : board.values()) {
            Assertions.assertThat(piece).isInstanceOf(King.class);
        }
    }
}
