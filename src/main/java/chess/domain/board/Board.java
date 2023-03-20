package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class Board {

    private static final String TURN_IS_NOT_EQUAL_PIECE_COLOR_MESSAGE = "차례에 맞는 말을 선택해 주세요";
    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Map<Position, Piece> board;

    public Board(final BoardFactory boardFactory) {
        this.board = boardFactory.createInitialBoard();
    }

    public void move(final Position from, final Position to, final Color turn) {
        validateIsFromEmpty(from);
        validateColorTurn(from, turn);
        final Path path = board.get(from)
                .searchPathTo(from, to, Optional.ofNullable(board.get(to)));
        path.validateObstacle(board.keySet());
        final Piece piece = board.remove(from);
        board.put(to, piece);
    }

    private void validateColorTurn(final Position from, final Color turn) {
        if (!board.get(from).isSameColor(turn)) {
            throw new IllegalArgumentException(TURN_IS_NOT_EQUAL_PIECE_COLOR_MESSAGE);
        }
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException(FROM_IS_EMPTY_MESSAGE);
        }
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
