package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final BoardFactory boardFactory) {
        this.board = boardFactory.createInitialBoard();
    }

    public Map<Position, Piece> board() {
        return board;
    }

    public void move(final Position from, final Position to, final Color turn) {
        validateColorTurn(from, turn);
        validateIsFromEmpty(from);
        final Path path = board.get(from)
                .searchPathTo(from, to, Optional.ofNullable(board.get(to)));
        path.validateObstacle(board.keySet());
        final Piece piece = board.remove(from);
        board.put(to, piece);
    }

    private void validateColorTurn(final Position from, final Color turn) {
        if (!board.get(from).isSameColor(turn)) {
            throw new IllegalArgumentException("차례에 맞는 말을 선택해 주세요");
        }
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }
}
