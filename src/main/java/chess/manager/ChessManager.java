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
    private Owner turn = Owner.BLACK;

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
            throw new IllegalArgumentException("현재 턴의 기물이 아닙니다.");
        }
    }

    public List<Position> getReachablePositions(final ShowCommand command) {
        return board.getAblePositionsToMove(command.source());
    }

    public Status calculateStatus() {
        return board.status();
    }

    public boolean isEnd() {
        return board.isEnd();
    }
}
