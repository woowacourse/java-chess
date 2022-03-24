package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    public void run() {
        OutputView.printStartGame();
        List<String> inputCommand = InputView.inputCommand();
        Command firstCommand = Command.of(inputCommand.get(0));
        if (firstCommand == Command.MOVE) {
            throw new IllegalStateException("[ERROR] 게임이 아직 시작되지 않았습니다.");
        }

        if (firstCommand == Command.START) {
            ChessGame chessGame = new ChessGame(Board.create());
            OutputView.printBoard(chessGame.getBoard());

            boolean flag = true;
            while (flag) {
                List<String> moveCommand = InputView.inputCommand();
                if (Command.of(moveCommand.get(0)) == Command.MOVE) {
                    chessGame.move(moveCommand.get(1), moveCommand.get(2));
                    OutputView.printBoard(chessGame.getBoard());
                }
                if (Command.of(moveCommand.get(0)) == Command.START) {
                    throw new IllegalStateException("[ERROR] 게임은 이미 시작되었습니다.");
                }
                if (Command.of(moveCommand.get(0)) == Command.END) {
                    flag = false;
                }
            }
        }
    }
}
