package service;

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
}
