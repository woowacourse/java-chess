package chess.dao;

import chess.domain.grid.Grid;
import chess.domain.piece.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GridDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;

    private final ConnectionSetup con;

    public GridDAO() {
        con = new ConnectionSetup();
    }

    public long createGrid(long roomId, Grid grid) throws SQLException {
        boolean isBlackTurn = grid.isMyTurn(Color.BLACK);
        boolean isFinished = grid.isFinished();

        String query = "INSERT INTO grid (isBlackTurn, isFinished, roomId) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlackTurn);
        pstmt.setBoolean(SECOND_PARAMETER_INDEX, isFinished);
        pstmt.setLong(THIRD_PARAMETER_INDEX, roomId);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(FIRST_COLUMN);
        }
        throw new SQLException("아무 값도 삽입되지 않았습니다.");
    }
}
