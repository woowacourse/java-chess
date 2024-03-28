package chess.dao;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import chess.exception.DbException;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessDaoManager {
    private final ConnectionGenerator connectionGenerator;
    private final BoardDao boardDao;
    private final ChessGameDao chessGameDao;

    public ChessDaoManager() {
        this(new ProductionConnectionGenerator(), new BoardDao(), new ChessGameDao());
    }

    private ChessDaoManager(ConnectionGenerator connectionGenerator, BoardDao boardDao, ChessGameDao chessGameDao) {
        this.connectionGenerator = connectionGenerator;
        this.boardDao = boardDao;
        this.chessGameDao = chessGameDao;
    }

    public void initialize(Board board, Team team, String roomName) {
        Connection connection = null;
        try {
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
            chessGameDao.add(team, roomName, connection);
            boardDao.addAll(board, roomName, connection);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
        } finally {
            close(connection);
        }
    }

    public ChessGame loadChessGame(String roomName) {
        Connection connection = null;
        try {
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
            Team currentTeam = chessGameDao.findCurrentTeamByRoomName(roomName, connection);
            Board board = boardDao.loadAll(roomName, connection);
            connection.commit();
            return new ChessGame(board, currentTeam);
        } catch (SQLException e) {
            rollback(connection);
        } finally {
            close(connection);
        }
        return null;
    }

    public void update(Movement movement, Piece piece, Team currentTeam, String roomName) {
        Connection connection = null;
        try {
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
            chessGameDao.update(currentTeam, roomName, connection);
            boardDao.update(movement, piece, roomName, connection);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
        } finally {
            close(connection);
        }
    }

    public void deleteChessGame(String roomName) {
        Connection connection = null;
        try {
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
            chessGameDao.delete(roomName, connection);
            boardDao.delete(roomName, connection);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
        } finally {
            close(connection);
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException("DB 연결 오류");
            }
        }
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DbException("DB 연결 오류");
            }
        }
        throw new DbException("DB 연결 오류");
    }
}
