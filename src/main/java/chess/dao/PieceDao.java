package chess.dao;

import chess.dao.setting.DBConnection;
import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao extends DBConnection {
    public void initializePieceStatus(final String pieceName, final String piecePosition) {
        String query = "INSERT INTO piece (piece_name, piece_position) VALUE (?, ?)";
        try (PreparedStatement psmt = getConnection().prepareStatement(query)) {
            psmt.setString(1, pieceName);
            psmt.setString(2, piecePosition);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ChessRequestDto> showAllPieces(){
        List<ChessRequestDto> pieces = new ArrayList<>();
        String query = "SELECT * FROM piece";
        try (PreparedStatement psmt = getConnection().prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                pieces.add(new ChessRequestDto(
                        rs.getLong("id"),
                        rs.getString("piece_name"),
                        rs.getString("piece_position")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void movePiece(final MoveRequestDto moveRequestDto) {
        String query = "UPDATE piece SET piece_position=? WHERE piece_position=?";
        try (PreparedStatement psmt = getConnection().prepareStatement(query)) {
            psmt.setString(1, moveRequestDto.getTarget());
            psmt.setString(2, moveRequestDto.getSource());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllPieces() {
        String query = "DELETE FROM piece";
        try (PreparedStatement psmt = getConnection().prepareStatement(query)) {
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePiece(final MoveRequestDto moveRequestDto) {
        String query = "DELETE FROM piece WHERE piece_position=?";
        try (PreparedStatement psmt = getConnection().prepareStatement(query)) {
            psmt.setString(1, moveRequestDto.getTarget());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
