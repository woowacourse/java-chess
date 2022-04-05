package chess.web.dao;

import static chess.web.dao.DBConnector.getConnection;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.web.converter.PieceConverter;
import chess.web.dto.ChessGameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDao {

    public void save(ChessGameDto chessGameDto, int chessBoardId) {
        Connection connection = getConnection();

        String sql = "insert into chessgame (game_name, turn, chess_board_id) values (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, chessGameDto.getGameName());
            statement.setString(2, chessGameDto.getTurn());
            statement.setInt(3, chessBoardId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ChessGameDto chessGameDto, int chessboardId) {
        Connection connection = getConnection();

        String sql = "update chessgame set turn = ?, chess_board_id = ? where game_name = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, chessGameDto.getTurn());
            statement.setInt(2, chessboardId);
            statement.setString(3, chessGameDto.getGameName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findIdByName(String gameName) {
        Connection connection = getConnection();

        String sql = "select chess_board_id from CHESSGAME where game_name = ?";

        int id = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, gameName);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                id = resultSet.getInt("chess_board_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public ChessGame findByName(String gameName) {
        Connection connection = getConnection();

        String sql = "select CHESSGAME.turn, CHESSGAME.game_name, PIECE.type, PIECE.team, PIECE.`rank`, PIECE.file from CHESSGAME, CHESSBOARD, PIECE\n"
                + "where CHESSGAME.chess_board_id = CHESSBOARD.id AND (CHESSBOARD.id IN (SELECT chessboard_id FROM PIECE))\n"
                + "AND game_name = ?;";

        try {
            String turn = "";

            Map<Position, Piece> cells = new HashMap<>();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, gameName);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            while(resultSet.next()) {
                turn = resultSet.getString("turn");

                int rank = resultSet.getInt("rank");
                String file = resultSet.getString("file");

                Position position = Position.of(File.toFile(file.charAt(0)), Rank.toRank(rank));

                String type = resultSet.getString("type");
                String team = resultSet.getString("team");

                Piece piece = PieceConverter.from(type, team);

                cells.put(position, piece);
            }

            return new ChessGame(turn, gameName, cells);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void remove(String gameName) {
        Connection connection = getConnection();

        String selectSQL = "select chess_board_id from chessgame where game_name = ?";
        String chessGameDeleteSQL = "delete from chessgame where game_name = ?";
        String chessBoardDeleteSQL = "delete from chessboard where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(selectSQL);
            statement.setString(1, gameName);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            int chessBoardId = resultSet.getInt("chess_board_id");

            statement = connection.prepareStatement(chessGameDeleteSQL);
            statement.setString(1, gameName);

            statement.executeUpdate();

            statement = connection.prepareStatement(chessBoardDeleteSQL);
            statement.setInt(1, chessBoardId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
