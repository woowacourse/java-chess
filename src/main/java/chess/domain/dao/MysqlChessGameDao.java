package chess.domain.dao;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlChessGameDao implements ChessGameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final ChessGame chessGame) {
        for (Piece piece : chessGame.getPieces()) {
            final String pieceName = piece.getClass().getSimpleName();
            final File file = piece.getPosition().getFile();
            final Rank rank = piece.getPosition().getRank();
            final Color pieceColor = piece.getColor();
            final Color currentTurnColor = chessGame.getCurrentTurnColor();

            final var query = "INSERT INTO chess_game (piece_type, piece_file, piece_rank, piece_color, turn) VALUES (?, ?, ?, ?, ?)";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pieceName);
                preparedStatement.setString(2, file.name());
                preparedStatement.setString(3, rank.name());
                preparedStatement.setString(4, pieceColor.name());
                preparedStatement.setString(5, currentTurnColor.name());
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        final List<Piece> pieces = new ArrayList<>();
        Color turnColor = null;
        final var query = "SELECT piece_type, piece_file, piece_rank, piece_color, turn FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String pieceType = resultSet.getString("piece_type");
                final File pieceFile = File.valueOf(resultSet.getString("piece_file"));
                final Rank pieceRank = Rank.valueOf(resultSet.getString("piece_rank"));
                final Color pieceColor = Color.valueOf(resultSet.getString("piece_color"));
                turnColor = Color.valueOf(resultSet.getString("turn"));

                Piece piece = generatePiece(pieceType, pieceFile, pieceRank, pieceColor);
                pieces.add(piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return ChessGame.of(pieces, turnColor);
    }

    private Piece generatePiece(final String pieceType, final File pieceFile, final Rank pieceRank, final Color pieceColor) {
        switch (pieceType) {
            case "King":
                return new King(pieceFile, pieceRank, pieceColor);
            case "Queen":
                return new Queen(pieceFile, pieceRank, pieceColor);
            case "Rook":
                return new Rook(pieceFile, pieceRank, pieceColor);
            case "Bishop":
                return new Bishop(pieceFile, pieceRank, pieceColor);
            case "Knight":
                return new Knight(pieceFile, pieceRank, pieceColor);
            case "Pawn":
                return new Pawn(pieceFile, pieceRank, pieceColor);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final ChessGame chessGame) {
        deleteAll();
        save(chessGame);
    }

    public void deleteAll() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
