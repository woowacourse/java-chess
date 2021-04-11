package chess.repository;

import chess.domain.ChessGame;
import chess.domain.Room;

import java.util.List;

public interface ChessRepository {
    void createChessGame(final ChessGame chessGame);
    void saveChessGameFromRoom(final String roomId, final ChessGame chessGame);
    ChessGame loadChessGameFromRoom(final String roomId);
    void createRoom(final String roomReqData);
    List<Room> getTotalRoom();
}
