package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;

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
