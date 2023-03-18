package chess.domain.game.constant;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPosition {

    private static final Piece EMPTY_PIECE = Piece.empty();
    private static final List<PieceType> highPieceTypes = List.of(
            PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
            PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
    );

    private ChessPosition() {
    }

    private static void makeBlackHighPiece(Map<Position, Piece> piecePositions) {
        for (int i = 0; i < highPieceTypes.size(); i++) {
            Position position = Position.of(File.from(i + 1), Rank.EIGHT);
            piecePositions.put(position, new Piece(highPieceTypes.get(i), Color.BLACK));
        }
    }

    private static void makeBlackPawn(Map<Position, Piece> piecePositions) {
        for (File file : File.values()) {
            Position position = Position.of(file, Rank.SEVEN);
            piecePositions.put(position, new Piece(PieceType.PAWN, Color.BLACK));
        }
    }

    private static void makeWhitePawn(Map<Position, Piece> piecePositions) {
        for (File file : File.values()) {
            Position position = Position.of(file, Rank.TWO);
            piecePositions.put(position, new Piece(PieceType.PAWN, Color.WHITE));
        }
    }

    private static void makeWhiteHighPiece(Map<Position, Piece> piecePositions) {
        for (int i = 0; i < highPieceTypes.size(); i++) {
            Position position = Position.of(File.from(i + 1), Rank.ONE);
            piecePositions.put(position, new Piece(highPieceTypes.get(i), Color.WHITE));
        }
    }

    private static void makeEmptyPiece(Map<Position, Piece> piecePositions) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                piecePositions.computeIfAbsent(Position.of(file, rank), ignored -> EMPTY_PIECE);
            }
        }
    }

    public static Map<Position, Piece> initialPiecePositions() {
        Map<Position, Piece> piecePositions = new HashMap<>();
        makeBlackHighPiece(piecePositions);
        makeBlackPawn(piecePositions);
        makeWhitePawn(piecePositions);
        makeWhiteHighPiece(piecePositions);
        makeEmptyPiece(piecePositions);
        return new HashMap<>(piecePositions);
    }
}
