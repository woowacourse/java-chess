package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.piece.Owner;

import java.util.List;

public class ChessManager {
    private final Board board;
    private Owner turn = Owner.WHITE;
    private boolean isEnd = false;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final MoveCommand command) {
        validateTurn(command.source());
        boolean isKing = isTargetKing(command.target());
        board.move(command.source(), command.target());
        // TODO 왕이 죽었는지 체크하는 로직 더 생각해보기
        isEnd = isKing;
        turn = turn.reverse();
    }

    private void validateTurn(final Position source) {
        if (!board.isPositionOwner(source, turn)) {
            throw new IllegalArgumentException("현재는 " + turn.name() + "플레이어의 턴입니다.");
        }
    }

    private boolean isTargetKing(Position target) {
        return board.isTargetKing(target);
    }

    public Path getReachablePositions(final ShowCommand command) {
        return board.getAbleToMove(command.source());
    }

    public Status getStatus() {
        return Status.statusOfBoard(board);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void resetBoard() {
        board.resetBoard();
    }

    public Board getBoard() {
        return board;
    }
}
