package techcourse.fp.chess.controller;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.ChessGameService;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.dto.request.CommandRequest;
import techcourse.fp.chess.dto.response.BoardResponse;
import techcourse.fp.chess.dto.response.ScoreResponse;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, PlayingCommandRunner> playingCommandMapper = new EnumMap<>(Command.class);
    private final Map<Command, StartCommandRunner> startCommandMapper = new EnumMap<>(Command.class);
    private final ChessGameService chessGameService;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;

        initStartCommandMapper();
        initPlayingCommandMapper();
    }

    private void initStartCommandMapper() {
        startCommandMapper.put(Command.START, this::createNewGame);
        startCommandMapper.put(Command.LOAD, this::loadGame);
        startCommandMapper.put(Command.END, StartCommandRunner.end);
    }

    private void initPlayingCommandMapper() {
        playingCommandMapper.put(Command.MOVE, this::move);
        playingCommandMapper.put(Command.STATUS, this::checkStatus);
        playingCommandMapper.put(Command.SAVE, this::save);
        playingCommandMapper.put(Command.END, PlayingCommandRunner.end);
    }

    public void run() {
        outputView.printInitialMessage();

        Command command = getInitialCommand();

        final StartCommandRunner initCommandRunner = startCommandMapper.get(command);
        final Board board = initCommandRunner.execute();

        while (command != Command.END) {
            command = play(board);
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

    private Board createNewGame() {
        final Board board = BoardFactory.generate();
        outputView.printBoard(BoardResponse.create(board.getBoard()));
        return board;
    }

    private Board loadGame() {
        try {
            outputView.printGameInfos(chessGameService.findInfos());

            final String id = inputView.readInitCommand();
            final Board board = chessGameService.findById(Integer.parseInt(id));
            outputView.printBoard(BoardResponse.create(board.getBoard()));
            return board;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return loadGame();
        }
    }


    private Command play(final Board board) {
        try {
            Command command = excuteCommand(board);

            if (board.isGameEnd()) {
                outputView.printWinningMessage(board.findWinner());
                return Command.END;
            }

            return command;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return Command.EMPTY;
        }
    }

    private Command excuteCommand(final Board board) {
        final CommandRequest commandRequest = inputView.readInPlayCommand();
        Command command = Command.createInPlayCommand(commandRequest.getMessage());

        final PlayingCommandRunner commandRunner = playingCommandMapper.get(command);
        commandRunner.execute(commandRequest, board);

        return command;
    }

    private void move(final CommandRequest commandRequest, final Board board) {
        board.move(commandRequest.getSource(), commandRequest.getTarget());
        outputView.printBoard(BoardResponse.create(board.getBoard()));
    }

    private void save(final CommandRequest commandRequest, final Board board) {
        final String gameName = inputView.readSaveGameName();
        chessGameService.save(board, gameName);
        outputView.printSaveSuccessMessage();
    }


    private void checkStatus(final CommandRequest commandRequest, final Board board) {
        List<ScoreResponse> scores = new ArrayList<>();
        addScoreByColor(Color.WHITE, scores, board);
        addScoreByColor(Color.BLACK, scores, board);

        outputView.printStatus(scores);
    }

    private void addScoreByColor(final Color color, final List<ScoreResponse> result, Board board) {
        final double score = board.findScoreByColor(color);
        result.add(ScoreResponse.of(color, score));
    }
}
