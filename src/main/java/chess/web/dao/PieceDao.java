package chess.web.dao;

import static chess.web.dao.DBConnector.getConnection;

import chess.web.dto.ChessBoardDto;
import chess.web.dto.ChessGameDto;
import chess.web.dto.PieceDto;
import chess.web.dto.PositionDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class PieceDao {
    public void save(ChessGameDto chessGameDto) {
        String sql = "insert into piece (type, team, `rank`, file, game_name) values (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ChessBoardDto chessBoard = chessGameDto.getChessBoard();
            String gameName = chessGameDto.getGameName();

            Map<PositionDto, PieceDto> cells = chessBoard.getCells();

            updateCells(gameName, cells, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCells(String gameName, Map<PositionDto, PieceDto> cells, PreparedStatement statement) throws SQLException {
        for (PositionDto positionDto : cells.keySet()) {
            statement.setString(1, cells.get(positionDto).getSymbol());
            statement.setString(2, cells.get(positionDto).getTeam());
            statement.setInt(3, positionDto.getRank());
            statement.setString(4, positionDto.getFile());
            statement.setString(5, gameName);

            statement.executeUpdate();
        }
    }

    public void delete(ChessGameDto chessGameDto) {
        String sql = "delete from piece where game_name = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String gameName = chessGameDto.getGameName();

            statement.setString(1, gameName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ChessGameDto chessGameDto) {
        delete(chessGameDto);
        save(chessGameDto);
    }
}
