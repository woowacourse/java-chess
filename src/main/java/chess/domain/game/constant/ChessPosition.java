package chess.domain.game.constant;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.Map;

public enum ChessPosition {
    A1(Position.of(File.A, Rank.ONE), PieceType.ROOK, Color.WHITE),
    B1(Position.of(File.B, Rank.ONE), PieceType.KNIGHT, Color.WHITE),
    C1(Position.of(File.C, Rank.ONE), PieceType.BISHOP, Color.WHITE),
    D1(Position.of(File.D, Rank.ONE), PieceType.QUEEN, Color.WHITE),
    E1(Position.of(File.E, Rank.ONE), PieceType.KING, Color.WHITE),
    F1(Position.of(File.F, Rank.ONE), PieceType.BISHOP, Color.WHITE),
    G1(Position.of(File.G, Rank.ONE), PieceType.KNIGHT, Color.WHITE),
    H1(Position.of(File.H, Rank.ONE), PieceType.ROOK, Color.WHITE),
    A2(Position.of(File.A, Rank.TWO), PieceType.PAWN, Color.WHITE),
    B2(Position.of(File.B, Rank.TWO), PieceType.PAWN, Color.WHITE),
    C2(Position.of(File.C, Rank.TWO), PieceType.PAWN, Color.WHITE),
    D2(Position.of(File.D, Rank.TWO), PieceType.PAWN, Color.WHITE),
    E2(Position.of(File.E, Rank.TWO), PieceType.PAWN, Color.WHITE),
    F2(Position.of(File.F, Rank.TWO), PieceType.PAWN, Color.WHITE),
    G2(Position.of(File.G, Rank.TWO), PieceType.PAWN, Color.WHITE),
    H2(Position.of(File.H, Rank.TWO), PieceType.PAWN, Color.WHITE),
    A7(Position.of(File.A, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    B7(Position.of(File.B, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    C7(Position.of(File.C, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    D7(Position.of(File.D, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    E7(Position.of(File.E, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    F7(Position.of(File.F, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    G7(Position.of(File.G, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    H7(Position.of(File.H, Rank.SEVEN), PieceType.PAWN, Color.BLACK),
    A8(Position.of(File.A, Rank.EIGHT), PieceType.ROOK, Color.BLACK),
    B8(Position.of(File.B, Rank.EIGHT), PieceType.KNIGHT, Color.BLACK),
    C8(Position.of(File.C, Rank.EIGHT), PieceType.BISHOP, Color.BLACK),
    D8(Position.of(File.D, Rank.EIGHT), PieceType.QUEEN, Color.BLACK),
    E8(Position.of(File.E, Rank.EIGHT), PieceType.KING, Color.BLACK),
    F8(Position.of(File.F, Rank.EIGHT), PieceType.BISHOP, Color.BLACK),
    G8(Position.of(File.G, Rank.EIGHT), PieceType.KNIGHT, Color.BLACK),
    H8(Position.of(File.H, Rank.EIGHT), PieceType.ROOK, Color.BLACK);
    private static final Piece EMPTY_PIECE = Piece.empty();

    private final Position position;
    private final PieceType pieceType;
    private final Color color;

    ChessPosition(Position position, PieceType pieceType, Color color) {
        this.position = position;
        this.pieceType = pieceType;
        this.color = color;
    }

    public static Map<Position, Piece> initialPiecePositions() {
        Map<Position, Piece> piecePositions = new HashMap<>();
        for (ChessPosition positionToPiece : ChessPosition.values()) {
            piecePositions.put(positionToPiece.position,
                    PieceFactory.getInstance(positionToPiece.pieceType, positionToPiece.color));
        }
        makeEmptyPiece(piecePositions);
        return piecePositions;
    }

    private static void makeEmptyPiece(Map<Position, Piece> piecePositions) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                piecePositions.computeIfAbsent(Position.of(file, rank), ignored -> EMPTY_PIECE);
            }
        }
    }
}
