package chess.controller;

import chess.dto.BoardDTO;
import chess.model.board.Board;
import chess.model.board.InitialBoardGenerator;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final OutputView outputView;
    private final InputView inputView;

    public ChessGame(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printGameIntro();
        Command command = inputView.askCommand();
        if (command == Command.START) {
            Board board = new InitialBoardGenerator().create();
            BoardDTO boardDTO = new BoardDTO(board);
            outputView.printBoard(boardDTO);
        }
    }
}
