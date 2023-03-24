package repository.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDto;
import dto.MoveHistoryDto;
import repository.connector.JdbcConnector;

public class JdbcGameDao implements GameDao{
    private final JdbcConnector connector;

    public JdbcGameDao(JdbcConnector connector) {
        this.connector = connector;
    }

    @Override
    public List<BoardDto> findBoardByRoomId(long roomId) {
        final String query = "SELECT square, piece, camp FROM room "
                + "JOIN board ON room._id = board.r_id "
                + "WHERE room._id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BoardDto> boardDtos = new ArrayList<>();
            while (resultSet.next()) {
                String square = resultSet.getString("square");
                String piece = resultSet.getString("piece");
                String camp = resultSet.getString("camp");
                boardDtos.add(new BoardDto(square, piece, camp));
            }
            return boardDtos;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveBoard(long game_id, List<BoardDto> boardDtos) {
        final String query = "INSERT INTO board (square, piece, camp, r_id) VALUES (?,?,?,?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (BoardDto boardDto : boardDtos) {
                preparedStatement.setString(1, boardDto.getSquare());
                preparedStatement.setString(2, boardDto.getPiece());
                preparedStatement.setString(3, boardDto.getCamp());
                preparedStatement.setLong(4, game_id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveMoveHistory(long game_id, MoveHistoryDto moveHistoryDto) {
        final String query = "INSERT INTO moveHistory (source, target, pieceOnTarget, r_id) VALUES (?,?,?,?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveHistoryDto.getSource());
            preparedStatement.setString(2, moveHistoryDto.getTarget());
            preparedStatement.setString(3, moveHistoryDto.getPiece());
            preparedStatement.setLong(4, game_id);
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateCurrentTurn(long gameId, String currentTurn) {
        final String query = "UPDATE room SET currentTurn = ? WHERE _id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currentTurn);
            preparedStatement.setLong(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String findCurrentTurnByGameName(long roomId) {
        final String query = "SELECT currentTurn FROM room WHERE _id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("currentTurn");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteBoardById(long gameId) {
        final String query = "DELETE from board WHERE r_id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<MoveHistoryDto> findMoveHistoryByGameId(long game_id) {
        final String query = "SELECT source, target, pieceOnTarget FROM moveHistory WHERE r_id = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, game_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MoveHistoryDto> moveHistoryDtos = new ArrayList<>();
            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String target = resultSet.getString("target");
                String piece = resultSet.getString("pieceOnTarget");
                moveHistoryDtos.add(new MoveHistoryDto(source, target, piece));
            }
            return moveHistoryDtos;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
