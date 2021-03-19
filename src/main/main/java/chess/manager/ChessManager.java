package chess.manager;

import chess.domain.MoveCommand;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;

public class ChessManager {
    private final Board board;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void readCommand(MoveCommand moveCommand) {
        System.out.println(board.of(moveCommand.source()));
        System.out.println(board.of(moveCommand.target()));
        board.move(moveCommand.source(), moveCommand.target());
    }
}
