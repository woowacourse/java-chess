package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.dto.BoardDto;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private ChessGame chessGame = new ChessGame(new Board(), WHITE);
    private final Map<Command, CommandAction> commands = new EnumMap<>(Command.class);
    private final GameService gameService = new GameService();

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        initCommands();
        Command command = START;
        int gameId = 0;
        boolean isNewGame = readUntilValidate(this::checkNewGame);
        if (isNewGame) {
            command = readUntilValidate(this::startNewGame);
            gameId = gameService.saveGame(chessGame);
        }
        if (!isNewGame) {
            gameId = startSavedGame();
        }
        while (command != END) {
            command = readUntilValidate(this::play);
            gameService.updateGame(chessGame, gameId);
        }
        outputView.printStartWinningTeam(chessGame.determineWinningTeam());
        System.out.printf("게임을 진행한 방은 %d번 입니다. "
                + "이후에 번호를 입력하면 게임을 이어서 할 수 있습니다.\n", gameId);
    }

    public boolean checkNewGame() {
        outputView.printCheckNewGameNotice();
        String input = inputView.readUserInput();
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y와 n로 입력해주세요.");
        }
        return input.equals("y");
    }

    public int startSavedGame() {
        outputView.printEnterSavedGameRoomNumberNotice();
        String input = inputView.readUserInput();
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("방번호는 숫자로 입력해주세요");
        }
        int gameId = Integer.parseInt(input);
        chessGame = gameService.findGame(gameId);
        return gameId;
    }

    public Command startNewGame() {
        outputView.printStartNotice();
        return readUntilValidate(this::ready);
    }

    private void initCommands() {
        commands.put(START, this::start);
        commands.put(STATUS, this::status);
        commands.put(MOVE, this::move);
        commands.put(END, this::end);
    }

    private Command ready() {
        List<String> inputs = inputView.readGameCommand();
        CommandAction commandAction = commands.get(START);
        return commandAction.get(inputs);
    }

    private Command play() {
        outputView.printTeamInTurn(chessGame.teamName());
        BoardDto boardDto = BoardDto.of(chessGame.getBoard());
        outputView.printChessBoard(boardDto.getPieces());
        List<String> inputs = inputView.readGameCommand();
        Command command = Command.from(inputs);
        if (command == START) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
        CommandAction commandAction = commands.get(command);
        return commandAction.get(inputs);
    }

    private Command start(List<String> commands) {
        Command command = Command.from(commands);
        if (command != START) {
            throw new IllegalArgumentException("start를 입력하여 게임을 실행하세요");
        }
        return command;
    }

    private Command move(List<String> commands) {
        chessGame.movePiece(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        if (chessGame.isCheckmate()) {
            return END;
        }
        return MOVE;
    }

    private Command status(List<String> commands) {
        final double whiteTeamScore = chessGame.calculateScoreBy(WHITE);
        final double blackTeamScore = chessGame.calculateScoreBy(BLACK);
        outputView.printScore(Map.of(WHITE.name(), whiteTeamScore,
                BLACK.name(), blackTeamScore));
        outputView.printStartWinningTeam(chessGame.determineWinningTeam());
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
