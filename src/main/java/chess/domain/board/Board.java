package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;

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
        Piece piece = get(from);

        //todo Pawn으로 이동
        if (piece instanceof Pawn) {
            pawnMove(from, to);
        }

        board.replace(to, get(from));
        board.replace(from, new Empty(from, Team.NONE));
    }

    private void pawnMove(Position from, Position to) {
        int columnGap = Math.abs(from.getColumnGap(to));
        if (columnGap == 0 && board.containsKey(to)) {
            throw new IllegalArgumentException("폰은 전방의 적을 공격할 수 없습니다.");
        }
        if (columnGap == 1 && !board.containsKey(to)) {
            throw new IllegalArgumentException("폰은 공격이 아니면 대각선 이동이 불가합니다.");
        }
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count() < 2;
    }

    public double getScoreOf(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(groupingBy(
                        entry -> entry.getKey().getColumn(),
                        mapping(entry -> entry.getValue().getName(), toList())))
                .values().stream()
                .mapToDouble(Score::calculateScoreOf)
                .sum();
    }

    public Piece get(Position key) {
        return board.get(key);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
