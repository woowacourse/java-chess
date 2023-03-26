package chess.dao;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
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

public class PieceDao {
    private PieceDao() {
    }

    public static Map<Position, Piece> getBoardDataOf(String gameId) {
        final var query = "SELECT * FROM piece WHERE game_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameId);
            var resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> result = new HashMap<>();
            while (resultSet.next()) {
                Position position = Position.of(File.valueOf(resultSet.getString("file_id")), Rank.valueOf(resultSet.getString("rank_id")));
                Piece piece = makePieceOf(resultSet.getString("pieceType"), resultSet.getString("color"));
                result.put(position, piece);
            }
            return result;
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
        delete(dto.getGameId());
        final var query = "INSERT INTO piece VALUES(?, ?, ?, ?, ?)";
        Map<String, HashMap<String, SavePieceDto>> data = dto.getData();
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            for (String file : data.keySet()) {
                for (String rank : data.get(file).keySet()) {
                    SavePieceDto pieceDto = data.get(file).get(rank);
                    preparedStatement.setString(1, rank);
                    preparedStatement.setString(2, file);
                    preparedStatement.setString(3, pieceDto.getPieceType());
                    preparedStatement.setString(4, pieceDto.getPieceColor());
                    preparedStatement.setString(5, dto.getGameId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String game_id) {
        final var query = "DELETE FROM piece WHERE game_id = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, game_id);
            int result = preparedStatement.executeUpdate();
            if (result == 64) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
