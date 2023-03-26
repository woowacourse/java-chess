package chess.domain.room;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.position.Position;

public class Room {

    private final ChessGameDao chessGameDao;
    private final int id;

    public Room(ChessGameDao chessGameDao, int id) {
        this.chessGameDao = chessGameDao;
        this.id = id;
    }

    public ChessGame connectRoom() {
        return chessGameDao.select(id);
    }

    public void updateRoom(Position sourcePosition, Position targetPosition){
        ChessGame chessGame = connectRoom();
        chessGame.movePiece(sourcePosition, targetPosition);
        chessGameDao.update(chessGame);
    }

    public void deleteRoom() {
        chessGameDao.delete(id);
    }

}
