package chess.controller;

import chess.db.ChessGameDBConnector;
import chess.db.ChessGameDBService;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.position.Positions;
import chess.domain.score.Scores;
import chess.dto.Status;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessGameController {
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile(
            "^" + Command.MOVE.command() + "\\s+(\\w\\d\\s+\\w\\d)$");

    private final ChessGameDBService chessGameDbService = new ChessGameDBService(new ChessGameDBConnector());
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        while (isNotStartCommand()) {
            outputView.printGuidanceForStart();
        }
        startChessGame();
    }

    private boolean isNotStartCommand() {
        String command = inputView.readCommand();
        return !Command.START.sameWith(command);
    }

    private void startChessGame() {
        ChessGame chessGame = registerChessGame();
        outputView.printBoard(chessGame.collectBoard());

        boolean canContinue = true;
        while (canContinue) {
            String command = readGameCommand();
            play(chessGame, command);
            canContinue = canContinue(chessGame, command);
        }
    }

    private ChessGame registerChessGame() {
        if (chessGameDbService.hasPreviousData()) {
            return chessGameDbService.getCurrentChessGame();
        }
        return new ChessGame(new BoardFactory().getInitialBoard());
    }

    private String readGameCommand() {
        String command = inputView.readCommand();
        try {
            validateIllegalGameCommand(command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return command;
    }

    private void validateIllegalGameCommand(String command) {
        if (!Command.hasCommand(command) && !Command.MOVE.startsWith(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    private void play(ChessGame chessGame, String command) {
        if (Command.MOVE.startsWith(command)) {
            movePiece(chessGame, command);
        }
        if (Command.STATUS.sameWith(command)) {
            showStatus(chessGame);
        }
    }

    private void showStatus(ChessGame chessGame) {
        Scores scores = chessGame.calculateScores();
        Status status = Status.of(scores);
        outputView.printStatus(status);
    }

    private void movePiece(ChessGame chessGame, String command) {
        try {
            Positions positions = readPositions(command);
            chessGame.move(positions);
            outputView.printBoard(chessGame.collectBoard());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private Positions readPositions(String command) {
        List<String> rawPositions = parseDepartureDestination(command);
        return new Positions(rawPositions);
    }

    private List<String> parseDepartureDestination(String command) {
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);

        if (matcher.find()) {
            return List.of(matcher.group(1).split("\\s+"));
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
    }

    private boolean canContinue(ChessGame chessGame, String command) {
        if (chessGame.isKingCaptured()) {
            outputView.printResult(chessGame.findLoser(), chessGame.findWinner());
            return false;
        }
        return isNotEnd(chessGame, command);
    }

    private boolean isNotEnd(ChessGame chessGame, String command) {
        if (Command.END.sameWith(command)) {
            chessGameDbService.saveGame(chessGame);
            return false;
        }
        return true;
    }
}
