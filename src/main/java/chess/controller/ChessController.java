package chess.controller;

import chess.Command;
import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.PieceType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

        if (retryOnInvalidUserInput(this::isStartCommand)) {
            play(chessGame);
        }
    }

    private boolean isStartCommand() {
        String command = requestCommand().get(Index.MAIN_COMMAND.value);

        if (Command.isStartCommand(command)) {
            return true;
        }

        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    private List<String> requestCommand() {
        return retryOnInvalidUserInput(inputView::requestCommand);
    }

    private void play(ChessGame chessGame) {
        Optional<List<String>> commands;
        do {
            outputView.printChessBoard(chessGame.getChessboard());
            commands = retryOnInvalidUserInput(this::handleCommand);

            commands.ifPresent(command -> actionForCommand(chessGame, command));
        } while (commands.isPresent() && chessGame.isBothKingAlive());

        outputView.printChessBoard(chessGame.getChessboard());
        outputView.printScoreMessage(chessGame);
    }

    private Optional<List<String>> handleCommand() {
        List<String> commands = requestCommand();
        String mainCommand = commands.get(Index.MAIN_COMMAND.value);

        if (Command.isStartCommand(mainCommand)) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }

        if (Command.isEndCommand(mainCommand)) {
            return Optional.empty();
        }

        return Optional.of(commands);
    }

    private void actionForCommand(ChessGame chessGame, List<String> command) {
        String mainCommand = command.get(Index.MAIN_COMMAND.value);

        if (Command.isStatusCommand(mainCommand)) {
            outputView.printScoreMessage(chessGame);
            return;
        }

        movePiece(chessGame, command);
        checkPromotion(chessGame, command);
    }

    private void movePiece(ChessGame chessGame, List<String> command) {
        Square source = getSquare(command.get(Index.SOURCE_SQUARE.value));
        Square target = getSquare(command.get(Index.TARGET_SQUARE.value));

        retryOnInvalidAction(() -> chessGame.move(source, target));
    }

    private Square getSquare(String command) {
        File file = FileRenderer.renderToFile(String.valueOf(command.charAt(Index.FILE.value)));
        Rank rank = RankRenderer.renderToRank(String.valueOf(command.charAt(Index.RANK.value)));

        return Square.getInstanceOf(file, rank);
    }

    private void checkPromotion(ChessGame chessGame, List<String> command) {
        Square movedSquare = getSquare(command.get(Index.TARGET_SQUARE.value));

        if (chessGame.canPromotion(movedSquare)) {
            PieceType pieceType = requestPieceType();

            chessGame.promotePawn(movedSquare, pieceType);
        }
    }

    private PieceType requestPieceType() {
        return retryOnInvalidUserInput(inputView::requestPiece);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }

    private void retryOnInvalidAction(ActionFunction request) {
        try {
            request.run();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
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
