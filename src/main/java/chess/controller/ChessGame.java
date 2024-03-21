package chess.controller;

import chess.dto.BoardDTO;
import chess.dto.PositionDTO;
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
            start();
        }
    }

    private void start() {
        Board board = new InitialBoardGenerator().create();
        GameStatus gameStatus = new GameStatus();
        while (gameStatus.isRunning()) {
            play(board);
            control(gameStatus);
            move(board);
        }
    }

    private void play(Board board) {
        BoardDTO boardDTO = new BoardDTO(board);
        outputView.printBoard(boardDTO);
    }

    private void move(Board board) {
        PositionDTO sourcePositionDTO = inputView.askPosition();
        PositionDTO targetPositionDTO = inputView.askPosition();
        board.move(sourcePositionDTO.toEntity(), targetPositionDTO.toEntity());
    }

    private void control(GameStatus gameStatus) {
        Command command = inputView.askCommand();
        if (command == Command.START) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
        if (command == Command.END) {
            gameStatus.stop();
        }
    }
}
