package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dto.BoardDto;
import chess.dto.UserDto;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;

public class ChessDao {
    private final SQLConnection connection;

    public ChessDao(SQLConnection connection) {
        this.connection = connection;
    }

    public void addUser(UserDto userDto) throws SQLException {
        String query = "INSERT INTO user (user_name, user_password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getName());
        preparedStatement.setString(2, userDto.getPwd());
        preparedStatement.executeUpdate();
        connection.closeConnection();
    }

    public UserDto findByUserId(String userId) throws SQLException {
        String query = "SELECT * FROM user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new UserDto(rs.getString("user_name"), rs.getString("user_password"));
    }

    public String findUserIdByUser(UserDto userDto) throws SQLException {
        String query = "SELECT user_id FROM user WHERE user_name = ? AND user_password = ?";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getName());
        preparedStatement.setString(2, userDto.getPwd());
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return rs.getString("user_id");
    }

    public UserDto findByUserNameAndPwd(String name, String password) throws SQLException {
        String query = "select * from user where user_name = ? and user_password = ?;";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new UserDto(rs.getString("user_name"), rs.getString("user_password"));
    }

    public void addBoard(String userId, String boardInfo, Color color) throws SQLException {
        String query = "INSERT INTO board (user_id, board_info, color) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        if (findBoard(userId) != null) {
            deleteBoard(userId);
        }
        preparedStatement.setString(1, userId);
        preparedStatement.setString(2, boardInfo);
        preparedStatement.setString(3, color.name());
        preparedStatement.executeUpdate();
        connection.closeConnection();
    }

    public void deleteBoard(String userId) throws SQLException {
        String query = "DELETE FROM board WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userId);
        preparedStatement.executeUpdate();
        connection.closeConnection();
    }

    public BoardDto findBoard(String userId) throws SQLException {
        String query = "SELECT * FROM board WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        Map<Point, Piece> chessBoard = new HashMap<>();
        if (!rs.next()) {
            return null;
        }
        String info = rs.getString("board_info");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point point = Point.valueOf(i, j);
                Piece piece = PieceType.findPiece(String.valueOf(info.charAt(i * 8 + j)));  //"K"
                chessBoard.put(point, piece);
            }
        }
        return new BoardDto(chessBoard);
    }
}