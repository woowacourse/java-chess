package chess.repository;

import chess.domain.DTO.BoardDTO;
import chess.domain.DTO.TurnDTO;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.utils.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDAO extends DBConnector {
    private final String updateBoardTableQuery = "update board set piece = ? where position = ?";
    private final String updateTurnTableQuery = "update turn set turn_owner = ? where turn_owner = ?";

    public BoardDTO getSavedBoardInfo() throws SQLException {
        String query = "select * from board;";
        Map<String, String> boardInfo = new HashMap<>();

        try(PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet savedBoardInfo = pstmt.executeQuery()) {
            while (savedBoardInfo.next()) {
                boardInfo.put(savedBoardInfo.getString("position"),
                        savedBoardInfo.getString("piece"));
            }
        }
        return BoardDTO.of(boardInfo);
    }

    public TurnDTO getSavedTurnOwner() throws SQLException {
        String query = "select * from turn;";
        String turnOwner = "";

        try(PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet savedTurnOwner = pstmt.executeQuery()) {
            while (savedTurnOwner.next()) {
                turnOwner = savedTurnOwner.getString("turn_owner");
            }
        }
        return TurnDTO.of(turnOwner);
    }

    public void resetBoard(Board board) throws SQLException {
        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            String unicode = piece != null ? piece.getUnicode() : "";
            executeBoardUpdateQuery(unicode, position.convertToString());
        }
    }

    private void executeBoardUpdateQuery(String unicode, String position) throws SQLException {
        try(PreparedStatement pstmt = getConnection().prepareStatement(updateBoardTableQuery)) {
            pstmt.setString(1, unicode);
            pstmt.setString(2, position);
            pstmt.executeUpdate();
        }
    }

    public void renewBoardAfterMove(String targetPosition, String destinationPosition,
                                    Piece targetPiece) throws SQLException {
        try(PreparedStatement destinationChangeQuery = getConnection().prepareStatement(updateBoardTableQuery);
            PreparedStatement targetPositionChangeQuery = getConnection().prepareStatement(updateBoardTableQuery)) {
            destinationChangeQuery.setString(1, targetPiece.getUnicode());
            destinationChangeQuery.setString(2, destinationPosition);
            destinationChangeQuery.executeUpdate();

            targetPositionChangeQuery.setString(1, "");
            targetPositionChangeQuery.setString(2, targetPosition);
            targetPositionChangeQuery.executeUpdate();
        }
    }

    public void renewTurnOwnerAfterMove(Team turnOwner) throws SQLException {
        try(PreparedStatement pstmt = getConnection().prepareStatement(updateTurnTableQuery)) {
            pstmt.setString(1, turnOwner.getTeamString());
            pstmt.setString(2, turnOwner.getOpposite().getTeamString());
            pstmt.executeUpdate();
        }
    }

    public void resetTurnOwner(Team turnOwner) throws SQLException {
        try(PreparedStatement pstmt = getConnection().prepareStatement(updateTurnTableQuery)) {
            pstmt.setString(1, "white");
            pstmt.setString(2, turnOwner.getTeamString());
            pstmt.executeUpdate();
        }
    }
}
