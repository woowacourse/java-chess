package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
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

        inputView.printStartChess();

        if (isStartCommand()) {
            extracted(chessGame);
        }
    }

    private void extracted(ChessGame chessGame) {
        Command command;
        do {
            printChessBoard(chessGame.getChessboard());
            command = retryOnInvalidUserInput(() -> play(chessGame));
        } while (command != Command.END);
    }

    private boolean isStartCommand() {
        Command userCommand = Command.renderToCommand(requestCommand().get(0));

        return userCommand == Command.START;
    }

    private Command play(ChessGame chessGame) {
        List<String> command = requestCommand();

        if (Command.renderToCommand(command.get(0)) == Command.END) {
            return Command.END;
        }

        movePiece(chessGame, command);

        return Command.MOVE;
    }

    private void movePiece(ChessGame chessGame, List<String> command) {
        Square source = makeSquare(command.get(1));
        Square target = makeSquare(command.get(2));

        chessGame.move(source, target);
    }

    private void printChessBoard(Chessboard chessboard) {
        outputView.printChessBoard(chessboard);
    }

    private List<String> requestCommand() {
        return retryOnInvalidUserInput(inputView::requestCommand);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }

    private Square makeSquare(String command) {
        File file = FileRenderer.renderToFile(String.valueOf(command.charAt(0)));
        Rank rank = RankRenderer.renderToRank(String.valueOf(command.charAt(1)));

        return new Square(file, rank);
    }

    private enum Command {
        START("start"),
        END("end"),
        MOVE("move");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        private static Command renderToCommand(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.command.equals(input))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
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
}
