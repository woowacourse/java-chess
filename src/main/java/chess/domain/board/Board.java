package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class Board {
    private static final int TEAM_COUNT = 2;

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> board) {
        return new Board(board);
    }

    public boolean hasPieceIn(List<Position> path) {
        return path.stream()
                .anyMatch(key -> get(key).isObstacle());
    }

    public void update(Position from, Position to) {
        board.replace(to, get(from));
        board.replace(from, new Empty(from));
    }

    public boolean isEnd() {
        return board.values()
                .stream()
                .filter(Piece::hasToAlive)
                .count() != TEAM_COUNT;
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
