package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class InitialGame implements GameStatus {
    private final InputView inputView;

    public InitialGame(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play() {
        GameCommand command = inputView.getGameCommand();
        if (command == GameCommand.START) {
            Board board = new Board(new BoardInitializer());
            OutputView.printBoard(board);
            return new MainGame(inputView, board);
        }
        
        if (command == GameCommand.END) {
            return new TerminateGame();
        }
        throw new IllegalArgumentException("보드를 초기화 하지 않았습니다. start 명령어로 시작 할 수 있습니다.");
    }
}
