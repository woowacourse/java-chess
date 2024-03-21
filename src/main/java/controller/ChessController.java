package controller;

//import domain.*;

import domain.*;
import dto.BoardDto;
import dto.RequestDto;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        GameCommand command = inputView.inputGameStart();
        if (command.isStart()) {
            startGame();
        }
    }

    private void startGame() {
        Board board = BoardInitializer.init();
        printStatus(board);

        Turn turn = new Turn();
        RequestDto requestDto = inputView.inputGameCommand();
        while (requestDto.command().isContinuable()) {
            doTurn(board, turn, requestDto);
            printStatus(board);
            requestDto = inputView.inputGameCommand();
        }
    }

    private void doTurn(final Board board, Turn turn, final RequestDto requestDto) {
        try {
            board.movePiece(turn.current(), requestDto.source(), requestDto.destination());
            turn.next();
        } catch (IllegalArgumentException e) {
            System.out.println("[오류] " + e.getMessage());
        }
    }

    private TeamColor defineTeam(boolean isWhiteTurn) {
        if (isWhiteTurn) {
            return TeamColor.WHITE;
        }
        return TeamColor.BLACK;
    }

    private void printStatus(Board board) {
        BoardDto boardDto = BoardDto.from(board);
        outputView.printBoard(boardDto);
    }
}
