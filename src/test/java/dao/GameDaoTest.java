package dao;

import chess.controller.dao.GameDao;
import chess.controller.dto.RequestDto;
import chess.domain.game.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class GameDaoTest {
    private final static GameDao gameDao = new GameDao();

    @DisplayName("게임 시작 시 초기 게임 정보 저장 후 게임 종료 테스트")
    @Test
    void saveInitGameAndUpdateEndStateTest() throws SQLException {
        saveInitGameTest();
        updateEndStateTest();
    }

    @DisplayName("게임 시작시 초기 게임 정보 저장")
    private void saveInitGameTest() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(PieceFactory.create());
        ChessGame chessGame = new ChessGame(chessBoard, Player.WHITE, GameStatus.NOT_STARTED);
        gameDao.saveInitGame(chessGame.start(new RequestDto(Command.START)));
    }

    @DisplayName("게임 끝날 시 게임 상태 종료로 변경")
    private void updateEndStateTest() throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();
        gameDao.updateEndState(roomNumber);
    }
}
