package chess.dao;

import chess.dao.entity.BoardEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    public void save(final List<BoardEntity> boardEntities) {
        String insertSql = "insert into board (name, position_column_value, position_row_value, piece_name, piece_team_value) values (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
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
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BoardEntity> load(final String name) {
        String selectSql = "select * from board where name=?";
        List<BoardEntity> boardEntities = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement loadStatement = connection.prepareStatement(selectSql)) {
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            addBoardEntity(boardEntities, resultSet);
            validateBoardExist(boardEntities);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardEntities;
    }

    private void addBoardEntity(final List<BoardEntity> boardEntities,
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
