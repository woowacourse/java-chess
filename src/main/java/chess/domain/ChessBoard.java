package chess.domain;

import java.util.HashMap;
import java.util.List;
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

    public boolean canMove(List<Square> squares, boolean blackTurn) {
        Square before = squares.get(0);
        Square after = squares.get(1);
        if (!chessBoard.containsKey(before) || chessBoard.get(before).isBlack() != blackTurn) {
            return false;
        }
        return chessBoard.get(before).calculateMoveBoundary(before, chessBoard).contains(after);
    }

    public void movePiece(List<Square> squares) {
        Square before = squares.get(0);
        Square after = squares.get(1);
        Piece currentPiece = chessBoard.remove(before);
        chessBoard.put(after, currentPiece);
    }

    public boolean isKingCaptured() {
        return !(chessBoard.containsValue(Piece.of(Color.WHITE, Type.KING))
                && chessBoard.containsValue(Piece.of(Color.BLACK, Type.KING)));
    }
}