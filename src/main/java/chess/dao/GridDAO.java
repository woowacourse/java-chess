package chess.dao;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.dto.GridDto;
import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

import java.sql.Connection;
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
    private static final int SIXTH_COLUMN = 6;
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

        try (Connection connection = con.getConnection()) {
            String query = "INSERT INTO grid (isBlackTurn, isFinished, roomId) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlackTurn);
            pstmt.setBoolean(SECOND_PARAMETER_INDEX, isFinished);
            pstmt.setLong(THIRD_PARAMETER_INDEX, roomId);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(FIRST_COLUMN);
            }
            throw new ChessException(ResponseCode.WRONG_ARGUMENTS_INSERT_ERROR);
        }
    }

    public GridDto findGridByGridId(long gridId) throws SQLException {
        String query = "SELECT * FROM grid WHERE gridId = ? LIMIT 1";
        try (Connection connection = con.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(FIRST_PARAMETER_INDEX, gridId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new GridDto(
                    rs.getLong(FIRST_COLUMN),
                    rs.getBoolean(SECOND_COLUMN),
                    rs.getBoolean(THIRD_COLUMN),
                    rs.getLong(FOURTH_COLUMN),
                    rs.getObject(FIFTH_COLUMN, LocalDateTime.class),
                    rs.getBoolean(SIXTH_COLUMN)
            );
        }
    }

    public GridDto findRecentGridByRoomId(long roomId) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "SELECT * FROM grid WHERE roomId = ? ORDER BY createdAt DESC LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(FIRST_PARAMETER_INDEX, roomId);
            pstmt.executeQuery();
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            GridDto gridDto = new GridDto(
                    rs.getLong(FIRST_COLUMN),
                    rs.getBoolean(SECOND_COLUMN),
                    rs.getBoolean(THIRD_COLUMN),
                    rs.getLong(FOURTH_COLUMN),
                    rs.getObject(FIFTH_COLUMN, LocalDateTime.class),
                    rs.getBoolean(SIXTH_COLUMN)
            );
            return gridDto;
        }
    }

    public void changeToStarting(long gridId) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "UPDATE grid SET isStarted = true WHERE gridId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(FIRST_PARAMETER_INDEX, gridId);
            pstmt.executeUpdate();
        }
    }

    public void changeTurn(long gridId, boolean isBlackTurn) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "UPDATE grid SET isBlackTurn = ? WHERE gridId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlackTurn);
            pstmt.setLong(SECOND_PARAMETER_INDEX, gridId);
            pstmt.executeUpdate();
        }
    }

    public void changeToFinished(long gridId) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "UPDATE grid SET isFinished = true WHERE gridId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(FIRST_PARAMETER_INDEX, gridId);
            pstmt.executeUpdate();
        }
    }
}
