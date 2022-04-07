package chess.web.dao;

import chess.domain.ChessGame;
import chess.web.dto.ChessGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private static final ChessGameDao chessGameDao = new ChessGameDao();
    private static final PieceDao pieceDao = new PieceDao();

    @AfterEach
    private void rollback() {
        chessGameDao.remove("test");
    }

    @DisplayName("피스들 저장 테스트")
    @Test
    public void save() {
        //given
        ChessGame chessGame = new ChessGame("test");
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);

        //when
        chessGameDao.save(chessGameDto);

        //then
        Assertions.assertDoesNotThrow(() -> pieceDao.save(chessGameDto));
    }
}
