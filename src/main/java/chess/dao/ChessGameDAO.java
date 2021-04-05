package chess.dao;

import chess.domain.game.ChessGameEntity;
import chess.view.dto.ChessGameStatusDto;

import java.sql.*;
import java.util.Optional;

public class ChessGameDAO {

    private final ConnectionFactory factory;

    public ChessGameDAO() {
        factory = new ConnectionFactory();
    }

    public Optional<ChessGameEntity> findByStateIsBlackTurnOrWhiteTurn() {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM chess_game WHERE state in(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "BlackTurn");
            pstmt.setString(2, "WhiteTurn");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ChessGameEntity(rs.getLong("id"), rs.getString("state")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Long create() {
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO chess_game(state) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "BlackTurn");
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(Statement.RETURN_GENERATED_KEYS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("ChessGame이 제대로 생성되지 않았습니다");
    }

    public void deleteById(Long id) {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM chess_game WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateState(final Long id, final String state) {
        try (Connection con = factory.getConnection()) {
            String query = "UPDATE chess_game SET state = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, state);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameStatusDto findIsExistPlayingChessGameStatus() {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM chess_game WHERE state in(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "BlackTurn");
            pstmt.setString(2, "WhiteTurn");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return ChessGameStatusDto.exist();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ChessGameStatusDto.isNotExist();
    }

}
