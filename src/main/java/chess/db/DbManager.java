package chess.db;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;

public class DbManager {
    private final BoardDao boardDao;
    private final ChessGameDao chessGameDao;

    public DbManager() {
        this.boardDao = new BoardDao();
        this.chessGameDao = new ChessGameDao();
    }

    public void initialize(String roomName, Board board, Team team) {
        chessGameDao.add(roomName, team);
        boardDao.addAll(board, roomName);
    }

    public ChessGame loadChessGame(String roomName) {
        Team currentTeam = chessGameDao.findCurrentTeamByRoomName(roomName);
        Board board = boardDao.loadAll(roomName);

        return new ChessGame(board, currentTeam);
    }

    public void update(String roomName, Movement movement, Piece piece, Team currentTeam) {
        chessGameDao.update(currentTeam, roomName);
        boardDao.update(movement, piece, roomName);
    }

    public void deleteChessGame(String roomName) {
        chessGameDao.delete(roomName);
        boardDao.delete(roomName);
    }
}
