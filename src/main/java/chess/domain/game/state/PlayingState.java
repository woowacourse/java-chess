package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public abstract class PlayingState implements GameState {

    private final Board board;

    protected PlayingState(final Board board) {
        this.board = board;
    }

    protected Board currentBoard() {
        return board;
    }

    protected Board moveInBoard(final Position sourceName, final Position targetName, final Color color) {
        validateMovement(sourceName, targetName, color);
        return board.movePiece(sourceName, targetName);
    }

    private void validateMovement(final Position sourceName, final Position targetName, final Color color) {
        validateOwner(sourceName, color);
        validatePath(sourceName, targetName);
    }

    private void validateOwner(final Position sourceName, final Color color) {
        final Piece piece = board.pieceAt(sourceName);
        if (piece.isColor(color.reversed())) {
            throw new IllegalArgumentException("해당 체스말을 움직일 권한이 없습니다.");
        }
    }

    private void validatePath(final Position sourceName, final Position targetName) {
        if (!board.hasAvailablePath(sourceName, targetName)) {
            throw new IllegalArgumentException("경로 안에서 체스말이 이동할 수 없습니다.");
        }
    }
}
