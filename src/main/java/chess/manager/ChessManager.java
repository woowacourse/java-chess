package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.piece.Owner;

import java.util.List;

public class ChessManager {
    private final Board board;
    private Owner turn = Owner.WHITE;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void move(final MoveCommand command) {
        validateTurn(command.source());
        board.move(command.source(), command.target());
        turn = turn.reverse();
    }

    private void validateTurn(final Position source) {
        if (!board.isPositionOwner(source, turn)) {
            throw new IllegalArgumentException("현재는 " + turn.name() + "플레이어의 턴입니다.");
        }
    }

    public List<Position> getReachablePositions(final ShowCommand command) {
        return board.getAbleToMove(command.source());
    }

    public Status calculateStatus() {
        return board.getStatus();
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public void resetBoard() {
        board.resetBoard();
    }
}
