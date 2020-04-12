package dao;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.ChessBoardDto;
import chess.domain.game.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ChessBoardDaoTest {

    @DisplayName("체스 보드를 DB에 저장 및 삭제 테스트")
    @Test
    void saveAndDeleteChessBoard() throws SQLException {
        GameDao gameDao = new GameDao();
        int roomNumber = gameDao.findMaxRoomNumber();
        saveChessBoardTest(roomNumber);
        deleteChessBoardTest(roomNumber);
    }

    @DisplayName("체스보드를 DB에 저장하는 테스트")
    void saveChessBoardTest(int roomNumber) throws SQLException {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        ChessBoardDto chessBoardDto = new ChessBoardDto(PieceFactory.create());
        chessBoardDao.saveChessBoard(chessBoardDto, roomNumber);
    }

    @DisplayName("게임이 끝날 시, 체스 보드를 DB에 삭제하는 테스트")
    void deleteChessBoardTest(int roomNumber) throws SQLException {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        chessBoardDao.deleteChessBoard(roomNumber);
    }
}
