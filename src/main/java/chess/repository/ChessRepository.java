package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.utils.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ChessRepository extends DBConnector {
    private final String updateBoardTableQuery = "update board set piece = ? where position = ?";
    private final String updateTurnTableQuery = "update turn set turn_owner = ? where turn_owner = ?";

    public ResultSet getSavedBoardInfo() throws SQLException {
        String query = "select * from board;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        return pstmt.executeQuery();
    }

    public ResultSet getSavedTurnOwner() throws SQLException {
        String query = "select * from turn;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        return pstmt.executeQuery();
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
        PreparedStatement pstmt = getConnection().prepareStatement(updateBoardTableQuery);
        pstmt.setString(1, unicode);
        pstmt.setString(2, position);
        pstmt.executeUpdate();
    }

    public void renewBoardAfterMove(String targetPosition, String destinationPosition,
                                    Piece targetPiece) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(updateBoardTableQuery);
        pstmt.setString(1, targetPiece.getUnicode());
        pstmt.setString(2, destinationPosition);
        pstmt.executeUpdate();

        PreparedStatement pstmt2 = getConnection().prepareStatement(updateBoardTableQuery);
        pstmt2.setString(1, "");
        pstmt2.setString(2, targetPosition);
        pstmt2.executeUpdate();
    }

    public void renewTurnOwnerAfterMove(Team turnOwner) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(updateTurnTableQuery);
        pstmt.setString(1, turnOwner.getTeamString());
        pstmt.setString(2, turnOwner.getOpposite().getTeamString());
        pstmt.executeUpdate();
    }

    public void resetTurnOwner(Team turnOwner) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(updateTurnTableQuery);
        pstmt.setString(1, "white");
        pstmt.setString(2, turnOwner.getTeamString());
        pstmt.executeUpdate();
    }
}
