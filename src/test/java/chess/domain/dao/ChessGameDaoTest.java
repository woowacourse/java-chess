package chess.domain.dao;

import chess.domain.board.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new MemoryChessGameDao();

    @Test
    @DisplayName("턴을 업데이트하는 기능 테스트")
    void updateTurn() {
        final Turn turn = new Turn();
        turn.changeTurn();
        chessGameDao.updateTurn(turn, 11);
    }
}
