package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.DatabaseException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static chess.dao.DbConnection.getConnection;

public class BackupBoardDao {

    public void savePlayingBoard(String name, Board board, PieceColor turnColor) {
        deleteExistingBoard(name);

        updateTurnColor(name, turnColor);
        addPlayingBoard(name, board);
    }

    public void deleteExistingBoard(String name) {
        String query = "DELETE FROM backup_board WHERE room_name = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt =
                createPreparedStatementWithOneParameter(con.prepareStatement(query), name)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private void updateTurnColor(String roomName, PieceColor turnColor) {
        RoomDao roomDao = new RoomDao();
        roomDao.updateTurn(roomName, turnColor);
    }

    private void addPlayingBoard(String name, Board board) {
        String query = "INSERT INTO backup_board VALUES (?, ?, ?)";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            board.positions()
                .forEach(position -> addSquare(pstmt, name, position, board));

            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseException();
        }

    }

    private void addSquare(PreparedStatement pstmt, String name, Position position, Board board) {
        try {
            pstmt.setString(1, name);
            pstmt.setString(2, position.toString());
            pstmt.setString(3, board.pieceAtPosition(position).toString());
            pstmt.addBatch();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public HashMap<Position, Piece> findPlayingBoardByRoom(String name) {
        String query = "SELECT backup_board.position, backup_board.piece FROM backup_board " +
            "WHERE backup_board.room_name = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithOneParameter(
                con.prepareStatement(query), name);
            ResultSet rs = pstmt.executeQuery()) {

            HashMap<Position, Piece> board = new HashMap<>();
            while (rs.next()) {
                addSquareToBoard(rs, board);
            }

            return board;
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private void addSquareToBoard(ResultSet rs, HashMap<Position, Piece> board) throws SQLException {
        Position position = Position.from(rs.getString("position"));
        List<String> pieceInfo = Arrays.asList(rs.getString("piece").split("_"));
        PieceColor pieceColor = PieceColor.pieceColorByName(pieceInfo.get(0));
        PieceKind pieceKind = PieceKind.pieceKindByName(pieceInfo.get(1).toUpperCase());
        Piece piece = new Piece(pieceKind, pieceColor);

        board.put(position, piece);
    }

    private PreparedStatement createPreparedStatementWithOneParameter(
        PreparedStatement ps, String param) throws SQLException {
        ps.setString(1, param);
        return ps;
    }
}
