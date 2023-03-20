package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        outputView.printStartMessage();

        if (isStartCommand()) {
            play(chessGame);
        }
    }

    private boolean isStartCommand() {
        return Command.isStartCommand(requestCommand().get(Index.MAIN_COMMAND.value));
    }

    private List<String> requestCommand() {
        return retryOnInvalidUserInput(inputView::requestCommand);
    }

    private void play(ChessGame chessGame) {
        do {
            outputView.printChessBoard(chessGame.getChessboard());
        } while (retryOnInvalidUserInput(() -> handleCommand(chessGame)) != Command.END);
    }

    private Command handleCommand(ChessGame chessGame) {
        List<String> command = requestCommand();

        if (Command.isEndCommand(command.get(Index.MAIN_COMMAND.value))) {
            return Command.END;
        }

        movePiece(chessGame, command);

        return Command.MOVE;
    }

    private void movePiece(ChessGame chessGame, List<String> command) {
        Square source = getSquare(command.get(Index.SOURCE_SQUARE.value));
        Square target = getSquare(command.get(Index.TARGET_SQUARE.value));

        chessGame.move(source, target);
    }

    private Square getSquare(String command) {
        File file = FileRenderer.renderToFile(String.valueOf(command.charAt(Index.FILE.value)));
        Rank rank = RankRenderer.renderToRank(String.valueOf(command.charAt(Index.RANK.value)));

        return Square.getInstanceOf(file, rank);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }

    private enum Command {
        START("start"),
        END("end"),
        MOVE("move");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        private static boolean isStartCommand(String input) {
            return START.command.equals(input);
        }

        private static boolean isEndCommand(String input) {
            return END.command.equals(input);
        }
    }

    private enum FileRenderer {
        A("a", File.A),
        B("b", File.B),
        C("c", File.C),
        D("d", File.D),
        E("e", File.E),
        F("f", File.F),
        G("g", File.G),
        H("h", File.H);

        private final String command;
        private final File file;

        FileRenderer(String command, File rank) {
            this.command = command;
            this.file = rank;
        }

        static private File renderToFile(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.command.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File입니다."))
                    .file;
        }
    }

    private enum RankRenderer {
        EIGHT("8", Rank.EIGHT),
        SEVEN("7", Rank.SEVEN),
        SIX("6", Rank.SIX),
        FIVE("5", Rank.FIVE),
        FOUR("4", Rank.FOUR),
        THREE("3", Rank.THREE),
        TWO("2", Rank.TWO),
        ONE("1", Rank.ONE);

        private final String command;
        private final Rank rank;

        RankRenderer(String command, Rank rank) {
            this.command = command;
            this.rank = rank;
        }

        static private Rank renderToRank(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.command.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank입니다."))
                    .rank;
        }
    }

    private enum Index {
        MAIN_COMMAND(0),
        SOURCE_SQUARE(1),
        TARGET_SQUARE(2),
        FILE(0),
        RANK(1);

        private final int value;

        Index(int value) {
            this.value = value;
        }
    }
}
