package chess.web.dao;

import static chess.web.dao.DBConnector.getConnection;

import chess.web.dto.ChessBoardDto;
import chess.web.dto.ChessGameDto;
import chess.web.dto.PieceDto;
import chess.web.dto.PositionDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ChessBoardDao {
    public int save(ChessGameDto chessGameDto) {
        Connection connection = getConnection();

        ChessBoardDto chessBoard = chessGameDto.getChessBoard();

        Map<PositionDto, PieceDto> cells = chessBoard.getCells();

        String sql = "insert into chessboard values ()";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();

            int chessBoardId = resultSet.getInt(1);

            for (PositionDto positionDto : cells.keySet()) {
                addBoard(connection, chessBoardId, positionDto, cells.get(positionDto));
            }
            return chessBoardId;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addBoard(Connection connection, int chessBoardId, PositionDto position, PieceDto piece) {

        final String sql = "insert into piece (type, team, `rank`, file, chessboard_id) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, piece.getSymbol());
            statement.setString(2, piece.getTeam());
            statement.setInt(3, position.getRank());
            statement.setString(4, position.getFile());
            statement.setInt(5, chessBoardId);

            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
