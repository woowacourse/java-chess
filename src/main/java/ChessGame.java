import database.ChessDao;
import domain.Room;
import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;

import java.util.List;
import java.util.Map;

public final class ChessGame {
    private static final String NO_SUCH_ROOM = "존재하지 않는 방 번호입니다.";

    private final long roomId;
    private final Board board;
    private final ChessDao dao;

    private ChessGame(final long roomId, final Board board, final ChessDao dao) {
        this.roomId = roomId;
        this.board = board;
        this.dao = dao;
    }

    public static ChessGame initGame(ChessDao dao, Room room) {
        final long roomId = dao.saveRoom(room);
        final Board board = Board.create(new InitialChessAlignment());

        return new ChessGame(roomId, board, dao);
    }

    public static ChessGame loadGame(ChessDao dao, long roomId) {
        if (dao.findAllRooms().stream().noneMatch(room -> room.getId() == roomId)) {
            throw new IllegalArgumentException(NO_SUCH_ROOM);
        }

        final Board board = dao.findBoardByRoomId(roomId);
        dao.deleteBoard(roomId);
        return new ChessGame(roomId, board, dao);
    }

    public static List<Room> findAllRooms(ChessDao dao) {
        return dao.findAllRooms();
    }

    public void save() {
        dao.saveBoard(board.getPieces(), roomId);
    }

    public void delete() {
        dao.deleteRoom(roomId);
    }

    public void move(Position source, Position destination) {
        board.move(source, destination);
    }

    public boolean winnerNotExist() {
        return board.isWhiteKingExist() && board.isBlackKingExist();
    }

    public Team getWinner() {
        return board.getWinner();
    }

    public Map<Position, Piece> getBoard() {
        return board.getPieces();
    }

    public double getBlackScore() {
        return board.getCurrentBlackScore();
    }

    public double getWhiteScore() {
        return board.getCurrentWhiteScore();
    }
}
