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
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^move\\s+([a-h][1-8]\\s+[a-h][1-8])$");

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        waitStartCommand();
    }

    private void waitStartCommand() {
        String command = inputView.readCommand().trim();
        if (command.equals("start")) {
            final ChessGame chessGame = new ChessGame(new BoardFactory().getInitialBoard());
            outputView.printBoard(chessGame.collectBoard());
            startChessGame(chessGame);
            return;
        }
        outputView.printGuidanceForStart();
        waitStartCommand();
    }

    private void startChessGame(ChessGame chessGame) {
        try {
            doOneRound(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        startChessGame(chessGame);
    }

    private void doOneRound(ChessGame chessGame) {
        String command = inputView.readCommand().trim();
        if (command.equals("end")) {
            System.exit(0);
        }
        if (command.startsWith("move")) {
            movePiece(chessGame, command);
            return;
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
    }

    private void movePiece(ChessGame chessGame, String command) {
        List<Position> positions = readPositions(command);
        chessGame.move(positions.get(0), positions.get(1));
        outputView.printBoard(chessGame.collectBoard());
    }

    private List<Position> readPositions(String command) {
        List<Position> positions = new ArrayList<>();
        List<String> rawPositions = parseDepartureDestination(command);
        positions.add(parsePosition(rawPositions.get(0)));
        positions.add(parsePosition(rawPositions.get(1)));
        return positions;
    }

    private Position parsePosition(String rawPosition) {
        int departureColumn = Column.findColumn(String.valueOf(rawPosition.charAt(0)));
        int departureRank = parseRank(String.valueOf(rawPosition.charAt(1)));
        return new Position(departureColumn, departureRank);
    }

    private List<String> parseDepartureDestination(String command) {
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);

        if (matcher.find()) {
            return List.of(matcher.group(1).split("\\s+"));
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
    }

    private int parseRank(String rawRank) {
        try {
            return Integer.parseInt(rawRank);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 명령어를 입력해주세요.");
        }
    }
}
