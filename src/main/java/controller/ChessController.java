package controller;

import domain.board.Board;
import domain.board.InitialBoardGenerator;
import view.dto.MovePositionDto;

public class ChessController {

    private final Board board = Board.generatedBy(new InitialBoardGenerator());

    public Board move(MovePositionDto movePositionDto) {
        board.move(movePositionDto.sourcePosition(), movePositionDto.targetPosition(), movePositionDto.color());
        return board;
    }

    public Board getBoard() {
        return board;
    }
}
