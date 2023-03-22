package chess.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Pawn;
import chess.domain.piece.King;
import chess.domain.piece.Bishop;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class NameBoard {

    private static final Map<Class<? extends Piece>, Name> nameByPiece = new HashMap<>();
    private static final int BOARD_ROW_SIZE = 8;

    private final List<List<String>> nameBoard;

    static {
        nameByPiece.put(King.class, Name.KING);
        nameByPiece.put(Queen.class, Name.QUEEN);
        nameByPiece.put(Bishop.class, Name.BISHOP);
        nameByPiece.put(Knight.class, Name.KNIGHT);
        nameByPiece.put(Rook.class, Name.ROOK);
        nameByPiece.put(Pawn.class, Name.PAWN);
    }

    private NameBoard(List<List<String>> nameBoard) {
        this.nameBoard = new ArrayList<>(nameBoard);
    }

    public static NameBoard generateNameBoard(List<Piece> pieces) {
        List<List<String>> nameBoard = new ArrayList<>();
        initNameBoard(nameBoard);
        fillNameBoardWithNames(pieces, nameBoard);
        return new NameBoard(nameBoard);
    }

    private static void initNameBoard(List<List<String>> nameBoard) {
        final List<String> emptyNames = List.of(".", ".", ".", ".", ".", ".", ".", ".");
        for (int rowIndex = 0; rowIndex < BOARD_ROW_SIZE; rowIndex++) {
            List<String> row = new ArrayList<>(emptyNames);
            nameBoard.add(row);
        }
    }

    private static void fillNameBoardWithNames(List<Piece> pieces, List<List<String>> nameBoard) {
        for (Piece piece : pieces) {
            final String pieceName = generateName(piece);
            int flippedIndex = getFlippedIndex(piece.getRank());
            final List<String> rowNames = nameBoard.get(flippedIndex);
            final int columnIndex = piece.getFile() - 1;
            rowNames.set(columnIndex, pieceName);
        }
    }

    private static String generateName(Piece piece) {
        Name pieceName = nameByPiece.get(piece.getClass());
        Side side = piece.getSide();
        if (Side.BLACK == side) {
            return pieceName.getUpperCaseValue();
        }
        return pieceName.getLowerCaseValue();
    }

    private static int getFlippedIndex(int originalIndex) {
        return Math.abs(originalIndex - BOARD_ROW_SIZE);
    }

    public List<List<String>> getNameBoard() {
        return List.copyOf(nameBoard);
    }
}
