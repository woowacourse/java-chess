package chess.controller;

import static chess.domain.Command.END;
import static chess.domain.Command.MOVE;
import static chess.domain.Command.START;
import static chess.domain.Command.STATUS;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private final ChessGame chessGame = new ChessGame(new Board(), WHITE);
    private final Map<Command, CommandAction> commands = new EnumMap<>(Command.class);

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        initCommands();
        outputView.printStartNotice();
        Command command = readUntilValidate(this::ready);
        while (command != END) {
            command = readUntilValidate(this::play);
        }
        outputView.printStartWinningTeam(chessGame.determineWinningTeam());

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
        BoardDto boardDto = BoardDto.of(chessGame.getBoard());
        outputView.printChessBoard(boardDto.getPieces());
        return command;
    }

    private Command move(List<String> commands) {
        chessGame.movePiece(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        BoardDto boardDto = BoardDto.of(chessGame.getBoard());
        outputView.printChessBoard(boardDto.getPieces());
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
