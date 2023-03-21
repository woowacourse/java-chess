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
import chess.service.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private final ChessGame chessGame;
    private final Map<Command, ChessGameAction> commandMapper = new EnumMap<>(Command.class);

    public ChessGameController(final ChessGame chessGame) {
        this.chessGame = chessGame;
        commandMapper.put(START, ignore -> start());
        commandMapper.put(MOVE, this::move);
        commandMapper.put(STATUS, ignore -> status());
        commandMapper.put(CLEAR, ignore -> clear());
        commandMapper.put(END, ChessGameAction.EMPTY);
    }

    public void run() {
        OutputView.printGameStart();
        Command command = EMPTY;
        while (command != END) {
            command = play();
        }
        OutputView.printGameEnd();
    }

    private Command play() {
        try {
            final List<String> commands = InputView.readCommand();
            final Command command = Command.from(commands);
            command.validateCommandsSize(commands);
            final ChessGameAction chessGameAction = commandMapper.get(command);
            chessGameAction.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void start() {
        if (chessGame.isInitialized()) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다.");
        }
        chessGame.initialize();
        OutputView.printBoard(chessGame.getResult());
    }

    private void move(final List<String> commands) {
        if (!chessGame.isInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        final MoveDto moveDto = new MoveDto(commands.get(MOVE_SOURCE_INDEX), commands.get(MOVE_TARGET_INDEX));
        chessGame.move(moveDto);
        OutputView.printBoard(chessGame.getResult());
    }

    private void status() {
        if (!chessGame.isInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        OutputView.printStatus(chessGame.getResult());
    }

    private void clear() {
        if (!chessGame.isInitialized()) {
            throw new IllegalArgumentException("START를 입력해주세요.");
        }
        chessGame.clear();
        OutputView.printGameClear();
    }
}
