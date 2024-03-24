package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printStartGame();
        GameCommand command = inputView.readCommand();
        if (command == GameCommand.START) {
            Board board = BoardFactory.createInitialBoard();
            showBoard(board);
            play(board);
        }
        if (command == GameCommand.MOVE) {
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
    }

    private void play(Board board) {
        while (true) {
            processTurn(board);
        }
    }

    private void processTurn(Board board) {
        GameCommand command = inputView.readCommand();
        if (command == GameCommand.START) {
            throw new IllegalArgumentException("이미 게임을 시작했습니다.");
        }
        if (command == GameCommand.END) {
            System.exit(0);
        }
        executeMove(board);
    }

    private void executeMove(Board board) {
        Position start = inputView.readPosition();
        Position end = inputView.readPosition();
        board.tryMove(start, end);
        showBoard(board);
    }

    private void showBoard(Board board) {
        BoardDto boardDto = BoardDto.of(board);
        outputView.printBoard(boardDto);
    }
}
