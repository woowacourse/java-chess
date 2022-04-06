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
        try (Connection connection = getConnection()) {
            saveChessGame(connection, chessGameDto, chessBoardId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveChessGame(Connection connection, ChessGameDto chessGameDto, int chessBoardId) throws SQLException {
        String sql = "insert into chessgame (game_name, turn, chess_board_id) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, chessGameDto.getGameName());
        statement.setString(2, chessGameDto.getTurn());
        statement.setInt(3, chessBoardId);

        statement.executeUpdate();
    }

    public void update(ChessGameDto chessGameDto, int chessboardId) {
        try (Connection connection = getConnection()) {
            updateChessGame(connection, chessGameDto, chessboardId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateChessGame(Connection connection, ChessGameDto chessGameDto, int chessboardId) throws SQLException {
        String sql = "update chessgame set turn = ?, chess_board_id = ? where game_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, chessGameDto.getTurn());
        statement.setInt(2, chessboardId);
        statement.setString(3, chessGameDto.getGameName());

        statement.executeUpdate();
    }

    public int findIdByName(String gameName) {
        try (Connection connection = getConnection()) {
            return findIdFromChessGame(connection, gameName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int findIdFromChessGame(Connection connection, String gameName) throws SQLException {
        String sql = "select chess_board_id from CHESSGAME where game_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, gameName);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("chess_board_id");
        }
        return 0;
    }

    public ChessGame findByName(String gameName) {
        try (Connection connection = getConnection()) {
            return findChessGame(connection, gameName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ChessGame findChessGame(Connection connection, String gameName) throws SQLException {
        String sql = "select CHESSGAME.turn, CHESSGAME.game_name, PIECE.type, PIECE.team, PIECE.`rank`, PIECE.file from CHESSGAME, CHESSBOARD, PIECE\n"
                + "where CHESSGAME.chess_board_id = CHESSBOARD.id AND (CHESSBOARD.id IN (SELECT chessboard_id FROM PIECE))\n"
                + "AND game_name = ?;";

        PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        statement.setString(1, gameName);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            return null;
        }

        Map<Position, Piece> cells = makeCells(resultSet);
        String turn = getTurn(resultSet);

        return new ChessGame(turn, gameName, cells);
    }

    private String getTurn(ResultSet resultSet) throws SQLException {
        resultSet.first();
        resultSet.next();
        return resultSet.getString("turn");
    }

    private Map<Position, Piece> makeCells(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> cells = new HashMap<>();

        while (resultSet.next()) {
            Position position = makePosition(resultSet);

            Piece piece = makePiece(resultSet);

            cells.put(position, piece);
        }

        return cells;
    }

    private Position makePosition(ResultSet resultSet) throws SQLException {
        int rank = resultSet.getInt("rank");
        String file = resultSet.getString("file");

        return Position.of(File.toFile(file.charAt(0)), Rank.toRank(rank));
    }

    private Piece makePiece(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");
        String team = resultSet.getString("team");

        return PieceConverter.from(type, team);
    }

    public void remove(String gameName) {
        try (Connection connection = getConnection()) {
            removeByGameName(connection, gameName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeByGameName(Connection connection, String gameName) throws SQLException {
        String selectSQL = "select chess_board_id from chessgame where game_name = ?";
        PreparedStatement statement = connection.prepareStatement(selectSQL);
        statement.setString(1, gameName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        int chessBoardId = resultSet.getInt("chess_board_id");

        removeChessBoard(connection, chessBoardId);
    }

    private void removeChessBoard(Connection connection, int chessBoardId) throws SQLException {
        String chessBoardDeleteSQL = "delete from chessboard where id = ?";
        PreparedStatement statement = connection.prepareStatement(chessBoardDeleteSQL);
        statement.setInt(1, chessBoardId);

        statement.executeUpdate();
    }
}
