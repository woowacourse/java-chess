package controller;

import domain.Board;
import domain.BoardInitializer;
import domain.TeamColor;
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
        RequestDto requestDto = inputView.inputGameCommand();
        Board board = BoardInitializer.init();
        printStatus(board);

        boolean isWhiteTurn = true;

        requestDto = inputView.inputGameCommand();
        while (requestDto.command().isContinuable()) {
            try {
                board.movePiece(defineTeam(isWhiteTurn), requestDto.source().get(), requestDto.destination().get());
                isWhiteTurn = !isWhiteTurn;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            printStatus(board);
            requestDto = inputView.inputGameCommand();
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
        System.out.println();
    }

}
