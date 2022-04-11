package chess.dao;

import chess.DBConnector;
import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDAO implements BoardDAO {

    @Override
    public void savePieces(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO) {
        try (Connection connection = DBConnector.getConnection()) {
            savePiece(chessDTOS, gameIdDTO, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePiece(List<ChessDTO> chessDTOS, GameIdDTO gameIdDTO, Connection connection) throws SQLException {
        final String sql = "insert into board(game_id, piece, position, color) values (?, ?, ?, ?)";
        for (ChessDTO chessDto : chessDTOS) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, gameIdDTO.getId());
                statement.setString(2, chessDto.getPiece().toLowerCase());
                statement.setString(3, chessDto.getPosition());
                statement.setString(4, chessDto.getColor());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public List<ChessDTO> findAllPiece(GameIdDTO gameIdDTO) {
        final String sql = "select * from board where game_id = (?)";

        try (Connection connection = DBConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, gameIdDTO.getId());
                ResultSet resultSet = statement.executeQuery();
                return toDTOFindPieces(resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        try (Connection connection = DBConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, position);
                statement.setInt(2, gameIdDTO.getId());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
