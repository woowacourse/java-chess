package chess.web;

import chess.database.factory.BoardFactory;
import chess.domain.piece.Blank;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ChessControllerTest {
    @Test
    void init() {
        ChessController chessController = new ChessController();
        Map<String, Object> initialBoard = chessController.getInitialBoard("1");
        System.out.println(initialBoard.get("chessPiece").toString());
    }

    @Test
    void getStatus() {
        String source = "a2";
        BoardFactory.updatePosition(source, Blank.SYMBOL);
    }
}