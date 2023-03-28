package chess.controller;

import chess.domain.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import chess.domain.game.User;
import chess.domain.piece.Team;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.SquareDto;

public class ChessController {

    private final LoadGameController loadGameController = new LoadGameController();
    private final ReplayController replayController = new ReplayController();

    public void run() {
        LoginController loginController = new LoginController();
        User user = loginController.getUser();
        Command initialCommand = readInitialCommand();
        if (initialCommand == Command.END) {
            return;
        }
        if (initialCommand == Command.REPLAY) {
            replayController.replayGame(user);
            return;
        }
        if (initialCommand == Command.START) {
            startGame(user);
        }
    }

    private Command readInitialCommand() {
        OutputView.printStartMessage();
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

    private void startGame(User user) {
        OutputView.printPlayNewGameMessage();
        Command newGameCommand = readNewGameCommand();
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
        playGame(gameId, chessGame);
    }

    private void printWinner(ChessGame chessGame) {
        Team winner = chessGame.getWinner();
        OutputView.printWinner(winner);
    }

    private void playGame(String gameId, ChessGame chessGame) {
        OutputView.printGameCommandMessage();
        OutputView.printGameStatus(chessGame.getGameStatus());
        Command command;
        do {
            command = readPlayCommand();
            playGameByCommand(gameId, chessGame, command);
        } while (chessGame.isBothKingAlive() && command != Command.END);
        printWinnerWhenKingIsDead(chessGame);
    }

    private Command readPlayCommand() {
        Command command = InputView.readCommand();
        try {
            validatePlayCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readPlayCommand();
        }
    }

    private void validatePlayCommand(final Command command) {
        if (command == Command.MOVE || command == Command.STATUS || command == Command.END) {
            return;
        }
        throw new IllegalArgumentException("기물을 움직이려면 move, 게임 점수를 보려면 status, 게임을 종료하려면 end를 입력해주세요.");
    }

    private void playGameByCommand(String gameId, ChessGame chessGame, Command command) {
        if (command == Command.END) {
            return;
        }
        if (command == Command.MOVE) {
            move(gameId, chessGame);
            return;
        }
        printPoint(chessGame);
    }

    private void printPoint(ChessGame chessGame) {
        OutputView.printWhitePoint(chessGame.getPoint(Team.WHITE));
        OutputView.printBlackPoint(chessGame.getPoint(Team.BLACK));
    }

    private void printWinnerWhenKingIsDead(ChessGame chessGame) {
        if (chessGame.isKingDead()) {
            printWinner(chessGame);
        }
    }

    private void move(final String gameId, final ChessGame chessGame) {
        try {
            SquareDto currentDto = InputView.readSquare();
            SquareDto destinationDto = InputView.readSquare();
            saveGameWhenMoveSuccess(gameId, chessGame, currentDto, destinationDto);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void saveGameWhenMoveSuccess(
            final String gameId,
            final ChessGame chessGame,
            final SquareDto currentDto,
            final SquareDto destinationDto
    ) {
        try {
            movePiece(chessGame, currentDto, destinationDto);
            OutputView.printGameStatus(chessGame.getGameStatus());
            chessGame.save(gameId);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void movePiece(final ChessGame chessGame, final SquareDto currentDto, final SquareDto destinationDto) {
        Square current = Square.of(File.from(currentDto.getFile()), Rank.from(currentDto.getRank()));
        Square destination = Square.of(File.from(destinationDto.getFile()), Rank.from(destinationDto.getRank()));
        chessGame.move(current, destination);
    }
}
