package service;

import java.util.List;

import dto.BoardDto;
import repository.ChessDao;

public class GameRoomService {

    private final ChessDao chessDao;
    private long gameId = 0L;

    public GameRoomService(ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    public void createGameRoom(String gameName) {
        gameId = chessDao.addGame(gameName);
    }

    public List<String> readGameRooms() {
        return chessDao.findAllGame();
    }

    public List<BoardDto> getBoard(String gameName) {
        this.gameId = chessDao.findGameIdByGameName(gameName);
        return chessDao.findBoardByGameName(gameName);
    }
}
