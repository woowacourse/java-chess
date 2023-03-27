package chessgame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessgame.domain.Board;
import chessgame.domain.GameBoardFactory;
import chessgame.domain.Team;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class BoardDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addBoard() {
        final String query = "insert into tbl_board () values ();";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findRunningBoards() {
        final var query = "SELECT pk_boardno, fk_state_name FROM tbl_board WHERE fk_state_name != 'end' ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            List<Integer> boardNos = new ArrayList<>();
            while (resultSet.next()) {
                boardNos.add(resultSet.getInt(1));
            }
            return boardNos;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Board findBoardByNo(int boardNo) {
        final var query = "SELECT pk_no, fk_board_no, fk_file_value, fk_rank_value, fk_piece_name, fk_team FROM tbl_point WHERE fk_board_no = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, boardNo);

            Map<Point, Piece> points = new HashMap<>();
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                points.put(
                    Point.of(
                        resultSet.getInt("pk_no"),
                        File.find(resultSet.getString("fk_file_value").charAt(0)),
                        Rank.find(resultSet.getInt("fk_rank_value"))),
                    PieceType.find(
                        resultSet.getString("fk_piece_name"),
                        Team.find(resultSet.getString("fk_team")))
                );
            }
            return new Board(boardNo, points);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findLastBoardNo() {
        final var query = "SELECT pk_boardno, fk_state_name FROM tbl_board WHERE fk_state_name != 'end' ORDER BY pk_boardno desc ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            List<Integer> boardNos = new ArrayList<>();
            if (resultSet.next()) {
                return resultSet.getInt("pk_boardno");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("해당하는 게임보드가 없습니다.");
    }

    public void addPoints(int lastBoardNo) {

        final String query =
            "insert into tbl_point (fk_board_no, fk_team, fk_file_value, fk_rank_value, fk_piece_name) "
                + "values (?, ?, ?, ?, ?);";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Map<Point, Piece> board = GameBoardFactory.create();
            for (Point point : board.keySet()) {
                preparedStatement.setInt(1, lastBoardNo);
                preparedStatement.setString(2, board.get(point).team().getName());
                preparedStatement.setString(3, point.getFile());
                preparedStatement.setString(4, point.getRank());
                preparedStatement.setString(5, board.get(point).name());
                preparedStatement.executeUpdate();
            }

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
