package chess.controller;

import chess.db.dao.BoardDao;
import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static chess.controller.Command.*;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class GameController {

    private static final int MOVE_COMMAND_SIZE = 3;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, Action> commandAction = new EnumMap<>(Command.class);
    private Command status = READY;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        commandAction.put(START, (chessGame, ignore) -> start(chessGame));
        commandAction.put(STATUS, (chessGame, ignore) -> status(chessGame));
        commandAction.put(MOVE, this::move);
        commandAction.put(END, (chessGame, ignore) -> end());
    }

    public void run() {
        outputView.printGameStart();
        BoardDao boardDao = new BoardDao();
        Map<Square, Piece> storedBoard = boardDao.select();
        if (storedBoard == null) {
            storedBoard = BoardGenerator.init();
            boardDao.insert(storedBoard);
        }
        ChessGame chessGame = new ChessGame(new Board(storedBoard), WHITE);

        Command command;
        do {
            List<String> gameCommand = repeatUntilValidate(() -> play(chessGame));
            command = Command.from(gameCommand.get(0));
        } while (command != END && !chessGame.isGameEnd());
        if (chessGame.isGameEnd()) {
            status(chessGame);
            boardDao.delete();
        }
    }

    public List<String> play(final ChessGame chessGame) {
        List<String> gameCommand = repeatUntilValidate(this::inputCommand);
        Command command = Command.from(gameCommand.get(0));
        validateStatus(command);
        commandAction.get(command).execute(chessGame, gameCommand);
        status = command;
        return gameCommand;
    }

    private List<String> inputCommand() {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(0));
        return gameCommand;
    }

    private void validateStatus(final Command command) {
        if (command == START && status != READY) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다. move, status, end 중에 입력해주세요.");
        }
        if (command != START && status == READY) {
            throw new IllegalArgumentException("체스 게임이 아직 시작되지 않았습니다. start 먼저 입력해주세요.");
        }
    }

    private void start(final ChessGame chessGame) {
        outputView.printChessBoard(chessGame.getBoard());
    }

    private void status(final ChessGame chessGame) {
        outputView.printTeamScore(WHITE, chessGame.calculateTeamScore(WHITE));
        outputView.printTeamScore(BLACK, chessGame.calculateTeamScore(BLACK));
        outputView.printWinnerTeam(chessGame.calculateWinnerTeam());
    }

    private void move(final ChessGame chessGame, final List<String> gameCommand) {
        validateMoveCommandFormat(gameCommand);
        chessGame.movePiece(gameCommand.get(1), gameCommand.get(2));
        outputView.printChessBoard(chessGame.getBoard());
    }

    private void end() {
    }

    private void validateMoveCommandFormat(final List<String> gameCommand) {
        if (gameCommand.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }

    private <T> T repeatUntilValidate(final Supplier<T> supplier) {
        T input;
        do {
            input = readUserInput(supplier);
        } while (input == null);
        return input;
    }

    private <T> T readUserInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
