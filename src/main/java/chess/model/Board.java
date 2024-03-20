package chess.model;

import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final List<String> INITIAL_BOARD = List.of(
        "RNBQKBNR",
        "PPPPPPPP",
        "........",
        "........",
        "........",
        "........",
        "pppppppp",
        "rnbqkbnr"
    );

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board createInitialBoard() {
        return new Board(generateBoard(INITIAL_BOARD));
    }

    public static Board createCustomBoard(List<String> customBoard) {
        return new Board(generateBoard(customBoard));
    }

    private static Map<Position, Piece> generateBoard(List<String> customBoard) {
        Map<Position, Piece> board = new HashMap<>();
        for (int i = 0; i < customBoard.size(); i++) {
            String row = customBoard.get(i);
            putPiecesInRow(board, row, i);
        }
        return board;
    }

    private static void putPiecesInRow(Map<Position, Piece> board, String row, int rowIndex) {
        for (int j = 0; j < row.length(); j++) {
            Position position = new Position(rowIndex, j);
            char pieceName = row.charAt(j);
            PieceType pieceType = PieceType.findPieceTypeByName(String.valueOf(pieceName));
            Piece piece = Piece.from(pieceType);
            board.put(position, piece);
        }
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
