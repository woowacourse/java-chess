package chess.web;

import chess.controller.dto.StatusDTO;
import chess.database.factory.BoardFactory;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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

    @Test
    void getStatus() {
        String source = "a2";
        BoardFactory.updatePosition(source, Blank.SYMBOL);
    }
}