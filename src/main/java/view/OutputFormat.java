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
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class OutputFormat {

    public static final int CHESSBOARD_SIZE = 8;
    public static final char START_RANK = 'a';
    public static final String BLANK_POSITION = ".";
    private static final Map<Piece, String> PIECE_SYMBOL = new HashMap<>();

    static {
        PIECE_SYMBOL.put(new Piece(new Rook(), Color.BLACK), "R");
        PIECE_SYMBOL.put(new Piece(new Knight(), Color.BLACK), "N");
        PIECE_SYMBOL.put(new Piece(new Bishop(), Color.BLACK), "B");
        PIECE_SYMBOL.put(new Piece(new Queen(), Color.BLACK), "Q");
        PIECE_SYMBOL.put(new Piece(new King(), Color.BLACK), "K");
        PIECE_SYMBOL.put(new Piece(new BlackPawn(), Color.BLACK), "P");

        PIECE_SYMBOL.put(new Piece(new Rook(), Color.WHITE), "r");
        PIECE_SYMBOL.put(new Piece(new Knight(), Color.WHITE), "n");
        PIECE_SYMBOL.put(new Piece(new Bishop(), Color.WHITE), "b");
        PIECE_SYMBOL.put(new Piece(new Queen(), Color.WHITE), "q");
        PIECE_SYMBOL.put(new Piece(new King(), Color.WHITE), "k");
        PIECE_SYMBOL.put(new Piece(new WhitePawn(), Color.WHITE), "p");
    }

    public String parseChessBoard(final Map<Position, Piece> chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int rank = CHESSBOARD_SIZE; rank >= 1; rank--) {
            stringBuilder.append(parsePositionByFile(chessBoard, rank)).append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String parsePositionByFile(Map<Position, Piece> chessBoard, int rank) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int file = 0; file < CHESSBOARD_SIZE; file++) {
            String currentRank = String.valueOf(rank);
            String currentFile = Character.toString(START_RANK + file);

            Position position = new Position(currentFile + currentRank);
            stringBuilder.append(parseSymbol(chessBoard, position));
        }
        return stringBuilder.toString();
    }

    private String parseSymbol(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            return PIECE_SYMBOL.get(piece);
        }
        return BLANK_POSITION;
    }
}
