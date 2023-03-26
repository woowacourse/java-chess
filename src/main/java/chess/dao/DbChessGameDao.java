package chess.dao;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbChessGameDao implements ChessGameDao {

    private final Database database = new Database();

    @Override
    public long create() {
        String query = "INSERT INTO chess_game (turn) VALUES(?)";
        long chessGameId = 0L;
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, Team.getStartTeam().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                chessGameId = generatedKeys.getLong(1);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return chessGameId;
    }

    @Override
    public ChessGame findById(long id) {
        Map<Position, Piece> board = new HashMap<>();
        String turn = Team.WHITE.name();
        String query = "SELECT cg.turn, cb.piece_type, cb.piece_rank, cb.piece_file, cb.team FROM chess_game cg "
                + "JOIN chess_board cb ON cg.chess_game_id = cb.chess_game_id and cg.chess_game_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
                String pieceType = resultSet.getString("piece_type");
                String team = resultSet.getString("team");
                Piece piece = makePiece(pieceType, team);
                int rank = resultSet.getInt("piece_rank");
                int file = resultSet.getInt("piece_file");
                board.put(new Position(rank, file), piece);
            }
            return ChessGame.setting(id, Board.setting(board), Team.valueOf(turn));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece makePiece(String pieceType, String team) {
        if (pieceType.equals(King.class.getName())) {
            return new King(Team.valueOf(team));
        }
        if (pieceType.equals(Queen.class.getName())) {
            return new Queen(Team.valueOf(team));
        }
        if (pieceType.equals(Rook.class.getName())) {
            return new Rook(Team.valueOf(team));
        }
        if (pieceType.equals(Bishop.class.getName())) {
            return new Bishop(Team.valueOf(team));
        }
        if (pieceType.equals(Knight.class.getName())) {
            return new Knight(Team.valueOf(team));
        }
        if (pieceType.equals(Pawn.class.getName())) {
            return new Pawn(Team.valueOf(team));
        }
        return new Empty(Team.valueOf(team));
    }

    @Override
    public void updateTurn(ChessGame chessGame) {
        String query = "UPDATE chess_game SET turn = ? WHERE chess_game_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGame.getTurn().name());
            preparedStatement.setLong(2, chessGame.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findAll() {
        List<Integer> allGameId = new ArrayList<>();
        String query = "SELECT chess_game_id FROM chess_game";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int chessGameId = resultSet.getInt("chess_game_id");
                allGameId.add(chessGameId);
            }
            return allGameId;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
