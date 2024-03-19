package domain.piece;

import domain.Position;
import domain.piece.type.Bishop;
import domain.piece.type.King;
import domain.piece.type.Knight;
import domain.piece.type.Pawn;
import domain.piece.type.Queen;
import domain.piece.type.Rook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiecesGenerator {
    // TODO: 미안해 자바야...
    static final List<Position> WHITE_PAWN_POSITIONS = List.of(
            new Position(ChessRank.TWO, ChessFile.A), new Position(ChessRank.TWO, ChessFile.B), new Position(ChessRank.TWO, ChessFile.C), new Position(ChessRank.TWO, ChessFile.D),
            new Position(ChessRank.TWO, ChessFile.E), new Position(ChessRank.TWO, ChessFile.F), new Position(ChessRank.TWO, ChessFile.G), new Position(ChessRank.TWO, ChessFile.H));
    static final List<Position> WHITE_ROOK_POSITION = List.of(
            new Position(ChessRank.ONE, ChessFile.A), new Position(ChessRank.ONE, ChessFile.H));
    static final List<Position> WHITE_KNIGHT_POSITION = List.of(
            new Position(ChessRank.ONE, ChessFile.B), new Position(ChessRank.ONE, ChessFile.G));
    static final List<Position> WHITE_BISHOP_POSITION = List.of(
            new Position(ChessRank.ONE, ChessFile.C), new Position(ChessRank.ONE, ChessFile.F));
    static final Position WHITE_KING_POSITION = new Position(ChessRank.ONE, ChessFile.E);
    static final Position WHITE_QUEEN_POSITION = new Position(ChessRank.ONE, ChessFile.D);
    static final List<Position> BLACK_PAWN_POSITIONS = List.of(
            new Position(ChessRank.SEVEN, ChessFile.A), new Position(ChessRank.SEVEN, ChessFile.B), new Position(ChessRank.SEVEN, ChessFile.C), new Position(ChessRank.SEVEN, ChessFile.D),
            new Position(ChessRank.SEVEN, ChessFile.E), new Position(ChessRank.SEVEN, ChessFile.F), new Position(ChessRank.SEVEN, ChessFile.G), new Position(ChessRank.SEVEN, ChessFile.H));

    static final List<Position> BLACK_ROOK_POSITION = List.of(
            new Position(ChessRank.EIGHT, ChessFile.A), new Position(ChessRank.EIGHT, ChessFile.H));
    static final List<Position> BLACK_KNIGHT_POSITION = List.of(
            new Position(ChessRank.EIGHT, ChessFile.B), new Position(ChessRank.EIGHT, ChessFile.G));
    static final List<Position> BLACK_BISHOP_POSITION = List.of(
            new Position(ChessRank.EIGHT, ChessFile.C), new Position(ChessRank.EIGHT, ChessFile.F));
    static final Position BLACK_KING_POSITION = new Position(ChessRank.EIGHT, ChessFile.E);
    static final Position BLACK_QUEEN_POSITION = new Position(ChessRank.EIGHT, ChessFile.D);

    private final static PiecesGenerator INSTANCE = new PiecesGenerator();

    private PiecesGenerator() {
    }

    public static PiecesGenerator getInstance() {
        return INSTANCE;
    }

    public Map<Position, Piece> generate() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(generateWhitePieces());
        pieces.putAll(generateBlackPieces());

        return pieces;
    }

    // TODO: piece 생성 iterate 메서드 분리
    private Map<Position, Piece> generateWhitePieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (Position position : WHITE_PAWN_POSITIONS) {
            pieces.put(position, new Pawn(PieceColor.WHITE));
        }
        for (Position position : WHITE_ROOK_POSITION) {
            pieces.put(position, new Rook(PieceColor.WHITE));
        }
        for (Position position : WHITE_KNIGHT_POSITION) {
            pieces.put(position, new Knight(PieceColor.WHITE));
        }
        for (Position position : WHITE_BISHOP_POSITION) {
            pieces.put(position, new Bishop(PieceColor.WHITE));
        }
        pieces.put(WHITE_KING_POSITION, new King(PieceColor.WHITE));
        pieces.put(WHITE_QUEEN_POSITION, new Queen(PieceColor.WHITE));

        return pieces;
    }

    private Map<Position, Piece> generateBlackPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (Position position : BLACK_PAWN_POSITIONS) {
            pieces.put(position, new Pawn(PieceColor.BLACK));
        }
        for (Position position : BLACK_ROOK_POSITION) {
            pieces.put(position, new Rook(PieceColor.BLACK));
        }
        for (Position position : BLACK_KNIGHT_POSITION) {
            pieces.put(position, new Knight(PieceColor.BLACK));
        }
        for (Position position : BLACK_BISHOP_POSITION) {
            pieces.put(position, new Bishop(PieceColor.BLACK));
        }
        pieces.put(BLACK_KING_POSITION, new King(PieceColor.BLACK));
        pieces.put(BLACK_QUEEN_POSITION, new Queen(PieceColor.BLACK));
        return pieces;
    }
}
