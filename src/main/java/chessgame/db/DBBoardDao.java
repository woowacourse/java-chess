package chessgame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessgame.domain.Board;
import chessgame.domain.Game;
import chessgame.domain.GameBoardFactory;
import chessgame.domain.Team;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class DBBoardDao implements BoardDao {
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
        final var query = "SELECT board_no, state FROM tbl_board WHERE state != 'end' ";
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
        final var query = "SELECT piece_file, piece_rank, team, piece_type FROM tbl_point WHERE board_no = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, boardNo);
            final var resultSet = preparedStatement.executeQuery();
            Map<Point, Piece> points = loadPoints(resultSet);
            return new Board(boardNo, points);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Point, Piece> loadPoints(ResultSet resultSet) throws SQLException {
        Map<Point, Piece> points = new HashMap<>();
        while (resultSet.next()) {
            File pieceFile = File.valueOf(resultSet.getString(1));
            Rank pieceRank = Rank.valueOf(resultSet.getString(2));
            Point piecePoint = Point.of(pieceFile, pieceRank);
            Team pieceTeam = Team.find(resultSet.getString(3));
            Piece piece = PieceType.find(resultSet.getString(4), pieceTeam);

            points.put(piecePoint, piece);
        }
        return points;
    }

    public int findLastBoardNo() {
        final var query = "SELECT board_no, state FROM tbl_board WHERE state != 'end' ORDER BY board_no desc ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("board_no");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("해당하는 게임보드가 없습니다.");
    }

    public void addPoints(int boardNo) {

        final String query =
            "insert into tbl_point (board_no, team, piece_file, piece_rank, piece_type) "
                + "values (?, ?, ?, ?, ?);";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Map<Point, Piece> board = GameBoardFactory.create();
            for (Point point : board.keySet()) {
                preparedStatement.setInt(1, boardNo);
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

    @Override
    public void updatePoints(Board board) {
        deletePoints(board.number());
        loadPoints(board);
    }

    private void deletePoints(int boardNo) {
        final String query = "delete from tbl_point where board_no = ? ;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, boardNo);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPoints(Board board) {
        final String query =
            "insert into tbl_point (board_no, team, piece_file, piece_rank, piece_type) "
                + "values (?, ?, ?, ?, ?);";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            Map<Point, Piece> points = board.getBoard();
            for (Point point : points.keySet()) {
                preparedStatement.setInt(1, board.number());
                preparedStatement.setString(2, points.get(point).team().getName());
                preparedStatement.setString(3, point.getFile());
                preparedStatement.setString(4, point.getRank());
                preparedStatement.setString(5, points.get(point).name());
                preparedStatement.executeUpdate();
            }

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateBoardState(Game game) {
        final String query = "update tbl_board set state = ? where board_no = ? ;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, game.state());
            preparedStatement.setInt(2, game.board().number());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getState(int boardNo) {
        final var query = "SELECT state FROM tbl_board WHERE board_no = ? ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, boardNo);
            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("해당하는 게임보드가 없습니다.");
    }

    @Override
    public boolean isNotRunning(int boardNo) {
        final var query = "SELECT board_no FROM tbl_board WHERE state != 'end' and board_no = ? ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, boardNo);
            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return false;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
