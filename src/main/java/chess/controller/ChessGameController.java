package chess.controller;

import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessGameController {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^" + MOVE_COMMAND + "\\s+(\\w\\d\\s+\\w\\d)$");

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
        String command = inputView.readCommand().trim();
        return !command.equals(START_COMMAND);
    }

    private void startChessGame() {
        ChessGame chessGame = new ChessGame(new BoardFactory().getInitialBoard());
        outputView.printBoard(chessGame.collectBoard());

        boolean canContinue = true;
        while (canContinue) {
            String command = readGameCommand();
            tryOneRound(chessGame, command);
            canContinue = canContinue(command);
        }
    }

    private String readGameCommand() {
        String command = inputView.readCommand().trim();
        try {
            validateIllegalGameCommand(command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return command;
    }

    private void validateIllegalGameCommand(String command) {
        if (!command.startsWith(MOVE_COMMAND) && !command.equals(END_COMMAND)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    private void tryOneRound(ChessGame chessGame, String command) {
        if (command.startsWith(MOVE_COMMAND)) {
            movePiece(chessGame, command);
        }
    }

    private void movePiece(ChessGame chessGame, String command) {
        try {
            List<Position> positions = readPositions(command);
            chessGame.move(positions.get(0), positions.get(1));
            outputView.printBoard(chessGame.collectBoard());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private List<Position> readPositions(String command) {
        List<Position> positions = new ArrayList<>();
        List<String> rawPositions = parseDepartureDestination(command);
        positions.add(parsePosition(rawPositions.get(0)));
        positions.add(parsePosition(rawPositions.get(1)));
        return positions;
    }

    private Position parsePosition(String rawPosition) {
        int departureColumn = File.findFile(String.valueOf(rawPosition.charAt(0)));
        int departureRank = Rank.findRank(String.valueOf(rawPosition.charAt(1)));
        return new Position(departureColumn, departureRank);
    }

    private List<String> parseDepartureDestination(String command) {
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);

        if (matcher.find()) {
            return List.of(matcher.group(1).split("\\s+"));
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
    }

    private boolean canContinue(String command) {
        return !command.equals("end");
    }
}
