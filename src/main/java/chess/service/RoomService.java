package chess.service;

import chess.dao.DBChessGameDao;
import chess.domain.ChessGame;
import chess.domain.position.Position;

import java.util.List;

public class RoomService {

    private final DBChessGameDao chessGameDao;
    private ChessGame chessGame;
    private int id;

    public RoomService(DBChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void createRoom(){
        this.chessGame = ChessGame.create();
        this.id = chessGameDao.insert(chessGame);

    }

    public void connectRoom(final int id){
        this.chessGame = chessGameDao.select(id);
        this.id = id;
    }

    public void updateRoom(final Position sourcePosition, final Position targetPosition) {
        chessGame.movePiece(sourcePosition, targetPosition);
        chessGameDao.update(id, chessGame);
    }

    public void deleteRoom(){
        chessGameDao.delete(id);
    }

    public List<Integer> getRoomIds() {
        return chessGameDao.selectAllId();
    }

    public int getId() {
        return id;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

}
