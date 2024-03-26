package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {
    private static final Empty EMPTY = new Empty();

    private final Map<Position, Piece> board;
    private final Color color;

    public Board(final Map<Position, Piece> board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Board move(final Position source, final Position target) {
        Piece piece = board.get(source);
        validateEmpty(source);
        validateColorTurn(piece);
        validateMovement(source, target, piece);

        board.put(target, piece);
        board.put(source, EMPTY);

        return new Board(board, color.opposite());
    }

    private void validateEmpty(Position source) {
        if (board.get(source).isEmpty()) {
            throw new IllegalArgumentException("기물이 존재하지 않아 이동시킬 수 없습니다.");
        }
    }

    private void validateColorTurn(Piece piece) {
        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException("상대 팀의 기물을 이동시킬 수 없습니다.");
        }
    }

    private void validateMovement(Position source, Position target, Piece piece) {
        if (!piece.canMove(source, target, getBoard())) {
            throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
