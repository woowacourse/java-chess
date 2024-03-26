package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardFactory {

    public static ChessBoard makeChessBoard() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.putAll(getBlackPiecesExceptPawn());
        chessBoard.putAll(getBlackPawns());
        chessBoard.putAll(getBlankPieces());
        chessBoard.putAll(getWhitePawns());
        chessBoard.putAll(getWhitePiecesExceptPawn());

        return new ChessBoard(chessBoard);
    }

    private static Map<Position, Piece> getBlackPiecesExceptPawn() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.put(Position.of('a', 8), Rook.colorOf(Color.BLACK));
        chessBoard.put(Position.of('b', 8), Knight.colorOf(Color.BLACK));
        chessBoard.put(Position.of('c', 8), Bishop.colorOf(Color.BLACK));
        chessBoard.put(Position.of('d', 8), Queen.colorOf(Color.BLACK));
        chessBoard.put(Position.of('e', 8), King.colorOf(Color.BLACK));
        chessBoard.put(Position.of('f', 8), Bishop.colorOf(Color.BLACK));
        chessBoard.put(Position.of('g', 8), Knight.colorOf(Color.BLACK));
        chessBoard.put(Position.of('h', 8), Rook.colorOf(Color.BLACK));

        return chessBoard;
    }

    private static Map<Position, Piece> getBlackPawns() {
        Map<Position, Piece> blackPawns = new LinkedHashMap<>();
        for (char file = 'a'; file <= 'h'; file++) {
            blackPawns.put(Position.of(file, 7), Pawn.colorOf(Color.BLACK));
        }

        return blackPawns;
    }

    private static Map<Position, Piece> getBlankPieces() {
        Map<Position, Piece> blankPieces = new LinkedHashMap<>();
        for (int rank = 6; rank >= 3; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                blankPieces.put(Position.of(file, rank), Empty.of());
            }
        }

        return blankPieces;
    }

    private static Map<Position, Piece> getWhitePawns() {
        Map<Position, Piece> whitePawns = new LinkedHashMap<>();
        for (char file = 'a'; file <= 'h'; file++) {
            whitePawns.put(Position.of(file, 2), Pawn.colorOf(Color.WHITE));
        }

        return whitePawns;
    }

    private static Map<Position, Piece> getWhitePiecesExceptPawn() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        chessBoard.put(Position.of('a', 1), Rook.colorOf(Color.WHITE));
        chessBoard.put(Position.of('b', 1), Knight.colorOf(Color.WHITE));
        chessBoard.put(Position.of('c', 1), Bishop.colorOf(Color.WHITE));
        chessBoard.put(Position.of('d', 1), Queen.colorOf(Color.WHITE));
        chessBoard.put(Position.of('e', 1), King.colorOf(Color.WHITE));
        chessBoard.put(Position.of('f', 1), Bishop.colorOf(Color.WHITE));
        chessBoard.put(Position.of('g', 1), Knight.colorOf(Color.WHITE));
        chessBoard.put(Position.of('h', 1), Rook.colorOf(Color.WHITE));

        return chessBoard;
    }
}
