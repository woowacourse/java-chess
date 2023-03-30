package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Square;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.piece.Role;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.service.RoomNumber;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ChessDao {


    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static ChessDao instance;

    private ChessDao() {
    }

    public static ChessDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ChessDao();
        }
        return instance;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(final ChessBoard chessBoard, final RoomNumber roomNumber) {
        clear(roomNumber);
        final int turn = chessBoard.getTurn().getTurn();
        int insertedRoomNumber = saveTurn(turn, roomNumber.getRoomNumber());
        final List<Square> squares = chessBoard.getSquares();
        for (Square square : squares) {
            saveOneSquare(square, insertedRoomNumber);
        }
    }

    public void clear(final RoomNumber roomNumber) {
        final String query = "DELETE FROM board where room_number = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomNumber.getRoomNumber());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveOneSquare(final Square square, int roomNumber) {

        final String query = "INSERT INTO board(piece_rank, piece_file, piece_role, piece_team, room_number) VALUES (?, ?, ?, ?, ?)";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String rank = square.getPosition().getRank().name();
            String file = square.getPosition().getFile().name();
            String role = square.getPiece().getRole().name();
            String team = square.getPiece().getTeam().name();

            preparedStatement.setString(1, rank);
            preparedStatement.setString(2, file);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, team);
            preparedStatement.setInt(5, roomNumber);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int saveTurn(final int turn, final int roomNumber) {

        if (roomNumber == 0) {
            final String query = "INSERT INTO room (turn) VALUES (?)";
            try (final Connection connection = getConnection();
                 final PreparedStatement preparedStatement = connection.prepareStatement(query,
                         Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, turn);
                preparedStatement.executeUpdate();
                final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                throw new IllegalStateException("정상적으로 insert 되지 않았습니다.");
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
        final String query = "UPDATE room SET turn = ? WHERE id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, turn);
            preparedStatement.setInt(2, roomNumber);
            preparedStatement.execute();
            return roomNumber;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> fetchAllRoomNumbers() {
        final List<Integer> roomNumbers = new ArrayList<>();
        final String query = "SELECT id from room";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roomNumbers.add(resultSet.getInt(1));
            }
            return roomNumbers;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessGame fetchGame(final RoomNumber roomNumber) {
        final Turn turn = fetchTurn(roomNumber.getRoomNumber());
        if (turn.isFirst()) {
            return null;
        }
        final List<Square> squares = new ArrayList<>();
        fetchBoard(squares, roomNumber.getRoomNumber());
        return new ChessGame(new ChessBoard(squares, turn));
    }

    private Turn fetchTurn(final int roomNumber) {
        final String stateSelectQuery = "SELECT * from room where id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(stateSelectQuery)) {
            preparedStatement.setInt(1, roomNumber);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Turn(Integer.parseInt(resultSet.getString(2)));
            }
            return new Turn();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchBoard(final List<Square> squares, final int roomNumber) {
        final String boardSelectQuery = "SELECT * FROM board where room_number = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(boardSelectQuery)) {
            preparedStatement.setInt(1, roomNumber);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fetchOneSquare(squares, resultSet);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchOneSquare(final List<Square> squares, final ResultSet resultSet) throws SQLException {
        final Rank rank = Rank.valueOf(resultSet.getString(2));
        final File file = File.valueOf(resultSet.getString(3));
        final Role role = Role.valueOf(resultSet.getString(4));
        final Team team = Team.valueOf(resultSet.getString(5));
        squares.add(new Square(Position.of(file, rank), role.createPiece(team)));
    }
}
