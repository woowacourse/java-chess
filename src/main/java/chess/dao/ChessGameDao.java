package chess.dao;

import chess.dto.ResponseDto;
import chess.domain.game.Player;

import java.sql.*;
import java.util.Objects;

public class ChessGameDao {
    private final DBConnector DBConnector = new DBConnector();

    public void saveGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);

        Connection connection = DBConnector.getConnection();
        String query = "INSERT INTO chessgame (turn, white_score, black_score) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getWhiteScore());
            pstmt.setDouble(3, responseDto.getResult().getBlackScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("체스 게임 정보를 저장할 수 없습니다.");
        }
    }

    public void updateGame(ResponseDto responseDto) throws SQLException {
        Objects.requireNonNull(responseDto);

        Connection connection = DBConnector.getConnection();
        String query = "UPDATE chessgame " +
                "SET turn = ?, white_score = ?, black_score = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);

        try (connection; pstmt) {
            pstmt.setString(1, responseDto.getTurn().name());
            pstmt.setDouble(2, responseDto.getResult().getWhiteScore());
            pstmt.setDouble(3, responseDto.getResult().getBlackScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw  new SQLException("게임 정보를 갱신할 수 없습니다.");
        }
    }


    public boolean isGameExists() throws SQLException {
        Connection connection = DBConnector.getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM chessgame";
        ResultSet rs = stmt.executeQuery(query);

        try (connection; stmt; rs) {
            return rs.next();
        } catch (SQLException e) {
            throw new SQLException("게임 존재 여부를 찾을 수 없습니다.");
        }
    }

    public Player getTurn() throws SQLException {
        Connection connection = DBConnector.getConnection();

        Statement stmt = connection.createStatement();
        String query = "SELECT turn FROM chessgame";
        ResultSet rs = stmt.executeQuery(query);

        try (connection; stmt; rs) {
            String turn = null;
            if (rs.next()) {
                turn = rs.getString("turn");
            }
            return Player.of(turn);
        } catch (SQLException e) {
            throw new SQLException("Player 차례를 찾을 수 없습니다.");
        }
    }

    public void deleteChessGame() throws SQLException {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM chessgame";

        try (connection; stmt) {
            stmt.executeUpdate(query);
        } catch (SQLException e){
            throw new SQLException("체스 게임 정보를 지울 수 없습니다.");
        }
    }
}