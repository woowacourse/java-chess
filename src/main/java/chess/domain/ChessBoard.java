package chess.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final List<List<Piece>> chessBoard;
    private Map<Square, Piece> chessBoard2 = new HashMap<>();

    public ChessBoard() {
        List<Piece> rank8 = new ArrayList<>(8);
        List<Piece> rank7 = new ArrayList<>(8);
        List<Piece> rank3456 = new ArrayList<>(8);
        List<Piece> rank2 = new ArrayList<>(8);
        List<Piece> rank1 = new ArrayList<>(8);

        rank8.add(Piece.of(Color.BLACK, Type.ROOK));
        rank8.add(Piece.of(Color.BLACK, Type.KNIGHT));
        rank8.add(Piece.of(Color.BLACK, Type.BISHOP));
        rank8.add(Piece.of(Color.BLACK, Type.QUEEN));
        rank8.add(Piece.of(Color.BLACK, Type.KING));
        rank8.add(Piece.of(Color.BLACK, Type.BISHOP));
        rank8.add(Piece.of(Color.BLACK, Type.KNIGHT));
        rank8.add(Piece.of(Color.BLACK, Type.ROOK));

        rank1.add(Piece.of(Color.WHITE, Type.ROOK));
        rank1.add(Piece.of(Color.WHITE, Type.KNIGHT));
        rank1.add(Piece.of(Color.WHITE, Type.BISHOP));
        rank1.add(Piece.of(Color.WHITE, Type.QUEEN));
        rank1.add(Piece.of(Color.WHITE, Type.KING));
        rank1.add(Piece.of(Color.WHITE, Type.BISHOP));
        rank1.add(Piece.of(Color.WHITE, Type.KNIGHT));
        rank1.add(Piece.of(Color.WHITE, Type.ROOK));

        for (int i = 0; i < 8; i++) {
            rank2.add(Piece.of(Color.WHITE, Type.PAWN));
            rank7.add(Piece.of(Color.BLACK, Type.PAWN));
            rank3456.add(null);
        }

        List<List<Piece>> chessBoard = new ArrayList<>();

        chessBoard.add(rank8);
        chessBoard.add(rank7);
        chessBoard.add(rank3456);
        chessBoard.add(rank3456);
        chessBoard.add(rank3456);
        chessBoard.add(rank3456);
        chessBoard.add(rank2);
        chessBoard.add(rank1);
        this.chessBoard = chessBoard;

        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard2.put(Square.of(file + "2"), Piece.of(Color.WHITE, Type.PAWN));
            chessBoard2.put(Square.of(file + "7"), Piece.of(Color.BLACK, Type.PAWN));
        }

        Type[] arr = new Type[]{Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK};
        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            if (i == 4) {
                chessBoard2.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard2.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i + 1]));
                continue;
            }
            if (i == 5) {
                chessBoard2.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
                chessBoard2.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i - 1]));
                continue;
            }
            chessBoard2.put(Square.of(file + "1"), Piece.of(Color.WHITE, arr[i]));
            chessBoard2.put(Square.of(file + "8"), Piece.of(Color.BLACK, arr[i]));
        }
    }

    public List<List<Piece>> getChessBoard() {
        return chessBoard;
    }

    public Map<Square, Piece> getChessBoard2() {
        return chessBoard2;
    }
}