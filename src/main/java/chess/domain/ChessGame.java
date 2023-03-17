package chess.domain;

import chess.domain.piece.Piece;
import java.util.List;

public class ChessGame {

    private boolean playable;
    private final Board board;

    public ChessGame(final Board board) {
        this.playable = false;
        this.board = board;
    }

    public void start() {
        this.playable = true;
    }

    public void end() {
        this.playable = false;
    }

    public void moveOrNot(final Position source, final Position target) {
        final Piece sourcePiece = board.getPiece(source.getFile(), source.getRank());
        final List<Position> movablePositions = sourcePiece.findMovablePositions(source, board);
        checkMovable(movablePositions, target);
        board.move(source, target);
        if (sourcePiece.isPawn()) {
            sourcePiece.changePawnMoved();
        }
    }

    private void checkMovable(final List<Position> movablePositions, final Position target) {
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    public boolean isPlayable() {
        return playable;
    }

    public Board getBoard() {
        return board;
    }
}
