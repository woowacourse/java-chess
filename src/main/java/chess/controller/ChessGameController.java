package chess.controller;

import chess.domain.Board;
import chess.domain.InitialPiecePosition;
import chess.domain.Position;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private Board initializeBoard() {
        Board board = new Board(new InitialPiecePosition());
        outputView.printBoard(board);
        return board;
    }

    public void start() {
        outputView.printInitialMessage();

        GameCommand gameCommand = inputView.getGameCommand();
        if (gameCommand != GameCommand.START) {
            throw new IllegalArgumentException("시작 명령어를 입력해주세요.");
        }

        Board board = initializeBoard();
        while (gameCommand != GameCommand.END) {
            gameCommand = inputView.getGameCommand();
            run(board, gameCommand);
        }
    }

    private void run(Board board, GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            board = initializeBoard();
        }

        if (gameCommand == GameCommand.MOVE) {
            Position from = PositionConverter.convert(inputView.getPosition());
            Position to = PositionConverter.convert(inputView.getPosition());
            board.move(from, to);
            outputView.printBoard(board);
        }
    }
}
