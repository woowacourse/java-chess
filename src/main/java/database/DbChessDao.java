package database;

import domain.Room;
import domain.board.Board;
import domain.piece.*;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DbChessDao implements ChessDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final List<Piece> pieces = List.of(new King(Team.BLACK),
            new Bishop(Team.BLACK),
            new Queen(Team.BLACK),
            new Pawn(Team.BLACK),
            new Rook(Team.BLACK),
            new Knight(Team.BLACK),
            new King(Team.WHITE),
            new Bishop(Team.WHITE),
            new Queen(Team.WHITE),
            new Pawn(Team.WHITE),
            new Rook(Team.WHITE),
            new Knight(Team.WHITE));

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Room> findAllRooms() {
        final List<Room> result = new ArrayList<>();

        final String query = "SELECT * FROM room";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Room(
                        resultSet.getLong("room_id"),
                        resultSet.getString("name")));
            }
            return result;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long saveRoom(final Room room) {
        final String query = "INSERT INTO room(name) VALUES(?)";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.executeUpdate();

            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRoom(final long roomId) {
        final String query = "DELETE FROM room WHERE room_id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveBoard(final Map<Position, Piece> board, final long roomId) {
        final String query = "INSERT INTO piece(room, name, team, " +
                "position_file, position_rank) VALUES(?,?,?,?,?)";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (final Map.Entry<Position, Piece> pieceEntry : board.entrySet()) {
                final Position position = pieceEntry.getKey();
                final Piece piece = pieceEntry.getValue();

                preparedStatement.setLong(1, roomId);
                preparedStatement.setString(2, piece.getName());
                preparedStatement.setString(3, piece.getTeam().name());
                preparedStatement.setString(4, position.getFile().name());
                preparedStatement.setString(5, position.getRank().name());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBoard(final long roomId) {
        final String query = "DELETE FROM piece WHERE room = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board findBoardByRoomId(final long roomId) {
        final Map<Position, Piece> pieces = new HashMap<>();

        final String query = "SELECT * FROM piece WHERE room = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final File file = File.valueOf(resultSet.getString("position_file"));
                final Rank rank = Rank.valueOf(resultSet.getString("position_rank"));
                final Position position = Position.of(file, rank);

                final Team team = Team.valueOf(resultSet.getString("team"));
                final Piece piece = makePiece(team, resultSet.getString("name"));

                pieces.put(position, piece);
            }

            return Board.load(pieces);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        final String query = "DELETE FROM room";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece makePiece(final Team team, final String pieceName) {
        return pieces.stream()
                .filter(piece -> piece.isTeam(team))
                .filter(piece -> piece.getName().equals(pieceName))
                .findAny()
                .orElseThrow();
    }
}
