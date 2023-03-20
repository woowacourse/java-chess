package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.view.OutputView;

public class StartController implements Controller {
    private final OutputView outputView;

    public StartController(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public Board execute(RequestInfo requestInfo, Board board) {
        try {
            validate(requestInfo, board);
            Board newBoard = BoardGenerator.makeBoard();
            outputView.printBoard(newBoard.getPiecePosition());
            return newBoard;
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return board;
        }
    }

    private void validate(RequestInfo requestInfo, Board board) {
        validateRequest(requestInfo);
        validateBoard(board);
    }

    private void validateRequest(RequestInfo requestInfo) {
        if (requestInfo.getGameCommand() != GameCommand.START) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBoard(Board board) {
        if (board != BoardGenerator.emtpyBoard()) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }
}
