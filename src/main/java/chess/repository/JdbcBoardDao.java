package chess.repository;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.controller.mapper.FileCoordinateMapper;
import chess.controller.mapper.PieceMapper;
import chess.controller.mapper.RankCoordinateMapper;
import chess.controller.mapper.TeamMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcBoardDao implements BoardDao {

    private final Connector connector;

    public JdbcBoardDao(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void saveChessGame(final ChessGame chessGame) {
        final var query = "INSERT INTO PIECE(piece_type, piece_column, piece_row, piece_team, turn) VALUES(?, ?, ?, ?, ?)";
        try (final var connection = connector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            executeSaveQuery(chessGame, preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeSaveQuery(ChessGame chessGame, PreparedStatement preparedStatement) throws SQLException {
        String turn = TeamMapper.from(chessGame.getNowPlayingTeam()).getTeamView();
        for (Map.Entry<Position, Piece> positionPieceEntry : chessGame.getBoard().entrySet()) {
            Piece piece = positionPieceEntry.getValue();
            Position position = positionPieceEntry.getKey();
            String pieceType = PieceMapper.from(piece.getPieceType()).getPieceViewBy(Team.WHITE);
            String pieceColumn = String.valueOf(position.getColumn());
            String pieceRow = String.valueOf(position.getRow());
            String pieceTeam = TeamMapper.from(piece.getTeam()).getTeamView();
            preparedStatement.setString(1, pieceType);
            preparedStatement.setString(2, pieceColumn);
            preparedStatement.setString(3, pieceRow);
            preparedStatement.setString(4, pieceTeam);
            preparedStatement.setString(5, turn);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ChessGame selectChessGame() {
        final var query = "SELECT * FROM PIECE";
        try (final var connection = connector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            Map<Position, Piece> boards = new HashMap<>();
            Team turn = null;
            while (resultSet.next()) {
                turn = TeamMapper.from(resultSet.getString("turn")).getTeam();
                PieceType pieceType = PieceMapper.from(resultSet.getString("piece_type")).getPieceType();
                FileCoordinate pieceColumn = FileCoordinateMapper.findBy(Integer.parseInt(resultSet.getString("piece_column")));
                RankCoordinate pieceRow = RankCoordinateMapper.findBy(Integer.parseInt(resultSet.getString("piece_row")));
                Team pieceTeam = TeamMapper.from(resultSet.getString("piece_team")).getTeam();
                Position position = new Position(pieceColumn, pieceRow);
                Piece piece = pieceType.of(pieceTeam, position);
                boards.put(position, piece);
            }
            return new ChessGame(new Board(boards), turn);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final var query = "DELETE FROM PIECE";
        try (final var connection = connector.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
