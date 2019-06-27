package chess.domain.chess.dao;

import chess.domain.chess.game.ChessBoard;
import chess.domain.chess.game.ChessBoardConverter;
import chess.domain.chess.game.Team;
import chess.domain.chess.game.initializer.SettableChessBoardInitializer;
import chess.domain.chess.unit.Unit;
import chess.domain.chess.unit.UnitSymbolMapper;
import chess.domain.geometric.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardDAO {
    private Connection connection;

    public ChessBoardDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(ChessBoard chessBoard, Team team) throws SQLException {
        String boardInfo = ChessBoardConverter.convertsToString(chessBoard);
        String query = "INSERT INTO chess_board (status, team_id) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, boardInfo);
        pstmt.setInt(2, team.getTeamId());
        pstmt.executeUpdate();
    }

    public void add2(ChessBoard chessBoard, Team team, int roomId) throws SQLException {
        for (Position position : chessBoard.getUnits().keySet()) {
            String query = "INSERT INTO chess_board2 (roomId, positionX, positionY, unit) "
                    + "VALUES ( ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, roomId);
            pstmt.setInt(2, position.getX());
            pstmt.setInt(3, position.getY());
            pstmt.setString(4, chessBoard.getUnit(position).get().toString());
            pstmt.executeUpdate();
        }
    }

    public void update(ChessBoard chessBoard, Team team, int roomId) throws SQLException {
        String boardInfo = ChessBoardConverter.convertsToString(chessBoard);
        String query = "UPDATE chess_board SET status = ?, team_id = ? WHERE id = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, boardInfo);
        pstmt.setInt(2, team.getTeamId());
        pstmt.setInt(3, roomId);
        pstmt.executeUpdate();
    }

    public ChessBoard select(int roomId) throws SQLException {
        String query = "SELECT status, team_id FROM chess_board WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) throw new SQLException();

        String units = resultSet.getString("status");
        Team team = Team.getTeamById(resultSet.getInt("team_id"));

        return ChessBoardConverter.convertsStringToChessBoard(units, team);
    }

    public ChessBoard select2(int roomId, Team team) throws SQLException {
        String query = "SELECT positionX, positionY,unit  FROM chess_board2 WHERE roomId = ?";
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
        String query = "DELETE FROM chess_board2 WHERE roomId = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, roomId);
        pstmt.execute();
    }



    public List<Integer> getIdList() throws SQLException {
        String query = "SELECT id FROM chess_board ORDER BY id ASC LIMIT 100 OFFSET 0";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<Integer> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        return ids;
    }
}
