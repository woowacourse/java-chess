package chess.contoller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    public void play() {
        ChessGame chessGame = new ChessGame(new Board());

        ready(chessGame);
        start(chessGame);
    }

    private void ready(ChessGame chessGame) {
        OutputView.printStartInfo();
        List<String> input = InputView.InputString();
        validateStarCommant(chessGame, input);
    }

    private void validateStarCommant(ChessGame chessGame, List<String> input) {
        try {
            Command command = Command.getByInput(input.get(0));
            validateStartCommand(command);
            command.execute(chessGame, input);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            ready(chessGame);
        }
    }

    private void validateStartCommand(Command command) {
        List<Command> possibleCommand = Arrays.asList(Command.START, Command.END);
        if (!possibleCommand.contains(command)) {
            throw new IllegalArgumentException("start, end 이외의 명령은 입력할 수 없습니다.");
        }
    }

    private void start(ChessGame chessGame) {
        while (chessGame.isRunning()) {
            OutputView.printChessBoard(chessGame.generateBoardDto());
            List<String> input = InputView.InputString();
            validateCommand(chessGame, input);
        }
        OutputView.printGameOverInfo();
    }

    private void validateCommand(ChessGame chessGame, List<String> input) {
        try {
            Command command = Command.getByInput(input.get(0));
            command.execute(chessGame, input);
            printScoreIfStatus(chessGame, command);
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            start(chessGame);
        }
    }

    private void printScoreIfStatus(ChessGame chessGame, Command command) {
        if (command == Command.STATUS) {
            OutputView.printTeamScore(chessGame.score(Team.WHITE), Team.WHITE);
            OutputView.printTeamScore(chessGame.score(Team.BLACK), Team.BLACK);
        }
    }
}
