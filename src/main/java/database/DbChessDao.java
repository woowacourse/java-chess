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
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final String KING_NAME = "King";
    private static final String KNIGHT_NAME = "Knight";
    private static final String QUEEN_NAME = "Queen";
    private static final String PAWN_NAME = "Pawn";
    private static final String BISHOP_NAME = "Bishop";

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

        final var query = "SELECT * FROM room";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            final var resultSet = preparedStatement.executeQuery();
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
        final var query = "INSERT INTO room(name) VALUES(?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
        final var query = "DELETE FROM room WHERE room_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveBoard(final Map<Position, Piece> board, final long roomId) {
        final var query = "INSERT INTO piece(room, name, team, " +
                "position_file, position_rank) VALUES(?,?,?,?,?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (final Map.Entry<Position, Piece> pieceEntry : board.entrySet()) {
                final Position position = pieceEntry.getKey();
                final Piece piece = pieceEntry.getValue();

                preparedStatement.setLong(1, roomId);
                preparedStatement.setString(2, piece.getName());
                preparedStatement.setString(3, piece.getTeam().name());
                preparedStatement.setString(4, position.getFile().name());
                preparedStatement.setString(5, position.getRank().name());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBoard(final long roomId) {
        final var query = "DELETE FROM piece WHERE room = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board findBoardByRoomId(final long roomId) {
        Map<Position, Piece> pieces = new HashMap<>();

        final var query = "SELECT * FROM piece WHERE room = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
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
        final var query = "DELETE FROM room";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece makePiece(final Team team, final String pieceName) {
        if (pieceName.equals(KING_NAME)) {
            return new King(team);
        }
        if (pieceName.equals(KNIGHT_NAME)) {
            return new Knight(team);
        }
        if (pieceName.equals(QUEEN_NAME)) {
            return new Queen(team);
        }
        if (pieceName.equals(PAWN_NAME)) {
            return new Pawn(team);
        }
        if (pieceName.equals(BISHOP_NAME)) {
            return new Bishop(team);
        }
        return new Rook(team);
    }
}
