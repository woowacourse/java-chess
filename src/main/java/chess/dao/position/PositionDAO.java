package chess.dao.position;


import chess.dao.SQLQuery;
import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionDAO implements PositionRepository {

    @Override
    public Position findByFileAndRank(File file, Rank rank) throws SQLException {
        String query = "SELECT * FROM position WHERE file_value = ? AND rank_value = ?";
        ResultSet resultSet = SQLQuery.select(query, file.getValue(), String.valueOf(rank.getValue()));
        if (!resultSet.next()) {
            return null;
        }
        return new Position(
            resultSet.getLong("id"),
            resultSet.getString("file_value"),
            resultSet.getString("rank_value"));
    }
}
