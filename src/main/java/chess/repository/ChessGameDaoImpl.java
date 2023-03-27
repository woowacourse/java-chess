package chess.repository;

import chess.domain.PieceDto;
import chess.domain.chessGame.ChessGameState;
import chess.domain.chessGame.PlayingChessGameState;
import chess.domain.chessGame.ReadyChessGameState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDaoImpl implements ChessGameDao {

    private final Connection connection;

    public ChessGameDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Color findTurn() {
        final var query = "SELECT * FROM chess_game";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Color.from(resultSet.getString("turn"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ChessGameState findChessGame() {
        Color currentTurn = findTurn();
        if (currentTurn == null) {
            return new ReadyChessGameState();
        }
        Map<Position, Piece> board = loadBoard();
        return new PlayingChessGameState(board, currentTurn);
    }

    @Override
    public void updateChessGame(ChessGameState gameState) {
        updateTurn(gameState.getThisTurn());
        deletePieces();
        savePieces(gameState.getPrintingBoard());
    }

    @Override
    public void updateTurn(Color color) {
        deleteTurn();
        final var query = "insert into chess_game (turn) values (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color.getName());

            preparedStatement.executeUpdate();
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private Map<Position, Piece> loadBoard() {
        final String query = "select * from piece";
        Map<Position, Piece> board = new HashMap<>();

        try (final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String x = resultSet.getString("x");
                String y = resultSet.getString("y");
                Position position = Position.of(File.of(x), Rank.of(y));

                String color = resultSet.getString("color");
                String type = resultSet.getString("type");
                Piece piece = new Piece(PieceType.from(type), Color.from(color));

                board.put(position, piece);
            }
            return board;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePieces(Map<Position, PieceDto> pieces) {
        final var query = "insert into piece (x,y,color,type) values (?,?,?,?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<Position, PieceDto> entry : pieces.entrySet()) {
                preparedStatement.setString(1, entry.getKey().getFileIndex());
                preparedStatement.setString(2, entry.getKey().getRankIndex());
                preparedStatement.setString(3, entry.getValue().getColor().getName());
                preparedStatement.setString(4, entry.getValue().getPieceType().getName());

                preparedStatement.executeUpdate();
            }

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        deleteTurn();
        deletePieces();
    }

    private void deleteTurn() {
        final String query = "delete from chess_game";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void deletePieces() {
        final String query = "delete from piece";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
