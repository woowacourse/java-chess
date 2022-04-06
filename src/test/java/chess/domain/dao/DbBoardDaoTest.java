package chess.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.DbBoardDao;
import chess.domain.ChessGame;
import chess.dto.ChessBoardDto;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DbBoardDaoTest {

    @Test
    void connection() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void updateAll() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialze();
        dbBoardDao.updateAll(chessGame.getChessBoardInformation());
    }

    @Test
    void findAll() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialze();
        ChessBoardDto chessBoardDto = dbBoardDao.findAll();
        assertThat(chessBoardDto.isEmpty()).isFalse();
    }


}
