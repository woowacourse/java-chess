package chessrefactor;

import chessrefactor.piece.Bishop;
import chessrefactor.piece.Color;
import chessrefactor.piece.Empty;
import chessrefactor.piece.King;
import chessrefactor.piece.Knight;
import chessrefactor.piece.Pawn;
import chessrefactor.piece.Piece;
import chessrefactor.piece.Queen;
import chessrefactor.piece.Rook;
import chessrefactor.square.File;
import chessrefactor.square.Rank;
import chessrefactor.square.Square;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Board {

    private final Map<Square, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        final List<File> files = Arrays.asList(File.values());

        initChivalry(Color.WHITE, Rank.ONE, files);
        initChivalry(Color.BLACK, Rank.EIGHT, files);
        initPawns(Color.WHITE, Rank.TWO, files);
        initPawns(Color.BLACK, Rank.SEVEN, files);
        initEmpty();
        System.out.println(board.size());
    }

    private void initEmpty() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Square square = new Square(rank, file);
                if (!board.containsKey(square)) {
                    board.put(square, new Empty(Color.EMPTY));
                }
            }
        }
    }

    private void initPawns(Color color, Rank rank, List<File> files) {
        for (int i = 0; i < 8; i++) {
            board.put(new Square(rank, files.get(i)), new Pawn(color));
        }
    }

    private void initChivalry(Color color, Rank rank, List<File> files) {
        List<Piece> chivalryLineup = chivalryLineup(color);
        for (int i = 0; i < chivalryLineup.size(); i++) {
            board.put(new Square(rank, files.get(i)), chivalryLineup.get(i));
        }
    }

    private List<Piece> chivalryLineup(final Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + board +
                '}';
    }
}
