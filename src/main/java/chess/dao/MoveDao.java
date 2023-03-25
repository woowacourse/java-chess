package chess.dao;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveDao {

    private final Connection connection;

    public MoveDao() {
        this.connection = ConnectionGenerator.getConnection();
    }

    public void save(final MoveDto moveDto) {
        final String query = "INSERT INTO move (source, target) VALUES(?, ?)";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (final SQLException error) {
            throw new IllegalArgumentException("기보 저장 중 에러가 발생했습니다.");
        }
    }

    public List<MoveDto> restart() {
        final String query = "SELECT * FROM move";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<MoveDto> result = new ArrayList<>();

            while (resultSet.next()) {
                final String source = resultSet.getString("source");
                final String target = resultSet.getString("target");
                MoveDto moveDto = new MoveDto(source, target);

                result.add(moveDto);
            }
            return result;
        } catch (final SQLException error) {
            throw new IllegalArgumentException("기보 가져오는 중 에러가 발생했습니다.");
        }
    }

    public void clear() {
        final String query = "DELETE FROM move";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException error) {
            System.out.println(error);
            throw new IllegalArgumentException("기보 초기화 중 에러가 발생했습니다");
        }
    }
}
