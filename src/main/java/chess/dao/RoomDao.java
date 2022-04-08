package chess.dao;

import static chess.dao.util.StatementUtil.setParameter;

import chess.dao.util.ConnectionGenerator;
import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.CurrentTurnDto;
import chess.dto.RoomStatusDto;
import chess.exception.SQLQueryException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao {

    public int save(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "INSERT INTO room (name, game_status, current_turn) VALUE (?, ?, ?)";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, roomName, gameStatus.getValue(), currentTurn.getValue());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방을 저장하는데 실패했습니다.", e);
        }
    }

    public boolean isExistName(final String roomName) {
        final String sql = "SELECT name FROM room WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, roomName);
            try (final ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방의 존재 확인에 실패했습니다.", e);
        }
    }

    public CurrentTurnDto findCurrentTurnByName(final String roomName) {
        final String sql = "SELECT name, current_turn FROM room WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, roomName);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return CurrentTurnDto.from(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방의 현재 턴 조회에 실패했습니다.", e);
        }
    }

    public RoomStatusDto findStatusByName(final String roomName) {
        final String sql = "SELECT name, game_status FROM room WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, roomName);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return RoomStatusDto.from(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방의 상태 조회에 실패했습니다.", e);
        }
    }

    public int delete(final String roomName) {
        final String sql = "DELETE FROM room WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, roomName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방 삭제에 실패했습니다.", e);
        }
    }

    public int update(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "UPDATE room SET game_status = ?, current_turn = ? WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, gameStatus.getValue(), currentTurn.getValue(), roomName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방 업데이트에 실패했습니다.", e);
        }
    }

    public int updateStatusTo(final String roomName, final GameStatus gameStatus) {
        final String sql = "UPDATE room SET game_status = ? WHERE name = ?";

        try (final PreparedStatement statement = ConnectionGenerator.getStatement(sql)) {
            setParameter(statement, gameStatus.getValue(), roomName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException("방을 상태 업데이트에 실패했습니다.", e);
        }
    }
}
