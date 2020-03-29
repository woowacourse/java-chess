package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Boards {
    private static final int LOWER_BOARD = 0;
    private static final int UPPER_BOARD = 1;
    private static final String KING = "k";

    private final List<Board> boards;

    public Boards(List<Board> boards) {
        this.boards = boards;
    }

    public static Boards of(Map<String, Piece> lowerBoard, Map<String, Piece> upperBoard) {
        List<Board> boards = new ArrayList<>();

        boards.add(new Board(lowerBoard));
        boards.add(new Board(upperBoard));

        return new Boards(boards);
    }

    public boolean hasPieceIn(List<String> path) {
        return path.stream()
                .anyMatch(key -> getTotal().containsKey(key));
    }

    public void move(String from, String to, Turn turn) {
        if (boards.get(turn.self()).get(from) instanceof Pawn) {
            pawnMove(from, to, turn);
        }
        boards.get(turn.self()).update(from, to);
        boards.get(turn.enemy()).remove(Position.getReversedNameOf(to));
    }

    private void pawnMove(String from, String to, Turn turn) {
        int columnGap = Math.abs(Position.of(from).getColumnGap(Position.of(to)));
        if (columnGap == 0 && isEnemy(to, turn)) {
            throw new IllegalArgumentException("폰은 전방의 적을 공격할 수 없습니다.");
        }
        if (columnGap == 1 && !isEnemy(to, turn)) {
            throw new IllegalArgumentException("폰은 공격이 아니면 대각선 이동이 불가합니다.");
        }
    }

    private boolean isEnemy(String to, Turn turn) {
        return boards.get(turn.enemy()).containsKey(Position.getReversedNameOf(to));
    }

    public boolean isKingDead() {
        return getTotal().values()
                .stream()
                .filter(piece -> piece.toLowerCase().equals(KING))
                .count() < 2;
    }

    public double getScoreOf(Turn turn) {
        return boards.get(turn.self()).getScore();
    }

    public Map<String, String> getTotal() {
        Map<String, String> result = new LinkedHashMap<>(getLowerBoard());
        result.putAll(getUpperBoard());
        return result;
    }

    private Map<String, String> getUpperBoard() {
        return boards.get(UPPER_BOARD).getReversedBoard().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getUpperName(),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, String> getLowerBoard() {
        return boards.get(LOWER_BOARD).getBoard().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getLowerName(),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
