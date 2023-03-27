package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;

import chess.dto.BoardDto;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    public static final String YES = "y";
    public static final String NO = "n";

    private final Map<Command, CommandAction> commands = Map.of(START, this::start, STATUS, this::status, MOVE, this::move, END, this::end);
    private final GameService gameService = new GameService();
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int gameId = selectGame();
        Command command = START;
        while (command != END) {
            command = readUntilValidate(this::play);
            gameService.updateGame(gameId);
        }
        outputView.printStartWinningTeam(gameService.winningTeams());
        outputView.printGameNumberRoomNotice(gameId);
    }

    private int selectGame() {
        boolean isNewGame = readUntilValidate(this::checkNewGame);
        if (isNewGame) {
            readUntilValidate(this::startNewGame);
            return gameService.saveGame();
        }
        return readUntilValidate(this::startSavedGame);
    }

    public boolean checkNewGame() {
        outputView.printCheckNewGameNotice();
        String input = inputView.readUserInput();
        validateResponse(input);
        return input.equals(YES);
    }

    private void validateResponse(final String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("y와 n로 입력해주세요.");
        }
    }

    public int startSavedGame() {
        outputView.printEnterSavedGameRoomNumberNotice();
        String input = inputView.readUserInput();
        validateNumeric(input);
        int gameId = Integer.parseInt(input);
        gameService.findGame(gameId);
        return gameId;
    }

    private void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("방번호는 숫자로 입력해주세요");
        }
    }

    public Command startNewGame() {
        outputView.printStartNotice();
        return readUntilValidate(this::ready);
    }

    private Command ready() {
        List<String> inputs = inputView.readGameCommand();
        CommandAction commandAction = commands.get(START);
        return commandAction.get(inputs);
    }

    private Command play() {
        outputView.printTeamInTurn(gameService.teamName());
        BoardDto boardDto = BoardDto.of(gameService.board());
        outputView.printChessBoard(boardDto.getPieces());
        List<String> inputs = inputView.readGameCommand();
        Command command = Command.from(inputs);
        validateStart(command);
        CommandAction commandAction = commands.get(command);
        return commandAction.get(inputs);
    }

    private void validateStart(final Command command) {
        if (command == START) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }

    private Command start(List<String> commands) {
        return gameService.start(commands);
    }

    private Command move(List<String> commands) {
        return gameService.move(commands);
    }

    private Command status(List<String> commands) {
        outputView.printScore(gameService.scores());
        outputView.printStartWinningTeam(gameService.winningTeams());
        return STATUS;
    }

    private Command end(List<String> commands) {
        outputView.printEndNotice();
        return END;
    }

    private <T> T readUntilValidate(Supplier<T> supplier) {
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
            System.out.println(e.getMessage());
            return null;
        }
    }
}
