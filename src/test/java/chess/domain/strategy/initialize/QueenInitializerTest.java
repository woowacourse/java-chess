package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class QueenInitializerTest {
    @DisplayName("db에서 받아온 정보로 킹 초기화")
    @Test
    void webInitializeTest() {
        Map<String, String> pieceOnBoard = new HashMap<>();
        pieceOnBoard.put("a2", "q_white");
        pieceOnBoard.put("b2", "p_white");
        pieceOnBoard.put("f6", "P_black");
        pieceOnBoard.put("c8", "Q_black");
        pieceOnBoard.put("d4", "q_white");
        InitializeStrategy queenInitializer = new QueenInitializer();
        Map<Position, Piece> board = queenInitializer.webInitialize(pieceOnBoard);

        Assertions.assertThat(board.keySet()).contains(
                Position.of("a2"), Position.of("c8"), Position.of("d4")
        );
        for (Piece piece : board.values()) {
            Assertions.assertThat(piece).isInstanceOf(Queen.class);
        }
    }
}
