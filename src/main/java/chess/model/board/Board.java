package chess.model.board;

import chess.model.piece.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Board {
    public static final int MAX_LENGTH = 8;
    public static final int MIN_LENGTH = 1;
    private static final List<Position> ALL_POSITIONS = Position.values();

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
        ALL_POSITIONS.forEach(position -> this.squares.putIfAbsent(position, Piece.EMPTY));
    }

    public List<String> getSignatures() {
        return squares.values().stream()
                .map(Piece::getSignature)
                .toList();
    }

    public List<List<String>> getLines() {
        List<List<String>> lines = new ArrayList<>();
        for (int y = MAX_LENGTH; y >= MIN_LENGTH; y--) {
            lines.add(getLine(y));
        }
        return lines;
    }

    private List<String> getLine(int lineIndex) {
        return IntStream.rangeClosed(MIN_LENGTH, MAX_LENGTH)
                .mapToObj(x -> squares.get(Position.from(x, lineIndex)).getSignature())
                .toList();
    }
}
