package chess.dao;

import chess.domain.board.Position;
import chess.domain.exceptions.DatabaseException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static chess.dao.DbConnection.getConnection;

public class BackupBoardDao {

    public void savePlayingBoard(String name, Map<Position, Piece> playingBoard, PieceColor turnColor) {
        deleteExistingBoard(name);

        updateTurnColor(name, turnColor);
        addPlayingBoard(name, playingBoard);
    }

    public void deleteExistingBoard(String name) {
        String query = "DELETE FROM backup_board WHERE room_name = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithOneParameter(con.prepareStatement(query), name)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private void updateTurnColor(String roomName, PieceColor turnColor) {
        String query = "UPDATE room SET turn = ? WHERE name = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithTwoParameter(
                con.prepareStatement(query), turnColor.getName(), roomName)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private void addPlayingBoard(String name, Map<Position, Piece> playingBoard) {
        playingBoard.keySet()
            .forEach(position -> addSquare(name, position, playingBoard)); //TODO 수정 필요
    }

    private void addSquare(String name, Position position, Map<Position, Piece> playingBoard) {
        PositionDao positionDao = new PositionDao();
        PieceDao pieceDao = new PieceDao();
        int positionId = positionDao.findPositionId(position);
        int pieceId = pieceDao.findPieceId(playingBoard.get(position));

        String query = "INSERT INTO backup_board VALUES (?, ?, ?)";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithThreeParameter(
                con.prepareStatement(query), name, positionId, pieceId)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public Piece findPlayingBoardByRoom(String name, String positionName) {
        String query = "SELECT piece.piece_kind, piece.piece_color FROM ((backup_board " +
            "INNER JOIN piece ON backup_board.piece_id = piece.pid) " +
            "INNER JOIN position ON backup_board.position_id = position.pid)" +
            "WHERE backup_board.room_name = ? AND position.address = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithTwoParameter(
                con.prepareStatement(query), name, positionName);
            ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) {
                return null;
            }

            PieceKind pieceKind = PieceKind.pieceKindByName(rs.getString("piece_kind"));
            PieceColor pieceColor = PieceColor.pieceColorByName(rs.getString("piece_color"));

            return new Piece(pieceKind, pieceColor);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    private PreparedStatement createPreparedStatementWithOneParameter(
        PreparedStatement ps, String param) throws SQLException {
        ps.setString(1, param);
        return ps;
    }

    private PreparedStatement createPreparedStatementWithTwoParameter(
        PreparedStatement ps, String firstParam, String secondParam) throws SQLException {
        ps.setString(1, firstParam);
        ps.setString(2, secondParam);
        return ps;
    }

    private PreparedStatement createPreparedStatementWithThreeParameter(
        PreparedStatement ps, String firstParam, int secondParam, int thirdParam) throws SQLException {
        ps.setString(1, firstParam);
        ps.setInt(2, secondParam);
        ps.setInt(3, thirdParam);

        return ps;
    }
}
