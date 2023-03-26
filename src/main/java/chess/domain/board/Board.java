package chess.domain.board;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Turn turn = new Turn();
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position from, final Position to) {
        validateIsFromEmpty(from);
        turn.validateStartPieceColor(board.get(from));
        final Piece movingPiece = board.get(from);
        final Path path = movingPiece
                .searchPathTo(from, to, board.getOrDefault(to, Empty.getInstance()));
        path.validateObstacle(board.keySet());
        board.remove(from);
        board.put(to, movingPiece);
        turn.changeTurn();
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException(FROM_IS_EMPTY_MESSAGE);
        }
    }

    public Map<Color, Score> calculateScore() {
        final Map<Position, Piece> blackBoard = filterByColor(Color.BLACK);
        final Map<Position, Piece> whiteBoard = filterByColor(Color.WHITE);
        final Score blackScore = calculateScore(blackBoard);
        final Score whiteScore = calculateScore(whiteBoard);

        return Map.of(Color.BLACK, blackScore, Color.WHITE, whiteScore);
    }

    private Score calculateScore(final Map<Position, Piece> board) {
        final Score subtrahend = new Score(sumPawnCountInSameFileOver2(board) * Score.PAWN_WEIGHT);
        return board.values()
                .stream()
                .map(piece -> Score.mapPieceScore(piece.getPieceType()))
                .reduce(Score.ZERO, Score::sum)
                .subtract(subtrahend);
    }

    private long sumPawnCountInSameFileOver2(final Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getPieceType() == PieceType.PAWN)
                .collect(groupingBy(entry -> entry.getKey().file(), counting()))
                .values()
                .stream()
                .filter(count -> count >= 2)
                .reduce(0L, Long::sum);
    }

    private Map<Position, Piece> filterByColor(final Color color) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameColor(turn.getTurn()))
                .noneMatch(piece -> piece.getPieceType() == PieceType.KING);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
