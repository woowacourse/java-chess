package practice;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    void getchessBoardDto() {
        final ChessBoard chessBoard = new ChessBoard();
        System.out.println(chessBoard.getChessBoardDto().values().toString());
    }

}
