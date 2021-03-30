package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.piece.Owner;

public class ChessManager {
    private static final Owner FIRST_TURN = Owner.WHITE;

    private final Board board;
    private Owner turn = FIRST_TURN;
    private boolean isEnd = false;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final MoveCommand command) {
        validateSourcePiece(command.source());
        validateTurn(command.source());
        board.move(command.source(), command.target());
    }

    public void changeTurn() {
        turn = turn.reverse();
    }

    public void endGameByDiedKing() {
        isEnd = isDiedKing();
    }

    private boolean isDiedKing() {
        return !board.isKingAlive(this.turn.reverse());
    }

    private void validateSourcePiece(final Position source) {
        if (board.pickPiece(source).isEmpty()) {
            throw new IllegalArgumentException("지정한 칸에는 체스말이 존재하지 않습니다.");
        }
    }

    private void validateTurn(final Position source) {
        if (!board.isPositionSameOwner(source, turn)) {
            throw new IllegalArgumentException("현재는 " + turn.name() + "플레이어의 턴입니다.");
        }
    }

    public Path getReachablePositions(final ShowCommand command) {
        validateSourcePiece(command.source());
        return board.getAbleToMove(command.source());
    }

    public Status getStatus() {
        return Status.statusOfBoard(board);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void resetBoard() {
        turn = FIRST_TURN;
        board.resetBoard();
    }

    public Board getBoard() {
        return board;
    }
}
