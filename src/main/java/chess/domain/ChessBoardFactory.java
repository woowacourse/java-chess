package chess.domain;

import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardFactory {

    private ChessBoardFactory() {
    }

    public static ChessBoard makeChessBoard() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.putAll(makeBlackPiecesExceptPawn());
        chessBoard.putAll(makeBlackPawns());
        chessBoard.putAll(makeBlankPieces());
        chessBoard.putAll(makeWhitePawns());
        chessBoard.putAll(makeWhitePiecesExceptPawn());

        return new ChessBoard(chessBoard, new PawnMovementRule());
    }

    private static Map<Position, Piece> makeBlackPiecesExceptPawn() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.put(Position.of(File.A, Rank.EIGHT), Rook.of(Color.BLACK));
        chessBoard.put(Position.of(File.B, Rank.EIGHT), Knight.of(Color.BLACK));
        chessBoard.put(Position.of(File.C, Rank.EIGHT), Bishop.of(Color.BLACK));
        chessBoard.put(Position.of(File.D, Rank.EIGHT), Queen.of(Color.BLACK));
        chessBoard.put(Position.of(File.E, Rank.EIGHT), King.of(Color.BLACK));
        chessBoard.put(Position.of(File.F, Rank.EIGHT), Bishop.of(Color.BLACK));
        chessBoard.put(Position.of(File.G, Rank.EIGHT), Knight.of(Color.BLACK));
        chessBoard.put(Position.of(File.H, Rank.EIGHT), Rook.of(Color.BLACK));

        return chessBoard;
    }

    private static Map<Position, Piece> makeBlackPawns() {
        Map<Position, Piece> blackPawns = new LinkedHashMap<>();
        File[] files = File.values();
        for (File file : files) {
            blackPawns.put(Position.of(file, Rank.SEVEN), Pawn.of(Color.BLACK));
        }

        return blackPawns;
    }

    private static Map<Position, Piece> makeBlankPieces() {
        Map<Position, Piece> blankPieces = new LinkedHashMap<>();
        blankPieces.putAll(makeBlankPiecesForRank(Rank.SIX));
        blankPieces.putAll(makeBlankPiecesForRank(Rank.FIVE));
        blankPieces.putAll(makeBlankPiecesForRank(Rank.FOUR));
        blankPieces.putAll(makeBlankPiecesForRank(Rank.THREE));

        return blankPieces;
    }

    private static Map<Position, Piece> makeBlankPiecesForRank(Rank rank) {
        Map<Position, Piece> blankPieces = new LinkedHashMap<>();
        File[] files = File.values();
        for (File file : files) {
            blankPieces.put(Position.of(file, rank), EmptyPiece.of());
        }

        return blankPieces;
    }

    private static Map<Position, Piece> makeWhitePawns() {
        Map<Position, Piece> whitePawns = new LinkedHashMap<>();
        File[] files = File.values();
        for (File file : files) {
            whitePawns.put(Position.of(file, Rank.TWO), Pawn.of(Color.WHITE));
        }

        return whitePawns;
    }

    private static Map<Position, Piece> makeWhitePiecesExceptPawn() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.put(Position.of(File.A, Rank.ONE), Rook.of(Color.WHITE));
        chessBoard.put(Position.of(File.B, Rank.ONE), Knight.of(Color.WHITE));
        chessBoard.put(Position.of(File.C, Rank.ONE), Bishop.of(Color.WHITE));
        chessBoard.put(Position.of(File.D, Rank.ONE), Queen.of(Color.WHITE));
        chessBoard.put(Position.of(File.E, Rank.ONE), King.of(Color.WHITE));
        chessBoard.put(Position.of(File.F, Rank.ONE), Bishop.of(Color.WHITE));
        chessBoard.put(Position.of(File.G, Rank.ONE), Knight.of(Color.WHITE));
        chessBoard.put(Position.of(File.H, Rank.ONE), Rook.of(Color.WHITE));

        return chessBoard;
    }
}
