package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;

public class ChessManager {
    private static final Owner FIRST_TURN = Owner.WHITE;

    private final Board board;
    private Owner turn = FIRST_TURN;
    private boolean isPlaying = true;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final Position source, final Position target) {
        validateSourcePiece(source);
        validateTurn(source);
        board.move(source, target);
        changeTurn();
        checkCaughtKing();
    }

    private void validateSourcePiece(final Position source) {
        if (board.pickPiece(source).isEmptyPiece()) {
            throw new IllegalArgumentException("지정한 칸에는 체스말이 존재하지 않습니다.");
        }
    }

    private void validateTurn(final Position source) {
        if (!board.isPositionSameOwner(source, turn)) {
            throw new IllegalArgumentException("현재는 " + turn.name() + "플레이어의 턴입니다.");
        }
    }

    public void changeTurn() {
        turn = turn.reverse();
    }

    private void checkCaughtKing() {
        if (isKingDead()) {
            endGame();
        }
    }

    private boolean isKingDead() {
        return !board.isKingAlive(this.turn);
    }

    public void endGame() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void resetBoard() {
        turn = FIRST_TURN;
        board.resetBoard();
    }

    public Path movablePath(final Position source) {
        validateSourcePiece(source);
        return board.movablePath(source);
    }

    public GameStatus gameStatus() {
        return GameStatus.statusOfBoard(board);
    }

    public Board getBoard() {
        return board;
    }
}
