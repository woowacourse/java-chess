package chess.controller;

import static chess.controller.Command.MOVE;
import static chess.controller.Command.STATUS;
import static chess.controller.Command.of;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void play() {
        OutputView.printStartMessage();

        List<String> input = InputView.requestCommand();
        Command command = of(input.get(InputView.COMMAND_INDEX));

        if (command.isStart()) {
            startGame();
        }
    }

    private void startGame() {
        ChessGame chessGame = new ChessGame();
        OutputView.printChessBoard(chessGame.getBoardStatus());

        List<String> input = InputView.requestCommand();
        Command command = of(input.get(InputView.COMMAND_INDEX));

        playChessGame(input, command, chessGame);
    }

    private void playChessGame(List<String> input, Command command, ChessGame chessGame) {
        while (!runByCommand(input, command, chessGame)) {
            OutputView.printChessBoard(chessGame.getBoardStatus());
            input = InputView.requestCommand();
            command = of(input.get(InputView.COMMAND_INDEX));
        }
        OutputView.printFinishedGame(chessGame.getBoardStatus(), chessGame.getWinnerTeam());
    }

    private boolean runByCommand(List<String> input, Command command, ChessGame chessGame) {
        if (command.isStatus()) {
            OutputView.printStatus(chessGame.getWhiteTeamScore(), chessGame.getBlackTeamScore());
            return false;
        }
        if (command.isMove()) {
            return chessGame.move(
                    input.get(InputView.FIRST_POSITION_INDEX), input.get(InputView.SECOND_POSITION_INDEX));
        }
        return false;
    }
}
