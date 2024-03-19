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

    private static final String FRONT_RANK_FORMAT = "%s  %d ";
    private static final String BACK_RANK_FORMAT = FRONT_RANK_FORMAT + "(rank %d)";
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

    }

    public void printChessBoard(final ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getRanks();

        printBackRank(ranks.get(0), 8);
        for (int index = 1; index <= 6; index++) {
            printFrontRank(ranks.get(index), 8 - index);
        }
        printBackRank(ranks.get(7), 1);
    }

    private void printBackRank(final Rank rank, int index) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Square square : rank.getSquares()) {
            String symbol = pieceSymbol.get(square.getPiece());
            stringBuilder.append(symbol);
        }
        System.out.printf(BACK_RANK_FORMAT, stringBuilder, index, index);
    }

    private void printFrontRank(final Rank rank, int index) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Square square : rank.getSquares()) {
            String symbol = pieceSymbol.get(square.getPiece());
            stringBuilder.append(symbol);
        }
        System.out.printf(FRONT_RANK_FORMAT, stringBuilder, index);
    }
}
