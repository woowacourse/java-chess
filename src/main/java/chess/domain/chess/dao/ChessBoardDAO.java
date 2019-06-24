package chess.domain.chess.dao;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.ChessUnitMapper;
import chess.domain.chess.Team;
import chess.domain.chess.initializer.ChessBoardDB;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        for (int i = Position.MIN_POSITION; i < Position.MAX_POSITION; i++) {
            chessBoardInfo += addCheckBoardRowInfo(chessBoard, i);
        }
        return chessBoardInfo;
    }

    private String addCheckBoardRowInfo(ChessBoard chessBoard, int i) {
        String row = "";
        for (int j = Position.MIN_POSITION; j < Position.MAX_POSITION; j++) {
            Optional<Unit> optional = chessBoard.getUnit(Position.create(Position.MAX_POSITION - 1 - j, i));
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

    public void update(ChessBoard chessBoard, Team team) throws SQLException {
        String boardInfo = printCheckBoard(chessBoard);
        String query = "UPDATE chess_board SET status = ?, team_id = ? " +
                "ORDER BY id DESC " +
                "LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, boardInfo);
        pstmt.setInt(2, team.getTeamId());
        pstmt.executeUpdate();
    }

    public ChessBoard select() throws SQLException {
        String query = "SELECT status, team_id FROM chess_board ORDER BY id DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        Map<Position, Unit> map = new HashMap<>();

        if (!resultSet.next()) return null;

        String[] units = resultSet.getString("status").split("");

        for (int i = 0; i < 8; i++) {
            unitMapper(map, units, i);
        }

        return new ChessBoard(new ChessBoardDB(map));
    }

    private void unitMapper(Map<Position, Unit> map, String[] units, int i) {
        for (int j = 0; j < 8; j++) {
            unitMapper1(map, units[j * 8 + i], i, j);
        }
    }

    private void unitMapper1(Map<Position, Unit> map, String unit, int i, int j) {
        Optional<Unit> optionalUnit = ChessUnitMapper.getUnit(unit);
        optionalUnit.ifPresent(unit1 -> map.put(Position.create(i, j), unit1));
    }
}
