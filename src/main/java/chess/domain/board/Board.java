package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Map;

public class Board {

    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Turn turn = new Turn();
    private final Map<Position, Piece> board;

    public Board(final BoardFactory boardFactory) {
        this.board = boardFactory.createInitialBoard();
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

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
