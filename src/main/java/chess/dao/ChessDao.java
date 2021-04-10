package chess.dao;

import chess.controller.web.dto.piece.PieceResponseDto;
import chess.controller.web.dto.game.GameResponseDto;
import chess.controller.web.dto.game.GameRequestDto;
import chess.controller.web.dto.history.HistoryResponseDto;
import chess.controller.web.dto.score.ScoreResponseDto;
import chess.controller.web.dto.state.StateResponseDto;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;
import chess.domain.manager.GameStatus;
import chess.domain.piece.Piece;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessDao {
    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chessdb";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public Long saveGame(final GameRequestDto gameRequestDto) {
        final String query =
                "INSERT INTO game(room_name, white_username, black_username) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, gameRequestDto.getRoomName());
            pstmt.setString(2, gameRequestDto.getWhiteUsername());
            pstmt.setString(3, gameRequestDto.getBlackUsername());
            pstmt.executeLargeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long saveState(final ChessManager chessManager, final Long gameId) {
        final String query =
                "INSERT INTO state(gameId, turn_owner, turn_number, isPlaying) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, chessManager.turnOwner().name());
            pstmt.setInt(3, chessManager.turnNumber());
            pstmt.setBoolean(4, chessManager.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long saveScore(final GameStatus gameStatus, final Long gameId) {
        final String query =
                "INSERT INTO score(gameId, white_score, black_score) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setDouble(2, gameStatus.whiteScore());
            pstmt.setDouble(3, gameStatus.blackScore());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long[] savePieces(final Long gameId, final Map<Position, Piece> pieces) {
        final String query =
                "INSERT INTO piece(gameId, position, symbol) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (final Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                pstmt.setInt(1, gameId.intValue());
                pstmt.setString(2, entry.getKey().parseString());
                pstmt.setString(3, entry.getValue().getSymbol());
                pstmt.addBatch();
            }

            return pstmt.executeLargeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createHistory(final HistoryResponseDto history, final Long gameId) {
        final String query =
                "INSERT INTO history(gameId, move_command, turn_owner, turn_number, isPlaying) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameId.intValue());
            pstmt.setString(2, history.getMoveCommand());
            pstmt.setString(3, history.getTurnOwner());
            pstmt.setInt(4, history.getTurnNumber());
            pstmt.setBoolean(5, history.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameId) {
        final String query =
                "SELECT * from piece where gameId = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            List<PieceResponseDto> pieceResponseDtos = new ArrayList<>();

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    pieceResponseDtos.add(
                            new PieceResponseDto(resultSet.getString("symbol"),
                                    resultSet.getString("position")));
                }
                return pieceResponseDtos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameResponseDto findGameByGameId(final Long gameId) {
        final String query =
                "SELECT * from game where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new GameResponseDto(
                        resultSet.getString("white_username"),
                        resultSet.getString("black_username"),
                        resultSet.getString("room_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScoreResponseDto findScoreByGameId(final Long gameId) {
        final String query =
                "SELECT * from score where gameId = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new ScoreResponseDto(
                        resultSet.getDouble("white_score"),
                        resultSet.getDouble("black_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StateResponseDto findStateByGameId(final Long gameId) {
        final String query =
                "SELECT * from state where gameId = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new StateResponseDto(
                        resultSet.getString("turn_owner"),
                        resultSet.getInt("turn_number"),
                        resultSet.getBoolean("isPlaying"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        final String query =
                "SELECT * from history where gameId = ? ORDER BY id ASC";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<HistoryResponseDto> historyResponseDtos = new ArrayList<>();
                while (resultSet.next()) {
                    historyResponseDtos.add(new HistoryResponseDto(
                            resultSet.getString("move_command"),
                            resultSet.getString("turn_owner"),
                            resultSet.getInt("turn_number"),
                            resultSet.getBoolean("isPlaying")
                    ));
                }
                return historyResponseDtos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateScore(final GameStatus gameStatus, final Long gameId) {
        final String query =
                "UPDATE score SET white_score=?, black_score=? WHERE gameId=?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setDouble(1, gameStatus.whiteScore());
            pstmt.setDouble(2, gameStatus.blackScore());
            pstmt.setInt(3, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateState(final ChessManager chessManager, final Long gameId) {
        final String query =
                "UPDATE state SET turn_owner=?, turn_number=?, isPlaying=? WHERE gameId=?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, chessManager.turnOwner().name());
            pstmt.setInt(2, chessManager.turnNumber());
            pstmt.setBoolean(3, chessManager.isPlaying());
            pstmt.setInt(4, gameId.intValue());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateTargetPiece(final String target, final Piece sourcePiece, final Long gameId) {
        final String query =
                "UPDATE piece SET symbol = ? where gameId = ? && position = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, sourcePiece.getSymbol());
            pstmt.setInt(2, gameId.intValue());
            pstmt.setString(3, target);
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long updateSourcePiece(final String source, final Long gameId) {
        final String query =
                "UPDATE piece SET symbol = ? WHERE gameId=? && position=?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, ".");
            pstmt.setInt(2, gameId.intValue());
            pstmt.setString(3, source);
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
