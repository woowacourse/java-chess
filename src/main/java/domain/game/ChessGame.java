package domain.game;

import domain.board.Board;
import view.dto.MovePositionDto;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = Turn.makeInitialTurn();
    }

    public Board startTurn(MovePositionDto movePositionDto) {
        Board movedBoard = board.move(movePositionDto.source(), movePositionDto.target(), turn);
        turn = turn.changeTurn();
        return movedBoard;
    }

    public Board getBoard() {
        return board;
    }
}
