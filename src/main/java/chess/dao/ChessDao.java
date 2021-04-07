package chess.dao;

import chess.controller.dto.*;
import chess.domain.manager.ChessManager;
import chess.domain.manager.GameStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public Long createNewGame(final NewGameRequestDto newGameRequestDto) {
        final String query =
                "INSERT INTO game(room_name, white_username, black_username) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newGameRequestDto.getRoomName());
            pstmt.setString(2, newGameRequestDto.getWhiteUsername());
            pstmt.setString(3, newGameRequestDto.getBlackUsername());
            pstmt.executeLargeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createState(final ChessManager chessManager, final Long gameID) {
        final String query =
                "INSERT INTO state(gameID, turn_owner, turn_number, isPlaying) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameID.intValue());
            pstmt.setString(2, chessManager.turnOwner().name());
            pstmt.setInt(3, chessManager.turnNumber());
            pstmt.setBoolean(4, chessManager.isPlaying());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createScore(GameStatus gameStatus, Long gameID) {
        final String query =
                "INSERT INTO score(gameID, white_score, black_score) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt =
                     connection.prepareStatement(query)) {

            pstmt.setInt(1, gameID.intValue());
            pstmt.setDouble(2, gameStatus.whiteScore());
            pstmt.setDouble(3, gameStatus.blackScore());
            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createPieces(Long gameID, String position, String symbol) {
        final String query =
                "INSERT INTO piece(gameID, position, symbol) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, gameID.intValue());
            pstmt.setString(2, position);
            pstmt.setString(3, symbol);

            return pstmt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameID) {
        final String query =
                "SELECT * from piece where gameID = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameID.intValue());
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

    public GameResponseDto findGameByGameId(Long gameID) {
        final String query =
                "SELECT * from game where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameID.intValue());
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

    public ScoreResponseDto findScoreByGameId(Long gameID) {
        final String query =
                "SELECT * from score where gameId = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameID.intValue());
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

    public StateResponseDto findStateByGameId(Long gameID) {
        final String query =
                "SELECT * from state where gameId = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameID.intValue());
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
}
