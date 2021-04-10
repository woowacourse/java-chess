package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import chess.domain.ChessGame;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;
import chess.dto.BoardDto;
import chess.dto.UserDto;

public class ChessDao {
    private final SQLConnection sqlConnection;

    public ChessDao(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void addUser(UserDto userDto) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO user (user_name, user_password) VALUES (?, ?)")
        ) {
            preparedStatement.setString(1, userDto.getName());
            preparedStatement.setString(2, userDto.getPwd());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }
    }

    public UserDto findByUserId(String userId) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, userId);
            return makeUser(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    private UserDto makeUser(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery()) {
            if (!rs.next()) {
                return null;
            }
            return new UserDto(rs.getString("user_name"), rs.getString("user_password"));
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public String findUserIdByUser(UserDto userDto) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT user_id FROM user WHERE user_name = ? AND user_password = ?")
        ) {
            preparedStatement.setString(1, userDto.getName());
            preparedStatement.setString(2, userDto.getPwd());
            return makeUserId(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    private String makeUserId(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery()) {
            if (!rs.next()) {
                return null;
            }
            return rs.getString("user_id");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public UserDto findByUserNameAndPwd(String name, String password) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "select * from user where user_name = ? and user_password = ?;")
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            return makeUser(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    public void addBoard(String userId, String boardInfo, String color) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO board (user_id, board_info, color) VALUES (?, ?, ?)")
        ) {
            if (findBoard(userId) != null) {
                deleteBoard(userId);
            }
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, boardInfo);
            preparedStatement.setString(3, color);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    public void saveBoard(String userId, ChessGame chessGame, String color) {
        String boardInfo = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardInfo += chessGame.getBoard().get(Point.valueOf(i, j)).getName();
            }
        }
        addBoard(userId, boardInfo, color);
    }

    public void deleteBoard(String userId) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM board WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    public BoardDto findBoard(String userId) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM board WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, userId);
            return makeBoard(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    private BoardDto makeBoard(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery()) {
            Map<Point, Piece> chessBoard = new HashMap<>();
            if (!rs.next()) {
                return null;
            }
            String info = rs.getString("board_info");
            IntStream.rangeClosed(0, 7)
                .forEach(i -> IntStream.rangeClosed(0, 7).forEachOrdered(j -> chessBoard.put(
                    Point.valueOf(i, j), PieceType.findPiece(String.valueOf(info.charAt(i * 8 + j)))
                    ))
                );
            return new BoardDto(chessBoard);
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public Color findBoardNextTurn(String userId) {
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT color FROM board WHERE user_id = ?")
        ) {
            preparedStatement.setString(1, userId);
            return makeColor(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            throw new IllegalArgumentException("SQL Error 발생");
        }
    }

    private Color makeColor(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet rs = preparedStatement.executeQuery()) {
            if (!rs.next()) {
                return null;
            }
            if (rs.getString("color").equals(Color.BLACK.name())) {
                return Color.BLACK;
            }
            return Color.WHITE;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}