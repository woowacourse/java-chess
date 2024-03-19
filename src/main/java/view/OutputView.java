package view;

import domain.ChessBoard;
import domain.Color;
import domain.Piece;
import domain.PieceType;
import domain.Rank;
import domain.Square;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final Map<Piece, String> pieceSymbol = new HashMap<>();

    static {
        pieceSymbol.put(new Piece(PieceType.ROOK, Color.BLACK), "R");
        pieceSymbol.put(new Piece(PieceType.KNIGHT, Color.BLACK), "N");
        pieceSymbol.put(new Piece(PieceType.BISHOP, Color.BLACK), "B");
        pieceSymbol.put(new Piece(PieceType.QUEEN, Color.BLACK), "Q");
        pieceSymbol.put(new Piece(PieceType.KING, Color.BLACK), "K");
        pieceSymbol.put(new Piece(PieceType.PAWN, Color.BLACK), "P");

        pieceSymbol.put(new Piece(PieceType.ROOK, Color.WHITE), "r");
        pieceSymbol.put(new Piece(PieceType.KNIGHT, Color.WHITE), "n");
        pieceSymbol.put(new Piece(PieceType.BISHOP, Color.WHITE), "b");
        pieceSymbol.put(new Piece(PieceType.QUEEN, Color.WHITE), "q");
        pieceSymbol.put(new Piece(PieceType.KING, Color.WHITE), "k");
        pieceSymbol.put(new Piece(PieceType.PAWN, Color.WHITE), "p");

        pieceSymbol.put(new Piece(PieceType.NONE, Color.NONE), ".");
    }

    public void printChessBoard(final ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getRanks();
        for (Rank rank : ranks) {
            printRankMessage(rank);
        }
    }

    private void printRankMessage(final Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Square square : rank.getSquares()) {
            String symbol = pieceSymbol.get(square.getPiece());
            stringBuilder.append(symbol);
        }
        System.out.println(stringBuilder);
    }
}
