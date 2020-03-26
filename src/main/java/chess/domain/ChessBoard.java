package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Square.of(file + "2"), Piece.of(Color.WHITE, Type.PAWN));
            chessBoard.put(Square.of(file + "7"), Piece.of(Color.BLACK, Type.PAWN));
        }

        Type[] arr = new Type[]{Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK};
        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            if (i == 3) {
                chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i + 1]));
                continue;
            }
            if (i == 4) {
                chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i - 1]));
                continue;
            }
            chessBoard.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
            chessBoard.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i]));
        }
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }
}