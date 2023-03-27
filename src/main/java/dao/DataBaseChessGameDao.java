package dao;

import domain.ChessBoard;
import domain.ChessColumn;
import domain.ChessGame;
import domain.Rank;
import domain.Square;
import domain.Turn;
import domain.piece.Blank;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataBaseChessGameDao implements ChessGameDao {

    private final DataBase dataBase = new DataBase();

    @Override
    public void save(ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.getChessBoard();
        TeamColor turn = chessGame.getTurn().getCurrentOrder();
        Set<Entry<Square, Piece>> entries = chessBoard.getEntries();
        saveByTeam(entries, turn);
    }

    private void saveByTeam(Set<Entry<Square, Piece>> entries, TeamColor turn) {
        final var query = "INSERT INTO chess_game(piece_info, chess_rank, chessColumn, team, turn) VALUES (?, ?, ?, ?, ?);";
        for (Entry<Square, Piece> entry : entries) {
            Piece piece = entry.getValue();
            Square square = entry.getKey();

            try (final var connection = dataBase.getConnection();
                final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, piece.getPieceType().name());
                preparedStatement.setString(2, square.getRank().name());
                preparedStatement.setString(3, square.getChessColumn().name());
                preparedStatement.setString(4, piece.getTeamColor().name());
                preparedStatement.setString(5, turn.name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        final var query = "SELECT piece_info, chess_rank, chessColumn, team, turn FROM chess_game";

        Map<Square, Piece> locationInfo = new HashMap<>(ChessBoard.BOARD_SIZE);
        Turn turn = new Turn();
        try (final var connection = dataBase.getConnection();
            final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PieceInfo pieceInfo = PieceInfo.valueOf(resultSet.getString("piece_info"));
                Rank rank = Rank.valueOf(resultSet.getString("chess_rank"));
                ChessColumn chessColumn = ChessColumn.valueOf(resultSet.getString("chessColumn"));
                TeamColor team = TeamColor.valueOf(resultSet.getString("team"));
                turn = new Turn(TeamColor.valueOf(resultSet.getString("turn")));

                Piece piece = makePiece(pieceInfo, team);
                Square square = Square.of(chessColumn, rank);
                locationInfo.put(square, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        if (locationInfo.isEmpty()) {
            return new ChessGame();
        }

        ChessBoard chessBoard = new ChessBoard(locationInfo);
        return new ChessGame(chessBoard, turn);
    }

    private Piece makePiece(PieceInfo pieceInfo, TeamColor team) {
        switch (pieceInfo) {
            case PAWN:
                return new Pawn(team);
            case ROOK:
                return new Rook(team);
            case KNIGHT:
                return new Knight(team);
            case BISHOP:
                return new Bishop(team);
            case QUEEN:
                return new Queen(team);
            case KING:
                return new King(team);
            case BLANK:
                return Blank.getInstance();
            default:
                throw new UnsupportedOperationException("존재하지 않는 기물입니다.");
        }
    }

    @Override
    public void update(ChessGame chessGame) {
        delete(chessGame);
        save(chessGame);
    }

    @Override
    public void delete(ChessGame chessGame) {
        final var query = "DELETE FROM chess_game";
        try (final var connection = dataBase.getConnection();
            final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeQuery();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
