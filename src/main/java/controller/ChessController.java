package controller;

import domain.*;
import dto.ChessBoardDTO;
import view.InputView;
import view.Menu;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        outputView.printHeader();
        ChessBoard chessBoard = new ChessBoard();
        Menu prevMenu = Menu.DEFAULT;
        while (true) {
            try {
                final Menu menu = inputView.readMenu();

                if (menu.isStart()) {
                    if (prevMenu.isMove() || prevMenu.isStart()) {
                        outputView.printReplayMessage();
                    }
                    chessBoard = ChessBoard.create();
                    outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));
                }
                if (menu.isEnd()) {
                    break;
                }
                if (menu.isMove()) {
                    play(chessBoard);
                }

                prevMenu = menu;
            } catch (final Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void play(final ChessBoard chessBoard) {
        validateGameState(chessBoard);

        final Square source = readSquare();
        final Square target = readSquare();

        chessBoard.move(source, target);

        outputView.printChessBoard(ChessBoardDTO.from(chessBoard.getPieces()));
    }

    private Square readSquare() {
        final MoveCommand moveCommand = MoveCommand.fromInput(inputView.readMoveCommand());
        return new Square(File.from(moveCommand.file()), Rank.from(moveCommand.rank()));
    }

    private static void validateGameState(final ChessBoard chessBoard) {
        if (chessBoard.isEmpty()) {
            throw new IllegalStateException("게임을 시작해주세요.");
        }
    }
}
