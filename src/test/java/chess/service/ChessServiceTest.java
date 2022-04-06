package chess.service;

import chess.dao.BoardDao;
import chess.domain.piece.Blank;
import chess.dto.MoveDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessServiceTest {
    @Test
    void updateMove() {
        ChessService chessService = new ChessService();
        chessService.getInitialBoard(1);
        MoveDTO moveDTO = new MoveDTO("g7", "g3");
        chessService.move(moveDTO, 1);
    }

}