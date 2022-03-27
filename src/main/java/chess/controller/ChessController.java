package chess.controller;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        final ChessGame chessGame = new ChessGame();

        play(chessGame);
    }

    public void play(ChessGame chessGame) {
        try {
            final String inputCommand = InputView.inputCommend();
            final Command command = Command.from(inputCommand);
            if (command == Command.END) {
                return;
            }
            executeCommand(chessGame, inputCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            runStatusCommand(chessGame);
        }
    }

    private void startCommand(ChessGame chessGame) {
        try {
            validateGameStart(chessGame);
            chessGame.start();
            OutputView.printChessBoard(chessGame.getBoard());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            String[] position = inputCommand.substring(5).split(" ");
            chessGame.play(
                    Position.from(position[0]),
                    Position.from(position[1]));
            OutputView.printChessBoard(chessGame.getBoard());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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

    private void runStatusCommand(ChessGame chessGame) {
        try {
            validateGameEnd(chessGame);
            OutputView.printStatus(chessGame.getStatus());
            play(chessGame);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    private void validateGameEnd(ChessGame chessGame) {
        if (chessGame.isPlaying() || chessGame.isReady()) {
            throw new IllegalArgumentException("게임이 아직 종료되지 않았습니다.");
        }
    }
}
