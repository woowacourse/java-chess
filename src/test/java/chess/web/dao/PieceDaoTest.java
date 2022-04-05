package chess.web.dao;

import chess.domain.ChessGame;
import chess.web.dto.ChessGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();
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
        ChessGameDto chessGameDto = new ChessGameDto(chessGame);

        //when
        int savedId = chessBoardDao.save();
        chessGameDao.save(chessGameDto, savedId);

        //then
        Assertions.assertDoesNotThrow(() -> pieceDao.save(savedId, chessGameDto));
    }
}