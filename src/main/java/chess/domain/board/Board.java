package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    public Board() {
        this(Collections.emptyMap(), Color.WHITE);
    }

    private Board(final Map<Position, Piece> board, final Color turn) {
        this.board = new HashMap<>(board);
        this.turn = turn;
    }

    public void initialize() {
        board.putAll(BoardGenerator.generate());
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        final Piece piece = board.get(sourcePosition);

        validate(sourcePosition, targetPosition, piece);
        movePiece(sourcePosition, targetPosition, piece);
    }

    private void validate(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        if (turn.isOpponent(piece.color())) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
        if (!piece.isMovable(sourcePosition, targetPosition, board.get(targetPosition))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어 입니다.");
        }
        if (isPieceExistsBetweenPosition(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있을 수 없습니다.");
        }
    }

    private boolean isPieceExistsBetweenPosition(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.between(targetPosition).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !board.get(position).equals(Empty.instance());
    }

    private void movePiece(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        board.put(targetPosition, piece);
        board.put(sourcePosition, Empty.instance());
        turn = turn.nextTurn();
    }

    public boolean isInitialized() {
        return board.size() != 0;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
