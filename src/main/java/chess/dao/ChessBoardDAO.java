package chess.dao;

import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDAO implements BoardDAO {

    private final Connection connection;

    public ChessBoardDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void savePieces(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO) {
        for (ChessDTO chessDto : chessDTOS) {
            savePiece(gameIdDTO, chessDto);
        }
    }

    private void savePiece(GameIdDTO gameIdDTO, ChessDTO chessDto) {
        final String sql = "insert into board(game_id, piece, position, color) values (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameIdDTO.getId());
            statement.setString(2, chessDto.getPiece().toLowerCase());
            statement.setString(3, chessDto.getPosition());
            statement.setString(4, chessDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChessDTO> findAllPiece(GameIdDTO gameIdDTO) {
        final String sql = "select * from board where game_id = (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameIdDTO.getId());
            ResultSet resultSet = statement.executeQuery();
            return toDTOFindPieces(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("[Error]: 모든 기물을 찾는 것을 실패했습니다.");
    }

    private List<ChessDTO> toDTOFindPieces(ResultSet resultSet) throws SQLException {
        List<ChessDTO> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(new ChessDTO(resultSet.getString("color"), resultSet.getString("piece"),
                    resultSet.getString("position")));
        }
        return pieces;
    }

    @Override
    public void deletePiece(String position, GameIdDTO gameIdDTO) {
        final String sql = "delete from board where position = (?) and game_id = (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position);
            statement.setInt(2, gameIdDTO.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
