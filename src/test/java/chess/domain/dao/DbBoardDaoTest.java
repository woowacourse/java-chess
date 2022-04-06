package chess.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.DbBoardDao;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import db.dao.MemberDao;
import java.sql.Connection;
import java.util.Map;
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
        ChessGame chessGame = ChessGame.create();
        chessGame.initialze();
        dbBoardDao.updateAll(chessGame.getChessBoardInformation());
    }

    @Test
    void findAll() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create();
        chessGame.initialze();
        ChessBoardDto chessBoardDto = dbBoardDao.findAll();
        assertThat(chessBoardDto.isEmpty()).isFalse();
    }


}
