package chess.manager;

import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
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

    public void move(MoveCommand moveCommand) {
        board.move(moveCommand.source(), moveCommand.target(), turn);
        turn = turn.reverse();
    }

    public List<Position> getAbleToMove(ShowCommand showCommand){
        return board.getAbleToMove(showCommand.source());
    }

    public Status calculateStatus(){
        return board.getStatus();
    }

    public boolean isEnd() {
        return board.isEnd();
    }
}
