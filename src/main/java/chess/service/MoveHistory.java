package chess.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoveHistory {

    private final List<Move> moves;

    public MoveHistory(List<Move> moves) {
        this.moves = moves;
    }

    public List<Move> getSortHistory() {
        return moves.stream()
                .sorted(Comparator.comparing(Move::getMoveTime))
                .collect(Collectors.toList());
    }
}
