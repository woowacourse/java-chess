package chess.web;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChessControllerTest {
    @Test
    void init() {
        ChessController chessController = new ChessController();
        Map<String, Object> initialBoard = chessController.getInitialBoard();
        System.out.println(initialBoard.get("chessPiece").toString());

    }
}