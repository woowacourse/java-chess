package chess.dao;

import chess.entity.BoardEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    private final Connection connection = ConnectionManager.getConnection();

    public void save(final List<BoardEntity> boardEntities) {
        String insertSql = "insert into board (name, position_column_value, position_row_value, piece_name, piece_team_value) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            for (BoardEntity boardEntity : boardEntities) {
                insertStatement.setString(1, boardEntity.getName());
                insertStatement.setString(2, boardEntity.getPositionColumnValue());
                insertStatement.setInt(3, boardEntity.getPositionRowValue());
                insertStatement.setString(4, boardEntity.getPieceName());
                insertStatement.setString(5, boardEntity.getPieceTeamValue());
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        String deleteSql = "delete from board where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BoardEntity> load(final String name) {
        String selectSql = "select * from board where name=?";
        List<BoardEntity> boardEntities = new ArrayList<>();
        try {
            PreparedStatement loadStatement = connection.prepareStatement(selectSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            putPositionAndPiece(boardEntities, resultSet);
            validateBoardExist(boardEntities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardEntities;
    }

    private void putPositionAndPiece(final List<BoardEntity> boardEntities,
                                     final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String columnValue = resultSet.getString("position_column_value");
            int rowValue = resultSet.getInt("position_row_value");
            String pieceName = resultSet.getString("piece_name");
            String pieceTeamValue = resultSet.getString("piece_team_value");
            boardEntities.add(new BoardEntity(name, columnValue, rowValue, pieceName, pieceTeamValue));
        }
    }

    private void validateBoardExist(final List<BoardEntity> boardEntities) {
        if (boardEntities.size() == 0) {
            throw new IllegalArgumentException("[ERROR] Board 가 존재하지 않습니다.");
        }
    }
}
