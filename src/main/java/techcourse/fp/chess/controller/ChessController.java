package techcourse.fp.chess.controller;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.dao.ChessGameDao;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.dto.request.ChessGameRequest;
import techcourse.fp.chess.dto.request.CommandRequest;
import techcourse.fp.chess.dto.response.BoardResponse;
import techcourse.fp.chess.dto.response.ScoreResponse;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, CommandRunner> commandMapper = new EnumMap<>(Command.class);
    private final Map<Command, InitCommandRunner> initCommandMapper = new EnumMap<>(Command.class);
    private final ChessGameDao chessGameDao;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGameDao chessGameDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameDao = chessGameDao;

        initCommandMapper.put(Command.START, this::createNewGame);
        initCommandMapper.put(Command.LOAD, this::loadGame);
        initCommandMapper.put(Command.END, InitCommandRunner.end);

        commandMapper.put(Command.MOVE, this::move);
        commandMapper.put(Command.STATUS, this::checkStatus);
        commandMapper.put(Command.SAVE, this::save);
        commandMapper.put(Command.END, CommandRunner.end);
    }

    public void run() {
        outputView.printInitialMessage();

        Command command = getInitialCommand();

        final InitCommandRunner initCommandRunner = initCommandMapper.get(command);
        final ChessGame chessGame = initCommandRunner.execute();

        while (command != Command.END) {
            command = play(chessGame);
        }

        outputView.printEndMessage();
    }

    private Command getInitialCommand() {
        try {
            return Command.createInitCommand(inputView.readInitCommand());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getInitialCommand();
        }
    }

    private ChessGame createNewGame() {
        final ChessGame chessGame = new ChessGame(BoardFactory.generate());
        outputView.printBoard(BoardResponse.create(chessGame.getBoard()));
        return chessGame;
    }

    private ChessGame loadGame() {
        try {
            outputView.printGameInfos(chessGameDao.findInfo());

            final String id = inputView.readInitCommand();
            final ChessGame chessGame = chessGameDao.findById(Integer.parseInt(id));
            outputView.printBoard(BoardResponse.create(chessGame.getBoard()));
            return chessGame;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return loadGame();
        }
    }


    private Command play(final ChessGame chessGame) {
        try {
            Command command = excuteCommand(chessGame);

            if (chessGame.isGameEnd()) {
                outputView.printWinningMessage(chessGame.findWinner());
                return Command.END;
            }

            return command;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return Command.EMPTY;
        }
    }

    private Command excuteCommand(final ChessGame chessGame) {
        final CommandRequest commandRequest = inputView.readInPlayCommand();
        Command command = Command.createInPlayCommand(commandRequest.getMessage());

        final CommandRunner commandRunner = commandMapper.get(command);
        commandRunner.execute(commandRequest, chessGame);

        return command;
    }

    private void move(final CommandRequest commandRequest, ChessGame chessGame) {
        try {
            chessGame.move(commandRequest.getSource(), commandRequest.getTarget());
            outputView.printBoard(BoardResponse.create(chessGame.getBoard()));
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
        }
    }

    private void save(final CommandRequest commandRequest, final ChessGame chessGame) {
        final String gameName = inputView.readSaveGameName();
        chessGameDao.save(ChessGameRequest.create(chessGame, gameName));
        outputView.printSaveSuccessMessage();
    }


    private void checkStatus(final CommandRequest commandRequest, final ChessGame chessGame) {
        List<ScoreResponse> scores = new ArrayList<>();
        addScoreByColor(Color.WHITE, scores, chessGame);
        addScoreByColor(Color.BLACK, scores, chessGame);

        outputView.printStatus(scores);
    }

    private void addScoreByColor(final Color color, final List<ScoreResponse> result, ChessGame chessGame) {
        final double score = chessGame.findScoreByColor(color);
        result.add(ScoreResponse.of(color, score));
    }
}
