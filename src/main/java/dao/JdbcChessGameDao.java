package dao;

import domain.game.GameState;
import domain.game.PieceType;
import domain.piece.*;
import dto.dao.ChessGameDaoResponseDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcChessGameDao implements ChessGameDao {

    private final ConnectionGenerator connectionGenerator;

    public JdbcChessGameDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    @Override
    public Long createRoom() {
        try (Connection connection = connectionGenerator.getConnection()) {
            final String createQuery = "INSERT INTO game_room(status, current_turn) VALUES(?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, GameState.RUN.name());
                preparedStatement.setString(2, Side.WHITE.name());

                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    connection.commit();
                    return generatedKeys.getLong(1);
                }
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("게임 방 생성 실패");
    }

    @Override
    public boolean saveChessBoard(Map<Position, Piece> board, Side currentTurn, Long roomId) {
        final String saveQuery = "INSERT INTO pieces(piece_type, side, piece_rank, piece_file, game_room_id_fk) VALUES(?,?,?,?,?)";
        try (Connection connection = connectionGenerator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);) {
            try {
                for (Map.Entry<Position, Piece> pieces : board.entrySet()) {
                    File file = pieces.getKey().getFile();
                    Rank rank = pieces.getKey().getRank();
                    PieceType pieceType = pieces.getValue().getPieceType();
                    Side pieceSide = pieces.getValue().getSide();

                    preparedStatement.setString(1, pieceType.name());
                    preparedStatement.setString(2, pieceSide.name());
                    preparedStatement.setString(3, rank.getText());
                    preparedStatement.setString(4, file.getText());
                    preparedStatement.setLong(5, roomId);

                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGameDaoResponseDto loadGame(Long roomId) {
        Map<Position, Piece> board = new HashMap<>();
        final String loadQuery = "select piece_type, p.side, current_turn, piece_rank, piece_file from pieces p join game_room gr on" +
                " p.game_room_id_fk = gr.game_room_id where p.game_room_id_fk = ?";
        Side lastTurn = null;

        try (Connection connection = connectionGenerator.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(loadQuery);
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Position position = Position.of(resultSet.getString("piece_file"), resultSet.getString("piece_rank"));
                Piece piece = new PieceMapper().create(PieceType.valueOf(resultSet.getString("piece_type")), Side.valueOf(resultSet.getString("side")));
                board.put(position, piece);
                lastTurn = Side.valueOf(resultSet.getString("current_turn"));
            }
            return new ChessGameDaoResponseDto(board, lastTurn, GameState.RUN);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Long> findAllGameRooms() {
        String selectQuery = "SELECT game_room_id, status FROM game_room";
        try (Connection connection = connectionGenerator.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(resultSet.getLong("game_room_id"));
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateChessBoard(Long roomId, Map<Position, Piece> board) {
        final String saveQuery = "UPDATE pieces SET piece_type = ?,side = ?,piece_rank = ?,piece_file = ? where game_room_id_fk = ? and piece_file = ? and piece_rank = ?";
        try (Connection connection = connectionGenerator.getConnection()) {
            try {
                for (Map.Entry<Position, Piece> pieces : board.entrySet()) {
                    File file = pieces.getKey().getFile();
                    Rank rank = pieces.getKey().getRank();
                    PieceType pieceType = pieces.getValue().getPieceType();
                    Side pieceSide = pieces.getValue().getSide();

                    PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);

                    preparedStatement.setString(1, pieceType.name());
                    preparedStatement.setString(2, pieceSide.name());
                    preparedStatement.setString(3, rank.getText());
                    preparedStatement.setString(4, file.getText());
                    preparedStatement.setLong(5, roomId);
                    preparedStatement.setString(6, file.getText());
                    preparedStatement.setString(7, rank.getText());

                    preparedStatement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGameRoom(Long roomId, Side currentTurn, GameState state) {
        final String saveQuery = "UPDATE game_room SET status = ?,current_turn = ? where game_room_id = ?";
        try (Connection connection = connectionGenerator.getConnection()) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);

                preparedStatement.setString(1, state.name());
                preparedStatement.setString(2, currentTurn.name());
                preparedStatement.setLong(3, roomId);

                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean hasGame(Long roomId) {
        final String loadQuery = "SELECT piece_type FROM pieces WHERE game_room_id_fk = ?";

        try (Connection connection = connectionGenerator.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(loadQuery);
            preparedStatement.setLong(1, roomId);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteGameRoom(Long roomId) {
        final String deleteQuery = "DELETE FROM game_room WHERE game_room_id = ?";
        try (Connection connection = connectionGenerator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
            try {
                preparedStatement.setLong(1, roomId);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                throw new RuntimeException(sqlException);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
