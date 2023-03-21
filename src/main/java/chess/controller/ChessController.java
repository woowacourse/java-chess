package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static chess.domain.game.Command.END;
import static chess.domain.game.Command.MOVE;
import static chess.domain.game.Command.START;
import static chess.domain.game.Command.from;
import static chess.domain.game.Command.validateCommandSize;

public class ChessController {

    private static final int MOVE_FROM_INDEX = 1;
    private static final int MOVE_TO_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    private final ChessGame chessGame;
    private final Map<Command, GameAction> commandMapper = Map.of(
            START, this::start,
            MOVE, this::move,
            END, this::end
    );

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printInitGame();
        processGame();
    }

    private void processGame() {
        final Command firstCommand = readValidateInput(this::readCommand);
        chessGame.receiveCommand(firstCommand);

        Command command = END;
        while (!chessGame.isEnd()) {
            renderChessBoard();
            command = readValidateInput(this::readCommand);
        }
    }

    private void renderChessBoard() {
        final ChessBoard chessBoard = chessGame.getChessBoard();
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessBoard);
        outputView.printChessBoard(chessBoardDto);
    }

    private Command readCommand() {
        final List<String> commands = inputView.readGameCommand();
        Command command = from(commands.get(0));
        GameAction gameAction = commandMapper.get(command);
        gameAction.execute(command, commands);

        return command;
    }

    private void start(final Command command, final List<String> commands) {
        validateCommandSize(commands.size(), START);
        if (!chessGame.isEnd()) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다.");
        }
        chessGame.receiveCommand(command);
    }

    private void move(final Command command, final List<String> commands) {
        validateCommandSize(commands.size(), MOVE);
        if (chessGame.isEnd()) {
            throw new IllegalArgumentException("체스게임을 시작하려면 START를 입력하세요.");
        }

        final String from = commands.get(MOVE_FROM_INDEX);
        final String to = commands.get(MOVE_TO_INDEX);

        chessGame.movePiece(PositionConvertor.convert(from), PositionConvertor.convert(to));
    }

    private void end(final Command command, final List<String> commands) {
        validateCommandSize(commands.size(), END);
        if (chessGame.isEnd()) {
            throw new IllegalArgumentException("체스게임을 시작하려면 START를 입력하세요.");
        }
        chessGame.receiveCommand(command);
    }

    private <T> T readValidateInput(final Supplier<T> function) {
        Optional<T> input;
        do {
            input = repeatByEx(function);
        } while (input.isEmpty());
        return input.get();
    }

    private <T> Optional<T> repeatByEx(final Supplier<T> function) {
        try {
            return Optional.of(function.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
