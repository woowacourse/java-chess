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

    private static final Piece WHITE_KING = new Piece(PieceType.KING, Color.WHITE);
    private static final Piece BLACK_KING = new Piece(PieceType.KING, Color.BLACK);
    private static final Piece WHITE_QUEEN = new Piece(PieceType.QUEEN, Color.WHITE);
    private static final Piece BLACK_QUEEN = new Piece(PieceType.QUEEN, Color.BLACK);
    private static final Piece WHITE_ROOK = new Piece(PieceType.ROOK, Color.WHITE);
    private static final Piece BLACK_ROOK = new Piece(PieceType.ROOK, Color.BLACK);
    private static final Piece WHITE_BISHOP = new Piece(PieceType.BISHOP, Color.WHITE);
    private static final Piece BLACK_BISHOP = new Piece(PieceType.BISHOP, Color.BLACK);
    private static final Piece WHITE_KNIGHT = new Piece(PieceType.KNIGHT, Color.WHITE);
    private static final Piece BLACK_KNIGHT = new Piece(PieceType.KNIGHT, Color.BLACK);
    private static final Piece WHITE_PAWN = new Piece(PieceType.PAWN, Color.WHITE);
    private static final Piece BLACK_PAWN = new Piece(PieceType.PAWN, Color.BLACK);
    private static final Piece EMPTY_PIECE = Piece.empty();
    private static final List<Piece> highBlackPieces = List.of(
            BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP,
            BLACK_KNIGHT, BLACK_ROOK
    );
    private static final List<Piece> highWhitePiece = List.of(
            WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP,
            WHITE_KNIGHT, WHITE_ROOK
    );

    private static final Map<Position, Piece> piecePositions = new HashMap<>();

    static {
        makeBlackHighPiece();
        makeBlackPawn();
        makeWhitePawn();
        makeWhiteHighPiece();
        makeEmptyPiece();
    }

    private ChessPosition() {
    }

    private static void makeBlackHighPiece() {
        for (int i = 0; i < highBlackPieces.size(); i++) {
            Position position = Position.of(File.from(i + 1), Rank.EIGHT);
            piecePositions.put(position, highBlackPieces.get(i));
        }
    }

    private static void makeBlackPawn() {
        for (File file : File.values()) {
            Position position = Position.of(file, Rank.SEVEN);
            piecePositions.put(position, BLACK_PAWN);
        }
    }

    private static void makeWhitePawn() {
        for (File file : File.values()) {
            Position position = Position.of(file, Rank.TWO);
            piecePositions.put(position, WHITE_PAWN);
        }
    }

    private static void makeWhiteHighPiece() {
        for (int i = 0; i < highWhitePiece.size(); i++) {
            Position position = Position.of(File.from(i + 1), Rank.ONE);
            piecePositions.put(position, highWhitePiece.get(i));
        }
    }

    private static void makeEmptyPiece() {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                piecePositions.computeIfAbsent(Position.of(file, rank), ignored -> EMPTY_PIECE);
            }
        }
    }

    public static Map<Position, Piece> initialPiecePositions() {
        return new HashMap<>(Map.copyOf(piecePositions));
    }
}
