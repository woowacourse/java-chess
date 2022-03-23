package chess.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
        Iterator<Name> names = Name.getBaseLineNames().iterator();
        while (files.hasNext() && names.hasNext()) {
            board.put(new Square(files.next(), rank), new Piece(names.next(), color));
        }
    }

    private void initPawns(Rank rank, Color color) {
        initByFiles(rank, color, Name.PAWN);
    }

    private void initByFiles(Rank rank, Color color, Name name) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), new Piece(name, color));
        }
    }

    private void initEmpty() {
        for (Rank rank : Rank.emptyBaseLine()) {
            initByFiles(rank, Color.NOTHING, Name.NOTHING);
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
