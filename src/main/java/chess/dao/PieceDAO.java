package chess.dao;

import chess.domain.piece.Piece;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PieceDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;

    private final ConnectionSetup con;

    public PieceDAO() {
        con = new ConnectionSetup();
    }

    public long createPiece(long gridId, Piece piece) throws SQLException {
        boolean isBlack = piece.isBlack();
        String position = String.valueOf(piece.position().x()) + String.valueOf(piece.position().y());

        String query = "INSERT INTO piece (isBlack, position, gridId) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlack);
        pstmt.setString(SECOND_PARAMETER_INDEX, position);
        pstmt.setLong(THIRD_PARAMETER_INDEX, gridId);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(FIRST_COLUMN);
        }
        throw new SQLException("아무 값도 삽입되지 않았습니다.");
    }
}
