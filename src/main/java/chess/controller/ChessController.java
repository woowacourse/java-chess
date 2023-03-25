package chess.controller;

import chess.Service.ChessService;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.view.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Map<ChessCommand, GameAction> commandMapper = new EnumMap<>(ChessCommand.class);
    private final ChessService chessService;
    private ChessGame game;

    public ChessController(final ChessGame game, final ChessService chessService) {
        this.game = game;
        this.chessService = chessService;
        initController();
    }

    private void initController() {
        commandMapper.put(ChessCommand.START, this::start);
        commandMapper.put(ChessCommand.MOVE, this::move);
        commandMapper.put(ChessCommand.STATUS, this::status);
        commandMapper.put(ChessCommand.END, (ignored1, ignored2) -> {
        });
    }

    public void run() {
        OutputView.printStartPrefix();
        game = chessService.checkNotation(game);

        ChessCommand gameCommand = ChessCommand.WAIT;
        while (game.isKingsLive() && gameCommand != ChessCommand.END) {
            gameCommand = play(game);
        }
        chessService.deleteData(game.isKingsLive());
        OutputView.printWinner(game.getWinnerCamp());
    }

    private ChessCommand play(final ChessGame game) {
        try {
            List<String> commands = InputView.readCommand();
            ChessCommand command = ChessCommand.from(commands.get(COMMAND_INDEX));
            GameAction gameAction = commandMapper.get(command);
            gameAction.execute(commands, game);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return ChessCommand.WAIT;
        }
    }

    private void start(final List<String> commands, final ChessGame game) {

        ChessCommand.validateStartCommand(commands);
        printBoard(game.getChessBoard());
    }

    private void move(final List<String> commands, final ChessGame game) {
        ChessCommand.validatePlayingCommand(commands);
        String fromInput = commands.get(FROM_POSITION_INDEX);
        String toInput = commands.get(TO_POSITION_INDEX);

        game.move(toPosition(fromInput), toPosition(toInput));

        chessService.saveGame(toPosition(fromInput), toPosition(toInput));
        printBoard(game.getChessBoard());
    }

    private void status(final List<String> commands, final ChessGame game) {
        ChessCommand.validateStatusCommand(commands);
        OutputView.printStatusScore(game.getWhiteScore(), game.getBlackScore());
    }

    private Position toPosition(final String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    private void printBoard(final ChessBoard chessBoard) {
        OutputView.printChessState(chessBoard.getBoard());
    }
}
