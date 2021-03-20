package chess.manager;

import chess.domain.command.MoveCommand;
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
        board.move(moveCommand.source(), moveCommand.target());
    }

    public Status calculateStatus(){
        return board.getStatus();
    }

    public boolean isEnd() {
        return board.isEnd();
    }
}
