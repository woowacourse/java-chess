package chess.domain;

import chess.dao.ChessGameDao;

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

    public ChessGame connectRoom(final int id) {
        return chessGameDao.select(id);
    }

    public List<Integer> getRooms() {
        return chessGameDao.selectAllId();
    }
}
