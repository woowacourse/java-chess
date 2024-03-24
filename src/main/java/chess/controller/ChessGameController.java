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
    private static final int COLUMN_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int SOURCE_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;
    private static final int SOURCE_DESTINATION_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        waitStartCommand();
        final ChessGame chessGame = new ChessGame(new BoardFactory().getInitialBoard());
        outputView.printBoard(chessGame.collectBoard());
        startChessGame(chessGame);
    }

    private void waitStartCommand() {
        String command = inputView.readCommand();
        if ("start".equals(command)) {
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
        String command = inputView.readCommand();
        if (command.equals("end")) {
            System.exit(0);
        }
        if (!command.startsWith("move")) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
        movePiece(chessGame, command);
    }

    private void movePiece(ChessGame chessGame, String command) {
        List<Position> positions = readPositions(command);
        chessGame.move(positions.get(COLUMN_INDEX), positions.get(RANK_INDEX));
        outputView.printBoard(chessGame.collectBoard());
    }

    private List<Position> readPositions(String command) {
        List<Position> positions = new ArrayList<>();
        List<String> rawPositions = parseSourceDestination(command);
        positions.add(parsePosition(rawPositions.get(SOURCE_INDEX)));
        positions.add(parsePosition(rawPositions.get(DESTINATION_INDEX)));
        return positions;
    }

    private Position parsePosition(String rawPosition) {
        int departureColumn = Column.findColumn(String.valueOf(rawPosition.charAt(COLUMN_INDEX)));
        int departureRank = parseRank(String.valueOf(rawPosition.charAt(RANK_INDEX)));
        return new Position(departureColumn, departureRank);
    }

    private List<String> parseSourceDestination(String command) {
        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(command);

        if (!matcher.find()) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
        return List.of(matcher.group(SOURCE_DESTINATION_INDEX).split("\\s+"));
    }

    private int parseRank(String rawRank) {
        try {
            return Integer.parseInt(rawRank);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 명령어를 입력해주세요.");
        }
    }
}
