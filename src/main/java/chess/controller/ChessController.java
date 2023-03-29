package chess.controller;

import chess.Service.ChessService;
import chess.domain.ChessGame;
import chess.dto.MoveDto;
import chess.view.ChessCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private final Map<ChessCommand, GameAction> commandMapper = new EnumMap<>(ChessCommand.class);
    private final ChessService chessService;

    public ChessController(final ChessService chessService) {
        this.chessService = chessService;
        initController();
    }

    private void initController() {
        commandMapper.put(ChessCommand.START, this::start);
        commandMapper.put(ChessCommand.MOVE, this::move);
        commandMapper.put(ChessCommand.STATUS, this::status);
        commandMapper.put(ChessCommand.END, (ignored) -> {
        });
    }

    public void run() {
        OutputView.printStartPrefix();

        chessService.checkNotation();
        ChessCommand gameCommand = ChessCommand.WAIT;
        while (chessService.isKingLive() && gameCommand != ChessCommand.END) {
            gameCommand = play();
        }
        chessService.checkDeleteData();
        OutputView.printWinner(chessService.getChessGame());
    }

    private ChessCommand play() {
        try {
            List<String> commands = InputView.readCommand();
            ChessCommand command = ChessCommand.from(commands.get(COMMAND_INDEX));
            GameAction gameAction = commandMapper.get(command);
            gameAction.execute(commands);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return ChessCommand.WAIT;
        }
    }

    private void start(final List<String> commands) {
        ChessCommand.validateStartCommand(commands);
        printBoard(chessService.getChessGame());
    }

    private void move(final List<String> commands) {
        ChessCommand.validatePlayingCommand(commands);
        MoveDto moveDto = MoveDto.of(commands.get(FROM_POSITION_INDEX), commands.get(TO_POSITION_INDEX));

        chessService.move(moveDto);

        printBoard(chessService.getChessGame());
    }

    private void status(final List<String> commands) {
        ChessCommand.validateStatusCommand(commands);
        OutputView.printStatusScore(chessService.getChessGame());
    }

    private void printBoard(final ChessGame chessGame) {
        OutputView.printChessState(chessGame.getChessBoard().getBoard());
    }
}
