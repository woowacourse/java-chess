package chess.repository;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.view.PieceMapper;
import chess.view.TeamMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            String turn = TeamMapper.from(chessGame.getNowPlayingTeam()).getTeamView();
            for (Map.Entry<Position, Piece> positionPieceEntry : chessGame.getBoard().entrySet()) {
                Piece piece = positionPieceEntry.getValue();
                String pieceType = PieceMapper.from(piece.getPieceType()).getPieceViewBy(Team.WHITE);
                Position position = positionPieceEntry.getKey();
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
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
