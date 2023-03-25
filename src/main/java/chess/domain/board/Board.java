package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
        this.turn = Color.WHITE;
    }

    public static Board from(final Map<Position, Piece> board) {
        return new Board(board);
    }

    public static Board create() {
        return new Board(new HashMap<>());
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
        if (piece.isUnmovable(sourcePosition, targetPosition, board.get(targetPosition))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어입니다.");
        }
        if (isPieceExistsBetweenPositions(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    private boolean isPieceExistsBetweenPositions(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.between(targetPosition).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !isPieceNonExists(position);
    }

    private boolean isPieceNonExists(final Position position) {
        return board.get(position).equals(Empty.getInstance());
    }

    private void movePiece(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        board.put(targetPosition, piece);
        board.put(sourcePosition, Empty.getInstance());
        turn = turn.nextTurn();
    }

    public boolean isKingDead(final Color color) {
        return !board.containsValue(King.from(color));
    }

    public void clear() {
        board.clear();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
