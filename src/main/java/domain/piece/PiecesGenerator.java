package domain.piece;

import domain.piece.type.Bishop;
import domain.piece.type.King;
import domain.piece.type.Knight;
import domain.piece.type.Pawn;
import domain.piece.type.Queen;
import domain.piece.type.Rook;
import domain.position.ChessFile;
import domain.position.ChessRank;
import domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiecesGenerator {
    // TODO: 미안해 자바야...
    static final List<Position> WHITE_PAWN_POSITIONS = List.of(
            new Position(ChessFile.A, ChessRank.TWO), new Position(ChessFile.B, ChessRank.TWO), new Position(ChessFile.C, ChessRank.TWO), new Position(ChessFile.D, ChessRank.TWO),
            new Position(ChessFile.E, ChessRank.TWO), new Position(ChessFile.F, ChessRank.TWO), new Position(ChessFile.G, ChessRank.TWO), new Position(ChessFile.H, ChessRank.TWO));
    static final List<Position> WHITE_ROOK_POSITION = List.of(
            new Position(ChessFile.A, ChessRank.ONE), new Position(ChessFile.H, ChessRank.ONE));
    static final List<Position> WHITE_KNIGHT_POSITION = List.of(
            new Position(ChessFile.B, ChessRank.ONE), new Position(ChessFile.G, ChessRank.ONE));
    static final List<Position> WHITE_BISHOP_POSITION = List.of(
            new Position(ChessFile.C, ChessRank.ONE), new Position(ChessFile.F, ChessRank.ONE));
    static final Position WHITE_KING_POSITION = new Position(ChessFile.E, ChessRank.ONE);
    static final Position WHITE_QUEEN_POSITION = new Position(ChessFile.D, ChessRank.ONE);
    static final List<Position> BLACK_PAWN_POSITIONS = List.of(
            new Position(ChessFile.A, ChessRank.SEVEN), new Position(ChessFile.B, ChessRank.SEVEN), new Position(ChessFile.C, ChessRank.SEVEN), new Position(ChessFile.D, ChessRank.SEVEN),
            new Position(ChessFile.E, ChessRank.SEVEN), new Position(ChessFile.F, ChessRank.SEVEN), new Position(ChessFile.G, ChessRank.SEVEN), new Position(ChessFile.H, ChessRank.SEVEN));

    static final List<Position> BLACK_ROOK_POSITION = List.of(
            new Position(ChessFile.A, ChessRank.EIGHT), new Position(ChessFile.H, ChessRank.EIGHT));
    static final List<Position> BLACK_KNIGHT_POSITION = List.of(
            new Position(ChessFile.B, ChessRank.EIGHT), new Position(ChessFile.G, ChessRank.EIGHT));
    static final List<Position> BLACK_BISHOP_POSITION = List.of(
            new Position(ChessFile.C, ChessRank.EIGHT), new Position(ChessFile.F, ChessRank.EIGHT));
    static final Position BLACK_KING_POSITION = new Position(ChessFile.E, ChessRank.EIGHT);
    static final Position BLACK_QUEEN_POSITION = new Position(ChessFile.D, ChessRank.EIGHT);

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
