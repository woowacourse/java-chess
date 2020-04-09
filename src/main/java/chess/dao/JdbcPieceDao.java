package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceDao implements PieceDao {
    private final DataSource dataSource;

    public JdbcPieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPiece(PieceEntity pieceEntity) throws SQLException {
        String query = "INSERT INTO piece VALUES (?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceEntity.getPosition());
        pstmt.setString(2, pieceEntity.getTeam());
        pstmt.setString(3, pieceEntity.getPieceType());
        pstmt.executeUpdate();
        pstmt.close();
        dataSource.closeConnection(connection);
    }

    @Override
    public void updatePiece(PieceEntity pieceEntity) throws SQLException {
        String query = "UPDATE SET team=? pieceType=? WHERE position=?";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceEntity.getTeam());
        pstmt.setString(2, pieceEntity.getPieceType());
        pstmt.setString(3, pieceEntity.getPosition());
        pstmt.executeUpdate();
        pstmt.close();
        dataSource.closeConnection(connection);
    }

    @Override
    public List<PieceEntity> findPiece() throws SQLException {
        String query = "SELECT * FROM piece";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<PieceEntity> pieceEntities = new ArrayList<>();

        while (resultSet.next()) {
            String position = resultSet.getString("position");
            String team = resultSet.getString("team");
            String pieceType = resultSet.getString("pieceType");
            pieceEntities.add(new PieceEntity(position, team, pieceType));
        }

        resultSet.close();
        pstmt.close();
        dataSource.closeConnection(connection);

        return pieceEntities;
    }
}
