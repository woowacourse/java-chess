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

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void initialize() {

    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();

        inputView.printStartChess();
        if (Command.renderToCommand(inputView.requestCommend().get(0)) != Command.START) {
            return;
        }
        outputView.printChessBoard(chessboard);

        List<String> commend = inputView.requestCommend();
        while (Command.renderToCommand(commend.get(0)) != Command.END) {
            commend = catchException(chessGame,commend);
        }
    }

    private List<String> catchException(ChessGame chessGame, List<String> commend) {
        try {
            chessGame.move(makeSquare(commend.get(1)), makeSquare(commend.get(2)));
            outputView.printChessBoard(chessGame.getChessboard());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            outputView.printChessBoard(chessGame.getChessboard());
            return inputView.requestCommend();
        }
        return inputView.requestCommend();
    }

    private Square makeSquare(String command) {
        File file = FileRenderer.renderString(String.valueOf(command.charAt(0)));
        Rank rank = RankRenderer.renderString(String.valueOf(command.charAt(1)));

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
        private final String input;
        private final File output;

        FileRenderer(String input, File output) {
            this.input = input;
            this.output = output;
        }

        static private File renderString(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.input.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException())
                    .output;
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
        private final String input;
        private final Rank output;

        RankRenderer(String input, Rank output) {
            this.input = input;
            this.output = output;
        }

        static private Rank renderString(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.input.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException())
                    .output;
        }
    }
}
