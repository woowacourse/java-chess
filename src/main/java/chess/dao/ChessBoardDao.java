package chess.dao;

import chess.dto.ChessBoardDto;
import chess.dto.Tile;
import chess.domain.chesspiece.ChessPieceFactory;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessBoardDao {

    public void saveChessBoard(ChessBoardDto chessBoardDto) throws SQLException {
        Objects.requireNonNull(chessBoardDto);
        Connection connection = new DBConnector().getConnection();
        String query = "INSERT INTO chessboard VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (Tile tile : chessBoardDto.getTiles()) {
            pstmt.setString(1, tile.getPosition());
            pstmt.setString(2, tile.getPiece());
            pstmt.addBatch();
        }

        try (connection; pstmt) {
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw  new SQLException("체스 보드를 저장할 수 없습니다.");
        }
    }

    public void deleteChessBoard() throws SQLException {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        String query = "DELETE FROM chessboard";
        try (connection; stmt) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw  new SQLException("체스 보드를 삭제할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getChessBoard() throws SQLException {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        String query = "SELECT * FROM chessboard";
        ResultSet rs = stmt.executeQuery(query);

        Map<Position, Piece> chessBoard = new HashMap<>();
        try (connection; stmt; rs) {
            while (rs.next()) {
                Position position = Positions.of(rs.getString("position"));
                Piece piece = ChessPieceFactory.of(rs.getString("piece"));
                chessBoard.put(position, piece);
            }
        } catch (SQLException e) {
            throw new SQLException("체스 보드를 가져올 수 없습니다.");
        }
        return chessBoard;
    }

    public boolean isChessBoardExists() throws SQLException {
        Connection connection = new DBConnector().getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM chessBoard";
        ResultSet rs = stmt.executeQuery(query);

        try (connection; stmt; rs) {
            return rs.next();
        } catch (SQLException e) {
            throw new SQLException("체스 보드 존재여부를 찾을 수 없습니다.");
        }
    }
}