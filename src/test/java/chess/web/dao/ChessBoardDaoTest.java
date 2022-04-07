package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.web.dto.ChessGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();
    private static final ChessGameDao chessGameDao = new ChessGameDao();
    private static final PieceDao pieceDao = new PieceDao();

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

        //when
        int savedId = chessBoardDao.save();
        chessGameDao.save(chessGameDto, savedId);

        //then
        assertThat(savedId).isNotEqualTo(0);
    }
}
