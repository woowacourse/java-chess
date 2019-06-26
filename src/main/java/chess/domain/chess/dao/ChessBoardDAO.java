package chess.domain.chess.dao;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.ChessUnitMapper;
import chess.domain.chess.Team;
import chess.domain.chess.initializer.ChessBoardDB;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;
import javafx.geometry.Pos;

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
        String query = "INSERT INTO chess_board (status, team_id) VALUES (?, ?)";
        executeQuery(chessBoard, team, query);
    }

    public void update(ChessBoard chessBoard, Team team) throws SQLException {
        String query = "UPDATE chess_board SET status = ?, team_id = ? ORDER BY id DESC LIMIT 1";
        executeQuery(chessBoard, team, query);
    }

    private void executeQuery(ChessBoard chessBoard, Team team, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        String boardInfo = printCheckBoard(chessBoard);

        preparedStatement.setString(1, boardInfo);
        preparedStatement.setInt(2, team.getTeamId());
        preparedStatement.executeUpdate();
    }

    private String printCheckBoard(ChessBoard chessBoard) {
        StringBuilder chessBoardInfo = new StringBuilder();
        for (int y = Position.MAX_POSITION - 1; y >= Position.MIN_POSITION; y--) {
            chessBoardInfo.append(addCheckBoardRowInfo(chessBoard, y));
        }
        return chessBoardInfo.toString();
    }

    private StringBuilder addCheckBoardRowInfo(ChessBoard chessBoard, int y) {
        StringBuilder row = new StringBuilder();
        for (int x = Position.MIN_POSITION; x < Position.MAX_POSITION; x++) {
            Optional<Unit> optional = chessBoard.getOptionalUnit(Position.create(x, y));
            row.append(printUnit(optional));
        }
        return row;
    }

    private String printUnit(Optional<Unit> optional) {
        if (optional.isPresent()) {
            return optional.get().toString();
        }
        return ".";
    }

    public ChessBoard selectRecentGame() throws SQLException {
        String query = "SELECT status, team_id FROM chess_board ORDER BY id DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        Map<Position, Unit> map = new HashMap<>();

        if (!resultSet.next()) return null;

        String[] units = resultSet.getString("status").split("");
        Team team = Team.getTeamById(resultSet.getInt("team_id"));


        for (int y = Position.MAX_POSITION - 1; y >= Position.MIN_POSITION; y--) {
            unitMapper(map, units, y);
        }

        return new ChessBoard(new ChessBoardDB(map, team));
    }

    private void unitMapper(Map<Position, Unit> map, String[] units, int y) {
        for (int x = Position.MIN_POSITION; x < Position.MAX_POSITION; x++) {
            unitMapper(map, units[y * Position.MAX_POSITION + x], x, y);
        }
    }

    private void unitMapper(Map<Position, Unit> map, String text, int x, int y) {
        Optional<Unit> optionalUnit = ChessUnitMapper.getUnit(text);
        optionalUnit.ifPresent(unit -> map.put(Position.create(x, Position.MAX_POSITION - 1 - y), unit));
    }
}
