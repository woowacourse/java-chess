package chess.model.domain.board;

import static java.util.stream.Collectors.toMap;

import chess.model.domain.piece.Color;
import chess.model.domain.piece.Empty;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.PieceType;
import chess.model.domain.position.Path;
import chess.model.domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Map<Position, Piece> board;
    private final Turn turn;

    public Board(final Map<Position, Piece> board, final Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public Board(final Map<Position, Piece> board) {
        this(board, new Turn());
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
        final Score blackScore = Score.calculateScore(blackBoard);
        final Score whiteScore = Score.calculateScore(whiteBoard);

        return Map.of(Color.BLACK, blackScore, Color.WHITE, whiteScore);
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
        return Map.copyOf(board);
    }

    public Turn getTurn() {
        return new Turn(turn.getTurn());
    }
}
