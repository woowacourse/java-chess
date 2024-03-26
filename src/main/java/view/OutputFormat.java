package view;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class OutputFormat {

    private static final Map<Piece, String> pieceSymbol = new HashMap<>();
    public static final int CHESSBOARD_SIZE = 8;

    static {
        pieceSymbol.put(new Piece(new Rook(), Color.BLACK), "R");
        pieceSymbol.put(new Piece(new Knight(), Color.BLACK), "N");
        pieceSymbol.put(new Piece(new Bishop(), Color.BLACK), "B");
        pieceSymbol.put(new Piece(new Queen(), Color.BLACK), "Q");
        pieceSymbol.put(new Piece(new King(), Color.BLACK), "K");
        pieceSymbol.put(new Piece(new BlackPawn(), Color.BLACK), "P");

        pieceSymbol.put(new Piece(new Rook(), Color.WHITE), "r");
        pieceSymbol.put(new Piece(new Knight(), Color.WHITE), "n");
        pieceSymbol.put(new Piece(new Bishop(), Color.WHITE), "b");
        pieceSymbol.put(new Piece(new Queen(), Color.WHITE), "q");
        pieceSymbol.put(new Piece(new King(), Color.WHITE), "k");
        pieceSymbol.put(new Piece(new WhitePawn(), Color.WHITE), "p");
    }

    public String parseChessBoard(final Map<Position, Piece> chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int rank = CHESSBOARD_SIZE; rank >= 1; rank--) {
            parsePositionByFile(chessBoard, rank);
            stringBuilder.append(parsePositionByFile(chessBoard, rank)).append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String parsePositionByFile(Map<Position, Piece> chessBoard, int rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = 0; file < CHESSBOARD_SIZE; file++) {
            Position position = new Position(new Position(new File((char) ('a' + file)), new Rank(rank)));
            stringBuilder.append(parseSymbol(chessBoard, position));
        }
        return stringBuilder.toString();
    }

    private String parseSymbol(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            return pieceSymbol.get(piece);
        }
        return ".";
    }

}
