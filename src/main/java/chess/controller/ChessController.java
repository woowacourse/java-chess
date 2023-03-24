package chess.controller;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.ChessGame;
import chess.domain.Game;
import chess.domain.User;
import chess.domain.database.ChessGameDao;
import chess.domain.piece.Team;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.SquareDto;

public class ChessController {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    public void run() {
        User user = null;
        while (user == null) {
            OutputView.printLoginMessage();
            Command loginCommand = readLoginCommand();
            if (loginCommand == Command.LOGIN) {
                user = login();
            }
            if (loginCommand == Command.REGISTER) {
                try {
                    user = register();
                } catch (IllegalArgumentException e) {
                    OutputView.printErrorMessage(e.getMessage());
                }
            }
        }
        OutputView.printStartMessage();
        OutputView.printCommandMessage();
        Command initialCommand = readInitialCommand();
        if (initialCommand == Command.END) {
            return;
        }
        if (initialCommand == Command.START) {
            OutputView.printPlayNewGameMessage();
            Command playNewGameCommand = readPlayNewGameCommand();
            if (playNewGameCommand == Command.NEW) {
                playNewGame(user.getId());
                return;
            }
            if (playNewGameCommand == Command.EXIST) {
                List<Game> games = chessGameDao.getGamesById(user.getId());
                if (games.isEmpty()) {
                    playNewGame(user.getId());
                    return;
                }
                playerExistGame(games);
            }
        }
    }

    private void playerExistGame(List<Game> games) {
        OutputView.printGames(games);
        List<String> gameIds = games.stream()
                .map(Game::getGameId)
                .collect(Collectors.toList());
        String gameId;
        while (true) {
            gameId = InputView.readNext();
            if (gameIds.contains(gameId)) {
                break;
            }
        }
        int lastTurn = chessGameDao.getLastTurnById(gameId);
        ChessGame chessGame = chessGameDao.getGameById(gameId, lastTurn);
        playGame(gameId, chessGame);
    }

    private Command readPlayNewGameCommand() {
        Command command = InputView.readCommand();
        try {
            validatePlayNewGameCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readPlayNewGameCommand();
        }
    }

    private void validatePlayNewGameCommand(Command command) {
        if (command == Command.NEW || command == Command.EXIST) {
            return;
        }
        throw new IllegalArgumentException("새로운 게임을 시작하려면 new, 이미 존재하는 게임을 확인하려면 exist를 입력해주세요.");
    }

    private void playNewGame(String userId) {
        ChessGame chessGame = new ChessGame();
        chessGameDao.createGame(userId);
        String lastGameId = chessGameDao.getLastGameId(userId);
        playGame(lastGameId, chessGame);
    }

    private User login() {
        OutputView.printIdMessage();
        String id = InputView.readNext();
        try {
            return chessGameDao.getUserById(id);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return null;
    }

    private User register() {
        OutputView.printIdInputMessage();
        String id = InputView.readNext();
        try {
            chessGameDao.getUserById(id);
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        } catch (IllegalArgumentException e) {
            OutputView.printNicknameInputMessage();
            String nickname = InputView.readNext();
            chessGameDao.addUser(new User(id, nickname));
            return chessGameDao.getUserById(id);
        }
    }

    private Command readLoginCommand() {
        Command command = InputView.readCommand();
        try {
            validateLoginCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readLoginCommand();
        }
    }

    private void validateLoginCommand(Command command) {
        if (command == Command.LOGIN || command == Command.REGISTER) {
            return;
        }
        throw new IllegalArgumentException("아이디가 있으시다면 login, 아이디가 없으시다면 register를 입력해주세요.");
    }

    private Command readInitialCommand() {
        Command command = InputView.readCommand();
        try {
            validateInitialCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readInitialCommand();
        }
    }

    private void validateInitialCommand(final Command command) {
        if (command == Command.MOVE) {
            throw new IllegalArgumentException("게임 시작 전에는 기물을 이동할 수 없습니다.");
        }
        if (command == Command.STATUS) {
            throw new IllegalArgumentException("게임 시작 전에는 승자를 확인할 수 없습니다.");
        }
    }

    private void playGame(String gameId, ChessGame chessGame) {
        OutputView.printGameStatus(chessGame.getGameStatus());
        while (true) {
            Command command = readPlayCommand();
            if (command == Command.END) {
                break;
            }
            if (command == Command.MOVE) {
                SquareDto current = readSquare();
                SquareDto destination = readSquare();
                move(gameId, chessGame, current, destination);
                if (chessGame.isKingDead()) {
                    break;
                }
                continue;
            }
            if (command == Command.STATUS) {
                try {
                    Team winner = chessGame.getWinner();
                    OutputView.printWinner(winner);
                } catch (IllegalStateException e) {
                    OutputView.printWhitePoint(chessGame.getPoint(Team.WHITE));
                    OutputView.printBlackPoint(chessGame.getPoint(Team.BLACK));
                }
            }
        }
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
        if (command == Command.START) {
            throw new IllegalArgumentException("이미 게임을 시작하셨습니다.");
        }
    }

    private SquareDto readSquare() {
        try {
            return InputView.readSquare();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    private void move(final String gameId, final ChessGame chessGame, final SquareDto currentDto, final SquareDto destinationDto) {
        if (currentDto == null || destinationDto == null) {
            return;
        }
        try {
            Square current = Square.of(File.from(currentDto.getFile()), Rank.from(currentDto.getRank()));
            Square destination = Square.of(File.from(destinationDto.getFile()), Rank.from(destinationDto.getRank()));
            chessGame.move(current, destination);
            OutputView.printGameStatus(chessGame.getGameStatus());
            chessGameDao.save(gameId, chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
