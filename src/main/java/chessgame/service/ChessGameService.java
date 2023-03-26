package chessgame.service;

import chessgame.dao.GameRoomDao;
import chessgame.domain.chessgame.ChessGame;

public class ChessGameService {

    private final GameRoomDao gameRoomDao = new GameRoomDao();

    public void addGame(ChessGame chessGame) {
        gameRoomDao.addRoom(chessGame.getTurn());
    }
}
