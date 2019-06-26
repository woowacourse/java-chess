package chess.domain.chess.dao;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.ChessUnitMapper;
import chess.domain.chess.Team;
import chess.domain.chess.initializer.SettableChessBoardInitializer;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ChessBoardDAO {
    private Connection connection;

    public ChessBoardDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(ChessBoard chessBoard, Team team) throws SQLException {
        String boardInfo = printCheckBoard(chessBoard);
        String query = "INSERT INTO chess_board (status, team_id) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, boardInfo);
        pstmt.setInt(2, team.getTeamId());
        pstmt.executeUpdate();
    }

    public String printCheckBoard(ChessBoard chessBoard) {
        String chessBoardInfo = "";
        for (int y = Position.MAX_POSITION - 1; y >= Position.MIN_POSITION; y--) {
            chessBoardInfo += addCheckBoardRowInfo(chessBoard, y);
        }
        return chessBoardInfo;
    }

    private String addCheckBoardRowInfo(ChessBoard chessBoard, int y) {
        String row = "";
        for (int x = Position.MIN_POSITION; x < Position.MAX_POSITION; x++) {
            Optional<Unit> optional = chessBoard.getUnit(Position.create(x, y));
            row += printUnit(optional);
        }
        return row;
    }

    private String printUnit(Optional<Unit> optional) {
        if (optional.isPresent()) {
            return optional.get().toString();
        }
        return ".";
    }

    public void update(ChessBoard chessBoard, Team team, int roomId) throws SQLException {
        String boardInfo = printCheckBoard(chessBoard);
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

        Map<Position, Unit> map = new HashMap<>();

        if (!resultSet.next()) throw new SQLException();

        String[] units = resultSet.getString("status").split("");
        Team team = Team.getTeamById(resultSet.getInt("team_id"));

        for (int y = 7; y >= 0; y--) {
            unitMapper(map, units, y);
        }

        return new ChessBoard(new SettableChessBoardInitializer(map, team));
    }

    public int getRowCount() throws SQLException {
        String query = "SELECT COUNT(1) FROM chess_board";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        int count = resultSet.getInt(1);
        return count;
    }

    private void unitMapper(Map<Position, Unit> map, String[] units, int y) {
        for (int x = 0; x <= 7; x++) {
            unitMapper1(map, units[y * 8 + x], x, y);
        }
    }

    private void unitMapper1(Map<Position, Unit> map, String unit, int x, int y) {
        Optional<Unit> optionalUnit = ChessUnitMapper.getUnit(unit);
        optionalUnit.ifPresent(unit1 -> map.put(Position.create(x, 7 - y), unit1));
    }

    public List<Integer> getIdList() throws SQLException {
        String query = "SELECT id FROM chess_board WHERE 1=1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<Integer> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getInt(1));
        }

        Collections.sort(ids);

        return ids;
    }
}
