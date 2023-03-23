package chess.controller;

import static chess.controller.Command.CLEAR;
import static chess.controller.Command.EMPTY;
import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.MOVE_SOURCE_INDEX;
import static chess.controller.Command.MOVE_TARGET_INDEX;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;

import chess.dto.MoveDto;
import chess.repository.ChessDaoGenerator;
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {

    private final Map<Command, ChessGameAction> commandMapper = new EnumMap<>(Command.class);

    public ChessGameController() {
        commandMapper.put(START, (chessGame, commands) -> start(chessGame));
        commandMapper.put(MOVE, this::move);
        commandMapper.put(STATUS, (chessGame, ignore) -> status(chessGame));
        commandMapper.put(CLEAR, (chessGame, ignore) -> clear(chessGame));
        commandMapper.put(END, ChessGameAction.EMPTY);
    }

    public void run() {
        OutputView.printGameStart();
        final ChessGame chessGame = new ChessGame(ChessDaoGenerator.getChessDao());
        Command command = EMPTY;
        while (command != END) {
            command = play(chessGame);
        }
        OutputView.printGameEnd();
    }

    private Command play(final ChessGame chessGame) {
        try {
            final List<String> commands = InputView.readCommand();
            final Command command = Command.from(commands);
            command.validateCommandsSize(commands);
            final ChessGameAction chessGameAction = commandMapper.get(command);
            chessGameAction.execute(chessGame, commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void start(final ChessGame chessGame) {
        if (chessGame.isInitialized()) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다.");
        }
        chessGame.initialize();
        OutputView.printBoard(chessGame.getResult());
    }

    private void move(final ChessGame chessGame, final List<String> commands) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        final MoveDto moveDto = new MoveDto(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
        chessGame.move(moveDto);
        OutputView.printBoard(chessGame.getResult());
    }

    private void status(final ChessGame chessGame) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        OutputView.printStatus(chessGame.getResult());
    }

    private void clear(final ChessGame chessGame) {
        if (chessGame.isNotInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        chessGame.clear();
        OutputView.printGameClear();
    }
}
