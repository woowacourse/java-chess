package chess.service;

import chess.dao.DBBoardDao;
import chess.dao.DBRoomDao;
import chess.domain.Board;
import chess.domain.Room;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import static chess.domain.piece.Team.EMPTY;

public final class ChessGameService {

    private final DBRoomDao roomDao;
    private final DBBoardDao boardDao;
    private final Room room;

    public ChessGameService(final DBRoomDao roomDao, final DBBoardDao boardDao, final Room room) {
        this.roomDao = roomDao;
        this.boardDao = boardDao;
        this.room = loadBoard(room);
    }

    public Room loadBoard(final Room waitingRoom) {
        int id = waitingRoom.getId();
        Team turn = waitingRoom.getTurn();
        if (turn == EMPTY) {
            Room room = Room.from(id);
            roomDao.update(room);
            boardDao.insert(waitingRoom.getId(), room.getBoard());
            return room;
        }
        Board board = boardDao.select(id);
        return new Room(id, board, turn);

    }

    public void move(final Position sourcePosition, final Position targetPosition) {
        room.movePiece(sourcePosition, targetPosition);
        roomDao.update(room);
        boardDao.update(room.getId(), room.getBoard());
    }

    public void delete() {
        roomDao.delete(room);
    }

    public boolean isGameEnd() {
        return room.isGameEnd();
    }

    public double getTotalScore(final Team team) {
        return room.getTotalScore(team);
    }

    public Team getWinTeam() {
        return room.getWinTeam();
    }

    public Team getTurn() {
        return room.getTurn();
    }

    public Board getBoard() {
        return room.getBoard();
    }

}
