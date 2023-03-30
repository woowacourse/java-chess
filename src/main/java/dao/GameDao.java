package dao;

import domain.game.Game;
import domain.game.Position;
import domain.game.Side;
import domain.piece.Piece;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import util.FileMapper;
import util.PieceMapper;
import util.PositionMapper;
import util.RankMapper;
import util.SideMapper;

public class GameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final DBConnection dbConnection = new DBConnection();

    public void create(Game game) {
        String[] returnId = {"game_id"};
        Side sideOfTurn = game.getSideOfTurn();
        processQueryWithResultSetType("INSERT INTO chess_game(user_name, title, turn) VALUES (?, ?, ?)", returnId,
                preparedStatement -> {
                    preparedStatement.setString(1, game.getUserName());
                    preparedStatement.setString(2, game.getTitle());
                    preparedStatement.setString(3, sideOfTurn.name());
                    preparedStatement.executeUpdate();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys(); // 쿼리 실행 후 생성된 키 값 반환
                    if (resultSet.next()) {
                        int gameId = resultSet.getInt(1); // 키값 초기화
                        saveChessBoard(game, gameId);
                    }
                });
    }

    public void saveChessBoard(Game game) {
        processQuery("SELECT id FROM chess_game WHERE (user_name = ? AND title = ?)", preparedStatement -> {
            preparedStatement.setString(1, game.getUserName());
            preparedStatement.setString(2, game.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String gameId = resultSet.getString("id");
                this.saveChessBoard(game, Integer.parseInt(gameId));
            }
        });
    }

    public void saveChessBoard(Game game, int gameId) {
        Map<Position, Piece> chessBoard = game.getChessBoard();
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            String positionText = PositionMapper.convertPositionToText(positionPieceEntry.getKey());
            String pieceText = positionPieceEntry.getValue().getCategory().name();
            processQuery("UPDATE chess_game SET " + positionText + " = ? WHERE id = ?", preparedStatement -> {
                preparedStatement.setString(1, pieceText);
                preparedStatement.setString(2, String.valueOf(gameId));
                preparedStatement.executeUpdate();
            });
        }
        processQuery("UPDATE chess_game SET turn = ? WHERE id = ?", preparedStatement -> {
            preparedStatement.setString(1, game.getSideOfTurn().name());
            preparedStatement.setString(2, String.valueOf(gameId));
            preparedStatement.executeUpdate();
        });
    }

    public void deleteById(int chessBoardId) {
        processQuery("DELETE FROM chess_game WHERE id = ?", preparedStatement -> {
            preparedStatement.setString(1, String.valueOf(chessBoardId));
            preparedStatement.executeUpdate();
        });
    }

    public List<GameDto> findGamesByUserName(String userName) {
        List<GameDto> gameDtos = new ArrayList<>();
        processQuery("SELECT id, user_name ,title FROM chess_game WHERE user_name = ?", preparedStatement -> {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gameDtos.add(new GameDto(
                        resultSet.getString("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("title")
                ));
            }
        });
        return gameDtos;
    }

    public Game findGameById(String gameId) {
        return getGameOfQuery("SELECT * FROM chess_game WHERE id = ?", preparedStatement -> {
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Game game = null;
            if (resultSet.next()) {
                Map<Position, Piece> chessBoard = makeChessBoardByDB(resultSet);
                game = new Game(
                        chessBoard,
                        resultSet.getString("user_name"),
                        resultSet.getString("title"),
                        SideMapper.convertTextToSide(resultSet.getString("turn"))
                );
            }
            return game;
        });
    }

    private static Map<Position, Piece> makeChessBoardByDB(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (RankMapper rankMapper : RankMapper.values()) {
            for (FileMapper fileMapper : FileMapper.values()) {
                String positionText = fileMapper.getText() + rankMapper.getText();
                chessBoard.put(
                        PositionMapper.convertTextToPosition(positionText),
                        PieceMapper.convertTextForDBToPiece(resultSet.getString(positionText)));
            }
        }
        return chessBoard;
    }

    private void processQuery(String query, QueryProcessor queryProcessor) {
        try (final Connection connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            queryProcessor.process(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void processQueryWithResultSetType(String query, String[] resultSetType, QueryProcessor queryProcessor) {
        try (final Connection connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query, resultSetType)) {
            queryProcessor.process(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Game getGameOfQuery(String query, QueryGameSupplier queryGameSupplier) {
        try (final Connection connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            return queryGameSupplier.get(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
