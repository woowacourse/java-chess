package chess.domain.strategy.initialize;

import chess.domain.piece.Pawn;
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
        pieceOnBoard.put("ATWO", "r_white");
        pieceOnBoard.put("BTWO", "p_white");
        pieceOnBoard.put("FSIX", "P_black");
        pieceOnBoard.put("CEIGHT", "R_black");
        pieceOnBoard.put("DFOUR", "r_white");
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
