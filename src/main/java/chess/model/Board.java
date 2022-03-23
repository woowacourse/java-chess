package chess.model;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Board {

    private final Map<Square, Piece> board;

    public Board() {
        this.board = new LinkedHashMap<>();
        initBlack();
        initEmpty();
        initWhite();
    }

    private void initBlack() {
        initBaseLine(Rank.ONE, Color.BLACK);
        initPawns(Rank.TWO, Color.BLACK);
    }

    private void initBaseLine(Rank rank, Color color) {
        Iterator<File> files = Arrays.stream(File.values()).iterator();
        Iterator<Piece> pieces = lineUp(color).iterator();
        while (files.hasNext() && pieces.hasNext()) {
            board.put(new Square(files.next(), rank), pieces.next());
        }
    }

    private List<Piece> lineUp(Color color) {
        return List.of(new Rook(color), new Knight(color), new Bishop(color), new Queen(color), new King(color),
                new Bishop(color), new Knight(color), new Rook(color));
    }

    private void initPawns(Rank rank, Color color) {
        initByFiles(rank, new Pawn(color));
    }

    private void initByFiles(Rank rank, Piece piece) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), piece);
        }
    }

    private void initEmpty() {
        for (Rank rank : Rank.emptyBaseLine()) {
            initByFiles(rank, new Empty());
        }
    }

    private void initWhite() {
        initPawns(Rank.SEVEN, Color.WHITE);
        initBaseLine(Rank.EIGHT, Color.WHITE);
    }

    public Set<Square> keySet() {
        return board.keySet();
    }

    public Piece get(final Square square) {
        return board.get(square);
    }
}
