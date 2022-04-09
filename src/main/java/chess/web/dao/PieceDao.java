package chess.web.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceSymbol;
import chess.domain.piece.vo.TeamColor;
import chess.web.dbmanager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final int TEANM_COLOR_INDEX = 1;
    private static final int SYMBOL_INDEX = 2;
    private static final int POSITION_INDEX = 3;

    private final DBManager dbManager;

    public PieceDao(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Piece> findAll() {
        String sql = "select teamColor, symbol, position from piece";
        List<Piece> pieces = new ArrayList<>();

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            pieces = convertPieces(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    private static List<Piece> convertPieces(ResultSet resultSet) throws SQLException {
        List<Piece> pieces = new ArrayList<>();
        while (resultSet.next()) {
            pieces.add(PieceSymbol.getConstructor(resultSet.getString("symbol")).apply(
                    TeamColor.valueOf(resultSet.getString("teamColor")),
                    Position.from(resultSet.getString("position")
                    )));
        }
        return pieces;
    }

    public void updatePieces(Board board) {
        try (Connection connection = dbManager.getConnection()) {
            initialize(connection);
            saveAllPieceBy(connection, board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initialize(Connection connection) throws SQLException {
        final String clearSql = "truncate table piece";
        PreparedStatement statement = connection.prepareStatement(clearSql);
        statement.executeUpdate();
        statement.close();
    }

    private static void saveAllPieceBy(Connection connection, Board board) throws SQLException {
        final String sql = "insert into piece (teamColor, symbol, position) values (?, ?, ?)";
        for (Piece piece : board.getPieces()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(TEANM_COLOR_INDEX, piece.getTeamColor());
            statement.setString(SYMBOL_INDEX, PieceSymbol.findWebSymbol(piece));
            statement.setString(POSITION_INDEX, piece.getPosition().toFileRankString());
            statement.executeUpdate();
            statement.close();
        }
    }
}
