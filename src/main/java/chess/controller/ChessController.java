package chess.controller;

import chess.controller.command.Command;
import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.ChessRunner;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Positions;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Optional;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();
    private ChessRunner chessRunner;

    public void run() {
        boolean runFlag = false;
        if (inputView.askStartCommand().isStart()) {
            startGame();
            runFlag = true;
        }
        while (runFlag) {
            List<String> inputCommand = inputView.askContinueCommand().get();
            Command command = Command.of(inputCommand.get(0));

            switch (command) {
                case MOVE:
                    update(inputCommand.get(1), inputCommand.get(2));
                    runFlag = stopGameIfWinnerExists(chessRunner);
                    break;
                case STATUS:
                    outputView.printStatus(chessRunner.calculateScore(), chessRunner.getCurrentTeam());
                    break;
                case END:
                    runFlag = false;
            }
        }
    }

    private void startGame() {
        chessRunner = new ChessRunner();
        printBoard(chessRunner.getBoard());
    }

    private void update(String sourcePosition, String targetPosition) {
        chessRunner.update(sourcePosition, targetPosition);
        printBoard(chessRunner.getBoard());
    }

    private boolean stopGameIfWinnerExists(ChessRunner chessRunner) {
        Optional<Team> winner = chessRunner.findWinner();

        if (winner.isPresent()) {
            outputView.printWinner(winner.get());
            return false;
        }
        return true;
    }

    private void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board.get());
        PositionDto positionDto = new PositionDto(Positions.get());
        outputView.printBoard(positionDto.get(), boardDto.get());
    }
}
