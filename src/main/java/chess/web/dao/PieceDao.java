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
    public void save(int chessBoardId, ChessGameDto chessGameDto) {
        Connection connection = getConnection();

        ChessBoardDto chessBoard = chessGameDto.getChessBoard();

        Map<PositionDto, PieceDto> cells = chessBoard.getCells();

        final String sql = "insert into piece (type, team, `rank`, file, chessboard_id) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            updateCells(chessBoardId, cells, statement);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCells(int chessBoardId, Map<PositionDto, PieceDto> cells, PreparedStatement statement) throws SQLException {
        for (PositionDto positionDto : cells.keySet()) {
            statement.setString(1, cells.get(positionDto).getSymbol());
            statement.setString(2, cells.get(positionDto).getTeam());
            statement.setInt(3, positionDto.getRank());
            statement.setString(4, positionDto.getFile());
            statement.setInt(5, chessBoardId);

            statement.executeUpdate();
        }
    }

    public void delete(int chessboardId) {
        Connection connection = getConnection();

        String sql = "delete from piece where chessboard_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, chessboardId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int chessboardId, ChessGameDto chessGameDto) {
        delete(chessboardId);
        save(chessboardId, chessGameDto);
    }
}
