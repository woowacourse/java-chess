package chess.dao;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.Board;
import chess.domain.dto.BoardSaveDto;
import chess.domain.dto.SavePieceDto;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.InitPawnPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDB {
    private ChessDB() {

    }

    public static Board getBoardData() {
        final var query = "SELECT * FROM piece";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> result = new HashMap<>();
            Color color = null;
            while (resultSet.next()) {
                color = Color.valueOf(resultSet.getString("turn"));
                Position position = Position.of(File.valueOf(resultSet.getString("file_id")), Rank.valueOf(resultSet.getString("rank_id")));
                Piece piece = makePieceOf(resultSet.getString("pieceType"), resultSet.getString("color"));
                result.put(position, piece);
            }
            return new Board(result, color);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Piece makePieceOf(String pieceType, String color) {
        PieceType type = PieceType.valueOf(pieceType);
        Color pieceColor = Color.valueOf(color);

        switch (type) {
            case KING:
                return new KingPiece(pieceColor);
            case QUEEN:
                return new QueenPiece(pieceColor);
            case ROOK:
                return new RookPiece(pieceColor);
            case KNIGHT:
                return new KnightPiece(pieceColor);
            case BISHOP:
                return new BishopPiece(pieceColor);
            case PAWN:
                return new PawnPiece(pieceColor);
            case INIT_PAWN:
                return new InitPawnPiece(pieceColor);
            case EMPTY:
                return EmptyPiece.getInstance();
        }
        throw new UnsupportedOperationException();
    }

    public static void saveBoard(BoardSaveDto dto) {
        delete();
        final var query = "INSERT INTO piece VALUES(?, ?, ?, ?, ?)";
        Map<String, HashMap<String, SavePieceDto>> data = dto.getData();
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            for (String hello : data.keySet()) {
                for (String hello2 : data.get(hello).keySet()) {
                    SavePieceDto pieceDto = data.get(hello).get(hello2);
                    preparedStatement.setString(1, hello2);
                    preparedStatement.setString(2, hello);
                    preparedStatement.setString(3, pieceDto.getPieceType());
                    preparedStatement.setString(4, pieceDto.getPieceColor());
                    preparedStatement.setString(5, dto.getTurn());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete() {
        final var query = "DELETE FROM piece";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existBoard() {
        final var query = "SELECT * FROM piece";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            var resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            return i == 64;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
