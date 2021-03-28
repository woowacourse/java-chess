package chess.dao;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.dto.responsedto.GridResponseDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class GridDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_COLUMN = 2;
    private static final int THIRD_COLUMN = 3;
    private static final int FOURTH_COLUMN = 4;
    private static final int FIFTH_COLUMN = 5;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;


    private final ConnectionSetup con;

    public GridDAO() {
        con = new ConnectionSetup();
    }

    public long createGrid(long roomId) throws SQLException {
        Grid grid = new Grid(new NormalGridStrategy());
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

    public GridResponseDto findGridByGridId(long gridId) throws SQLException {
        String query = "SELECT * FROM grid WHERE gridId = ? LIMIT 1";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query);
        pstmt.setLong(FIRST_PARAMETER_INDEX, gridId);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new GridResponseDto(
                rs.getLong(FIRST_COLUMN),
                rs.getBoolean(SECOND_COLUMN),
                rs.getBoolean(THIRD_COLUMN),
                rs.getLong(FOURTH_COLUMN),
                rs.getObject(FIFTH_COLUMN, LocalDateTime.class)
        );
    }

    public GridResponseDto findRecentGridByRoomId(long roomId) throws SQLException {
        String query = "SELECT * FROM grid WHERE roomId = ? ORDER BY createdAt DESC LIMIT 1";
        PreparedStatement pstmt = con.getConnection().prepareStatement(query);
        pstmt.setLong(FIRST_PARAMETER_INDEX, roomId);
        pstmt.executeQuery();
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new GridResponseDto(
                rs.getLong(FIRST_COLUMN),
                rs.getBoolean(SECOND_COLUMN),
                rs.getBoolean(THIRD_COLUMN),
                rs.getLong(FOURTH_COLUMN),
                rs.getObject(FIFTH_COLUMN, LocalDateTime.class)
        );
    }
}
