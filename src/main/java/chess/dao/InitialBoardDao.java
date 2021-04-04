package chess.dao;

import chess.domain.exceptions.DatabaseException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.DbConnection.getConnection;

public class InitialBoardDao {

    public Piece findInitialBoardPieceAtPosition(String positionName) {
        String query = "SELECT piece.piece_kind, piece.piece_color FROM ((initial_board " +
            "INNER JOIN piece ON initial_board.piece_id = piece.pid) " +
            "INNER JOIN position ON initial_board.position_id = position.pid)" +
            "WHERE position.address = ?";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = createPreparedStatementWithOneParameter(
                con.prepareStatement(query), positionName);
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

}
