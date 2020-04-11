package chess.dao;

import chess.dto.PieceDto;

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
    public void addPiece(PieceDto pieceDto) throws SQLException {
        String query = "INSERT INTO piece VALUES (?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDto.getPosition());
        pstmt.setString(2, pieceDto.getTeam());
        pstmt.setString(3, pieceDto.getPieceType());
        pstmt.executeUpdate();
        pstmt.close();
        dataSource.closeConnection(connection);
    }

    @Override
    public void updatePiece(PieceDto pieceDto) throws SQLException {
        String query = "UPDATE SET team=? pieceType=? WHERE position=?";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDto.getTeam());
        pstmt.setString(2, pieceDto.getPieceType());
        pstmt.setString(3, pieceDto.getPosition());
        pstmt.executeUpdate();
        pstmt.close();
        dataSource.closeConnection(connection);
    }

    @Override
    public List<PieceDto> findPiece() throws SQLException {
        String query = "SELECT * FROM piece";
        Connection connection = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<PieceDto> pieceDtos = new ArrayList<>();

        while (resultSet.next()) {
            String position = resultSet.getString("position");
            String team = resultSet.getString("team");
            String pieceType = resultSet.getString("pieceType");
            pieceDtos.add(new PieceDto(position, team, pieceType));
        }

        resultSet.close();
        pstmt.close();
        dataSource.closeConnection(connection);

        return pieceDtos;
    }
}
