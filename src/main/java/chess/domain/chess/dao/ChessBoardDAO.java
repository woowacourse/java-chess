package chess.domain.chess.dao;

import chess.domain.chess.game.ChessBoard;
import chess.domain.chess.game.Team;
import chess.domain.chess.game.initializer.SettableChessBoardInitializer;
import chess.domain.chess.unit.Unit;
import chess.domain.chess.unit.UnitSymbolMapper;
import chess.domain.geometric.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDAO {
    private Connection connection;

    public ChessBoardDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(ChessBoard chessBoard, Team team, int roomId) throws SQLException {
        for (Position position : chessBoard.getUnits().keySet()) {
            String query = "INSERT INTO chess_board (roomId, positionX, positionY, unit) "
                    + "VALUES ( ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, roomId);
            pstmt.setInt(2, position.getX());
            pstmt.setInt(3, position.getY());
            pstmt.setString(4, chessBoard.getUnit(position).get().toString());
            pstmt.executeUpdate();
        }
    }

    public ChessBoard select(int roomId, Team team) throws SQLException {
        String query = "SELECT positionX, positionY,unit  FROM chess_board WHERE roomId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet resultSet = pstmt.executeQuery();

        Map<Position, Unit> map = new HashMap<>();

        while (resultSet.next()) {
            int positionX = resultSet.getInt("positionX");
            int positionY = resultSet.getInt("positionY");
            String unit = resultSet.getString("unit");
            map.put(Position.create(positionX, positionY), UnitSymbolMapper.getUnit(unit).get());
        }
        return new ChessBoard(new SettableChessBoardInitializer(map, team));
    }

    public void delete(int roomId) throws SQLException {
        String query = "DELETE FROM chess_board WHERE roomId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        pstmt.execute();
    }
}
