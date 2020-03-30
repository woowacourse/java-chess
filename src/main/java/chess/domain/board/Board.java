package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Board {
    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
    }

    public boolean hasPieceIn(List<Position> path) {
        return path.stream()
                .anyMatch(key -> getBoard().containsKey(key));
    }

    public void update(Position from, Position to) {
        board.replace(to, get(from));
        board.replace(from, new Empty(from));
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count() < 2;
    }

    public Collection<List<Piece>> getColumnGroupOf(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(groupingBy(
                        entry -> entry.getKey().getColumn(),
                        mapping(Map.Entry::getValue, toList())))
                .values();
    }

    public Piece get(Position key) {
        return board.get(key);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
