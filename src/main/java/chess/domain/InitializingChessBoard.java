package chess.domain;

import chess.domain.piece.*;

import java.util.Map;

public class InitializingChessBoard {

    public static void initPiecesLocation(File file, Map<Square, Piece> chessBoard) {
        initPawnLocation(file, chessBoard);
        initRookLocation(file, chessBoard);
        initKnightLocation(file, chessBoard);
        initBishopLocation(file, chessBoard);
        initQueenLocation(file, chessBoard);
        initKingLocation(file, chessBoard);
    }

    private static void initQueenLocation(File file, Map<Square, Piece> chessBoard) {
        if (file.equals(File.D)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Queen.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Queen.of(Color.BLACK));
        }
    }

    private static void initKingLocation(File file, Map<Square, Piece> chessBoard) {
        if (file.equals(File.E)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), King.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), King.of(Color.BLACK));
        }
    }

    private static void initBishopLocation(File file, Map<Square, Piece> chessBoard) {
        if (file.equals(File.C) || file.equals(File.F)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Bishop.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Bishop.of(Color.BLACK));
        }
    }

    private static void initKnightLocation(File file, Map<Square, Piece> chessBoard) {
        if (file.equals(File.B) || file.equals(File.G)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Knight.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Knight.of(Color.BLACK));
        }
    }

    private static void initRookLocation(File file, Map<Square, Piece> chessBoard) {
        if (file.equals(File.A) || file.equals(File.H)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Rook.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Rook.of(Color.BLACK));
        }
    }

    private static void initPawnLocation(File file, Map<Square, Piece> chessBoard) {
        chessBoard.put(Square.of(file.getName(), Rank.TWO.getName()), Pawn.of(Color.WHITE));
        chessBoard.put(Square.of(file.getName(), Rank.SEVEN.getName()), Pawn.of(Color.BLACK));
    }

}
