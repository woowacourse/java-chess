package chess.domain.room;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;

import java.util.List;

public class Rooms {

    private final ChessGameDao chessGameDao;

    public Rooms(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public int createNewRoom() {
        ChessGame chessGame = ChessGame.create();
        return chessGameDao.save(chessGame);
    }

    public List<Integer> getRooms() {
        return chessGameDao.selectAllId();
    }
}
