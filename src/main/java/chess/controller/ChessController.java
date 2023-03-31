package chess.controller;

import chess.domain.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import chess.domain.game.User;
import chess.domain.piece.Team;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        LoginController loginController = new LoginController();
        User user = loginController.getUser();
        Command initialCommand = readInitialCommand(user.getNickname());
        initializeByCommand(initialCommand, user);
    }

    private Command readInitialCommand(String nickname) {
        OutputView.printStartMessage(nickname);
        OutputView.printInitialCommandMessage();
        Command command = InputView.readCommand();
        while (isNotInitialCommand(command)) {
            printInitialCommandErrorMessage(command);
            command = InputView.readCommand();
        }
        return command;
    }

    private boolean isNotInitialCommand(Command command) {
        return !(command == Command.START || command == Command.REPLAY || command == Command.END);
    }

    private void printInitialCommandErrorMessage(Command command) {
        String initialErrorMessage = command.getInitialErrorMessage();
        OutputView.printErrorMessage(initialErrorMessage);
    }

    private void initializeByCommand(Command command, User user) {
        if (command == Command.END) {
            return;
        }
        if (command == Command.REPLAY) {
            replay(user);
            return;
        }
        startGame(user);
    }

    private void replay(User user) {
        ReplayController replayController = new ReplayController();
        replayController.replayGame(user);
    }

    private void startGame(User user) {
        OutputView.printPlayNewGameMessage();
        Command newGameCommand = readNewGameCommand();
        LoadGameController loadGameController = new LoadGameController();
        ChessGameDto chessGameDto = loadGameController.getChessGameDtoByCommand(user, newGameCommand);
        playGameWhenBothKingAlive(chessGameDto);
    }

    private Command readNewGameCommand() {
        Command command = InputView.readCommand();
        try {
            validateNewGameCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readNewGameCommand();
        }
    }

    private void validateNewGameCommand(Command command) {
        if (command == Command.NEW || command == Command.EXIST) {
            return;
        }
        throw new IllegalArgumentException("새로운 게임을 시작하려면 new, 이미 존재하는 게임을 확인하려면 exist를 입력해주세요.");
    }

    private void playGameWhenBothKingAlive(ChessGameDto chessGameDto) {
        String gameId = chessGameDto.getId();
        ChessGame chessGame = chessGameDto.getChessGame();
        if (chessGame.isKingDead()) {
            printWinner(chessGame);
            return;
        }
        PlayController playController = new PlayController();
        playController.playGame(gameId, chessGame);
    }

    private void printWinner(ChessGame chessGame) {
        Team winner = chessGame.getWinner();
        OutputView.printWinner(winner);
    }
}
