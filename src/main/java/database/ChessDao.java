package database;

import domain.Room;
import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

import java.util.List;
import java.util.Map;

public interface ChessDao {
    List<Room> findAllRooms();

    Room saveRoom(final Room room);

    Room findRoomById(final long roomId);

    void deleteRoom(final long roomId);

    void saveBoard(final Map<Position, Piece> board, final long roomId);

    void deleteBoard(final long roomId);

    Board findBoardByRoomId(final long roomId);

    void clear();
}
