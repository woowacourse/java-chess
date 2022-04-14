package chess.domain.piece;

import chess.domain.piece.moving.BasicMovingPattern;
import chess.domain.piece.moving.BlackPawnMovingPattern;
import chess.domain.piece.moving.Movement;
import chess.domain.piece.moving.WhitePawnMovingPattern;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceColor.BLACK;
import static chess.domain.piece.PieceColor.WHITE;
import static chess.domain.piece.PieceType.*;

public class PieceFactory {

    private final static Piece BLACK_ROOK = new Piece(ROOK, BLACK, new BasicMovingPattern(Movement.getRookMoving()));
    private final static Piece WHITE_ROOK = new Piece(ROOK, WHITE, new BasicMovingPattern(Movement.getRookMoving()));
    private final static Piece BLACK_KNIGHT = new Piece(KNIGHT, BLACK, new BasicMovingPattern(Movement.getKnightMoving()));
    private final static Piece WHITE_KNIGHT = new Piece(KNIGHT, WHITE, new BasicMovingPattern(Movement.getKnightMoving()));
    private final static Piece BLACK_BISHOP = new Piece(BISHOP, BLACK, new BasicMovingPattern(Movement.getBishopMoving()));
    private final static Piece WHITE_BISHOP = new Piece(BISHOP, WHITE, new BasicMovingPattern(Movement.getBishopMoving()));
    private final static Piece BLACK_QUEEN = new Piece(QUEEN, BLACK, new BasicMovingPattern(Movement.getQueenMoving()));
    private final static Piece WHITE_QUEEN = new Piece(QUEEN, WHITE, new BasicMovingPattern(Movement.getQueenMoving()));
    private final static Piece BLACK_KING = new Piece(KING, BLACK, new BasicMovingPattern(Movement.getKingMoving()));
    private final static Piece WHITE_KING = new Piece(KING, WHITE, new BasicMovingPattern(Movement.getKingMoving()));
    private final static Piece BLACK_PAWN = new Piece(PAWN, BLACK, new BlackPawnMovingPattern(Movement.getBlackPawnMoving()));
    private final static Piece WHITE_PAWN = new Piece(PAWN, WHITE, new WhitePawnMovingPattern(Movement.getWhitePawnMoving()));

    private static final Map<String, Piece> PIECE_CACHE = new HashMap<>();

    static {
        PIECE_CACHE.put(parsePieceToString(BLACK, ROOK), BLACK_ROOK);
        PIECE_CACHE.put(parsePieceToString(WHITE, ROOK), WHITE_ROOK);
        PIECE_CACHE.put(parsePieceToString(BLACK, KNIGHT), BLACK_KNIGHT);
        PIECE_CACHE.put(parsePieceToString(WHITE, KNIGHT), WHITE_KNIGHT);
        PIECE_CACHE.put(parsePieceToString(BLACK, BISHOP), BLACK_BISHOP);
        PIECE_CACHE.put(parsePieceToString(WHITE, BISHOP), WHITE_BISHOP);
        PIECE_CACHE.put(parsePieceToString(BLACK, QUEEN), BLACK_QUEEN);
        PIECE_CACHE.put(parsePieceToString(WHITE, QUEEN), WHITE_QUEEN);
        PIECE_CACHE.put(parsePieceToString(BLACK, KING), BLACK_KING);
        PIECE_CACHE.put(parsePieceToString(WHITE, KING), WHITE_KING);
        PIECE_CACHE.put(parsePieceToString(BLACK, PAWN), BLACK_PAWN);
        PIECE_CACHE.put(parsePieceToString(WHITE, PAWN), WHITE_PAWN);
    }

    private static String parsePieceToString(PieceColor color, PieceType type) {
        return color.name() + type.name();
    }

    public static Map<Position, Piece> createChessPieces() {
        Map<Position, Piece> pieces = new HashMap<>();

        pieces.putAll(putWhitePiecesExceptPawn());
        pieces.putAll(putWhitePawnOnRank());
        pieces.putAll(putBlackPawnOnRank());
        pieces.putAll(putBlackPiecesExceptPawn());

        return pieces;
    }

    private static Map<Position, Piece> putWhitePiecesExceptPawn() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.A, Row.RANK_1), WHITE_ROOK);
        pieces.put(Position.of(Column.B, Row.RANK_1), WHITE_KNIGHT);
        pieces.put(Position.of(Column.C, Row.RANK_1), WHITE_BISHOP);
        pieces.put(Position.of(Column.D, Row.RANK_1), WHITE_QUEEN);
        pieces.put(Position.of(Column.E, Row.RANK_1), WHITE_KING);
        pieces.put(Position.of(Column.F, Row.RANK_1), WHITE_BISHOP);
        pieces.put(Position.of(Column.G, Row.RANK_1), WHITE_KNIGHT);
        pieces.put(Position.of(Column.H, Row.RANK_1), WHITE_ROOK);
        return pieces;
    }

    private static Map<Position, Piece> putWhitePawnOnRank() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (Column column : Column.values()) {
            Position position = Position.of(column, Row.RANK_2);
            pieces.put(position, WHITE_PAWN);
        }
        return pieces;
    }

    private static Map<Position, Piece> putBlackPawnOnRank() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (Column column : Column.values()) {
            Position position = Position.of(column, Row.RANK_7);
            pieces.put(position, BLACK_PAWN);
        }
        return pieces;
    }

    private static Map<Position, Piece> putBlackPiecesExceptPawn() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(Column.A, Row.RANK_8), BLACK_ROOK);
        pieces.put(Position.of(Column.B, Row.RANK_8), BLACK_KNIGHT);
        pieces.put(Position.of(Column.C, Row.RANK_8), BLACK_BISHOP);
        pieces.put(Position.of(Column.D, Row.RANK_8), BLACK_QUEEN);
        pieces.put(Position.of(Column.E, Row.RANK_8), BLACK_KING);
        pieces.put(Position.of(Column.F, Row.RANK_8), BLACK_BISHOP);
        pieces.put(Position.of(Column.G, Row.RANK_8), BLACK_KNIGHT);
        pieces.put(Position.of(Column.H, Row.RANK_8), BLACK_ROOK);
        return pieces;
    }

    public static Piece find(PieceType type, PieceColor color) {
        String key = parsePieceToString(color, type);
        if (PIECE_CACHE.containsKey(key)) {
            return PIECE_CACHE.get(key);
        }
        throw new IllegalArgumentException("해당 특징의 Piece 를 찾을 수 없습니다.");
    }
}
