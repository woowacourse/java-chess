package chess.web.dao;

import chess.domain.ChessGame;
import chess.web.dto.ChessGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    private static final ChessGameDao chessGameDao = new ChessGameDao();

    @AfterEach
    private void rollback() {
       chessGameDao.remove("test");
    }

    @DisplayName("체스판 저장 테스트")
    @Test
    public void save() {
        //given
        ChessGame chessGame = new ChessGame("test");
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);

        //when & then
        Assertions.assertDoesNotThrow(() -> chessGameDao.save(chessGameDto));
    }
}
