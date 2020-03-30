package chess.domain2;

import chess.domain2.piece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        for (File file : File.values()) {
            initPiecesLocation(file);
        }
    }

    private void initPiecesLocation(File file) {
        initPawnLocation(file);
        initRookLocation(file);
        initKnightLocation(file);
        initBishopLocation(file);
        initQueenLocation(file);
        initKingLocation(file);
    }

    private void initQueenLocation(File file) {
        if (file.equals(File.D)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Queen.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Queen.of(Color.BLACK));
        }
    }

    private void initKingLocation(File file) {
        if (file.equals(File.E)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), King.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), King.of(Color.BLACK));
        }
    }

    private void initBishopLocation(File file) {
        if (file.equals(File.C) || file.equals(File.F)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Bishop.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Bishop.of(Color.BLACK));
        }
    }

    private void initKnightLocation(File file) {
        if (file.equals(File.B) || file.equals(File.G)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Knight.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Knight.of(Color.BLACK));
        }
    }

    private void initRookLocation(File file) {
        if (file.equals(File.A) || file.equals(File.H)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Rook.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Rook.of(Color.BLACK));
        }
    }

    private void initPawnLocation(File file) {
        chessBoard.put(Square.of(file.getName(), Rank.TWO.getName()), Pawn.of(Color.WHITE));
        chessBoard.put(Square.of(file.getName(), Rank.SEVEN.getName()), Pawn.of(Color.BLACK));
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }
}
