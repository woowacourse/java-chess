package chess.controller;

import chess.dto.BoardDTO;
import chess.dto.PositionDTO;
import chess.model.board.Board;
import chess.model.board.InitialBoardGenerator;
import chess.model.piece.Color;
import chess.model.position.Movement;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChessGame {
    private static final Color START_COLOR = Color.WHITE;

    private final OutputView outputView;
    private final InputView inputView;

    public ChessGame(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printGameIntro();
        while (getValidCommand() != Command.START) {
            outputView.printException("게임을 시작하려면 start를 입력하세요.");
        }
        start();
    }

    private void start() {
        Board board = new InitialBoardGenerator().create();
        GameStatus gameStatus = new GameStatus();
        showBoard(board);
        Color currnetColor = START_COLOR;
        while (gameStatus.isRunning()) {
            retryOnException((color) -> playTurn(gameStatus, board, color), currnetColor);
            currnetColor = currnetColor.getOpposite();
        }
    }

    private void showBoard(Board board) {
        BoardDTO boardDTO = new BoardDTO(board);
        outputView.printBoard(boardDTO);
    }

    private void playTurn(GameStatus gameStatus, Board board, Color color) {
        Command command = inputView.askCommand();
        if (command == Command.START) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
        if (command == Command.END) {
            gameStatus.stop();
            return;
        }
        move(board, color);
        showBoard(board);
    }

    private void move(Board board, Color color) {
        PositionDTO sourcePositionDTO = inputView.askPosition();
        PositionDTO targetPositionDTO = inputView.askPosition();
        Movement movement = new Movement(sourcePositionDTO.toEntity(), targetPositionDTO.toEntity());
        board.move(movement, color);
    }

    private Command getValidCommand() {
        return retryOnException(inputView::askCommand);
    }

    private <T> void retryOnException(Consumer<T> action, T value) {
        try {
            action.accept(value);
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            retryOnException(action, value);
        }
    }

    private <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
