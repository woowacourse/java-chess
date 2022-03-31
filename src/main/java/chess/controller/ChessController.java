package chess.controller;


import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final String COMMAND_SPLIT = " ";
    private static final int POSITION_FROM_INDEX = 0;
    private static final int POSITION_TO_INDEX = 1;
    private static final int COMMAND_LENGTH = 5;

    public void run() {
        OutputView.printInitMessage();
        final ChessGame chessGame = new ChessGame();

        play(chessGame);
    }

    public void play(ChessGame chessGame) {
        if (chessGame.isEnd()) {
            executeEnd(chessGame);
        }
        try {
            final String inputCommand = InputView.inputCommend();
            final Command command = Command.from(inputCommand);
            if (command == Command.END) {
                return;
            }
            executeCommand(chessGame, inputCommand);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void executeCommand(ChessGame chessGame, String inputCommand) {
        final Command command = Command.from(inputCommand);

        if (command == Command.START) {
            startCommand(chessGame);
        }
        if (command == Command.MOVE) {
            runCommand(chessGame, inputCommand);
        }
        if (command == Command.STATUS) {
            statusCommand(chessGame);
        }
    }

    private void startCommand(ChessGame chessGame) {
        try {
            validateGameStart(chessGame);
            chessGame.start();
            OutputView.printChessBoard(chessGame.getBoard());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void validateGameStart(ChessGame chessGame) {
        if (chessGame.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 시작 상태입니다.");
        }
        if (chessGame.isEnd()) {
            throw new IllegalArgumentException("게임이 종료되었습니다.");
        }
    }

    private void runCommand(ChessGame chessGame, String inputCommand) {
        try {
            validateGamePlaying(chessGame);
            String[] position = inputCommand.substring(COMMAND_LENGTH).split(COMMAND_SPLIT);
            chessGame.play(
                    Position.from(position[POSITION_FROM_INDEX]),
                    Position.from(position[POSITION_TO_INDEX]));
            OutputView.printChessBoard(chessGame.getBoard());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void validateGamePlaying(ChessGame chessGame) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("게임이 아직 시작되지 않았습니다.");
        }
        if (chessGame.isEnd()) {
            throw new IllegalArgumentException("게임이 종료되었습니다.");
        }
    }

    private void statusCommand(ChessGame chessGame) {
        try {
            validateGameStatus(chessGame);
            OutputView.printStatus(chessGame.getStatus());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void validateGameStatus(ChessGame chessGame) {
        if (chessGame.isEnd() || chessGame.isReady()) {
            throw new IllegalArgumentException("게임이 아직 종료되지 않았습니다.");
        }
    }

    private void executeEnd(ChessGame chessGame) {
        OutputView.printGameResult(chessGame.getWinner());
    }
}
