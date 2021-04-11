package chess.repository;

import chess.dao.GameDAO;
import chess.dao.RoomDAO;
import chess.domain.ChessGame;
import chess.domain.Room;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;

import java.util.List;

public class ChessRepositoryImpl implements ChessRepository {
    private GameDAO gameDAO;
    private RoomDAO roomDAO;

    public ChessRepositoryImpl(GameDAO gameDAO, RoomDAO roomDAO) {
        this.gameDAO = gameDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    public void createChessGame(final ChessGame chessGame) {
        gameDAO.create(chessGame);
    }

    @Override
    public void saveChessGameFromRoom(final String roomId, final ChessGame chessGame) {
        Room room = roomDAO.readRoomFromId(roomId);
        gameDAO.update(room.getGameId(), chessGame);
    }

    @Override
    public ChessGame loadChessGameFromRoom(final String roomId) {
        Room room = roomDAO.readRoomFromId(roomId);
        return gameDAO.readFromId(room.getGameId());
    }

    @Override
    public void createRoom(final String roomReqData) {
        createChessGame(new ChessGame(new BlackTeam(), new WhiteTeam()));
        int gameId = gameDAO.getRecentGameId();
        roomDAO.create(roomReqData, gameId);
    }

    @Override
    public List<Room> getTotalRoom() {
        return roomDAO.readTotalRoom();
    }
}
