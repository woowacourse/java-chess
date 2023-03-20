package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class Board {

    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Map<Position, Piece> board;
    private final Turn turn = new Turn();

    public Board(final BoardFactory boardFactory) {
        this.board = boardFactory.createInitialBoard();
    }

    public void move(final Position from, final Position to) {
        validateIsFromEmpty(from);
        turn.validateStartPieceColor(board.get(from));
        final Path path = board.get(from)
                .searchPathTo(from, to, Optional.ofNullable(board.get(to)));
        path.validateObstacle(board.keySet());
        final Piece piece = board.remove(from);
        board.put(to, piece);
        turn.changeTurn();
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
