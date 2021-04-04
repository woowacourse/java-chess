package chess.dao;

import chess.dao.setting.DBConnection;
import chess.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessDao extends DBConnection {
    public void initializePieceStatus(final PieceRequestDto pieceRequestDto) throws SQLException {
        String query = "INSERT INTO piece_status (piece_name, piece_position) VALUE (?, ?)";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, pieceRequestDto.getPieceName());
        psmt.setString(2, pieceRequestDto.getPiecePosition());
        psmt.executeUpdate();
    }

    public void initializeTurn() throws SQLException {
        String query = "INSERT INTO turn (current_turn) VALUE (?)";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, "white");
        psmt.executeUpdate();
    }

    public List<ChessRequestDto> showAllPieces() throws SQLException {
        List<ChessRequestDto> pieces = new ArrayList<>();
        ResultSet rs = readPieceStatus();
        while (rs.next()) {
            pieces.add(new ChessRequestDto(
                    rs.getLong("id"),
                    rs.getString("piece_name"),
                    rs.getString("piece_position")
            ));
        }
        return pieces;
    }

    private ResultSet readPieceStatus() throws SQLException {
        String query = "SELECT * FROM piece_status";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        return psmt.executeQuery();
    }

    public List<TurnRequestDto> showCurrentTurn() throws SQLException {
        List<TurnRequestDto> turn = new ArrayList<>();
        ResultSet rs = readCurrentTurn();
        while (rs.next()) {
            turn.add(new TurnRequestDto(
                    rs.getLong("id"),
                    rs.getString("current_turn")
            ));
        }
        return turn;
    }

    private ResultSet readCurrentTurn() throws SQLException {
        String query = "SELECT * FROM turn";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        return psmt.executeQuery();
    }

    public void movePiece(final MoveRequestDto moveRequestDto) throws SQLException {
        String query = "UPDATE piece_status SET piece_position=? WHERE piece_position=?";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, moveRequestDto.getTarget());
        psmt.setString(2, moveRequestDto.getSource());
        psmt.executeUpdate();
    }

    public void changeTurn(final TurnChangeRequestDto turnChangeRequestDto) throws SQLException {
        String query = "UPDATE turn SET current_turn=? WHERE current_turn=?";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, turnChangeRequestDto.getNextTurn());
        psmt.setString(2, turnChangeRequestDto.getCurrentTurn());
        psmt.executeUpdate();
    }

    public void removeAllPieces() throws SQLException {
        String query = "DELETE FROM piece_status";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.executeUpdate();
    }
    
    public void removeTurn() throws SQLException {
        String query = "DELETE FROM turn";
        PreparedStatement pstm = getConnection().prepareStatement(query);
        pstm.executeUpdate();
    }

    public void removePiece(final MoveRequestDto moveRequestDto) throws SQLException {
        String query = "DELETE FROM piece_status WHERE piece_position=?";
        PreparedStatement psmt = getConnection().prepareStatement(query);
        psmt.setString(1, moveRequestDto.getTarget());
        psmt.executeUpdate();
    }
}
