package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.board.Square;
import chess.domain.piece.PieceType;
import chess.dto.SquareRenderer;
import chess.view.InputView;
import chess.view.OutputView;

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
        Command command = getMainCommand(requestCommand());

        if (command.isStartCommand()) {
            return true;
        }

        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    private List<String> requestCommand() {
        return retryOnInvalidUserInput(inputView::requestCommand);
    }

    private Command getMainCommand(List<String> command) {
        String mainCommand = command.get(Index.MAIN_COMMAND.value);

        return Command.renderToCommand(mainCommand);
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
        Command command = getMainCommand(commands);

        if (command.isStartCommand()) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }

        if (command.isEndCommand()) {
            //디비 추가
            return Optional.empty();
        }

        return Optional.of(commands);
    }

    private void actionForCommand(ChessGame chessGame, List<String> command) {
        Command mainCommand = getMainCommand(command);

        if (mainCommand.isStatusCommand()) {
            outputView.printScoreMessage(chessGame);
            return;
        }

        movePiece(chessGame, command);
        checkPromotion(chessGame, command);
    }

    private void movePiece(ChessGame chessGame, List<String> command) {
        Square source = SquareRenderer.render(command.get(Index.SOURCE_SQUARE.value));
        Square target = SquareRenderer.render(command.get(Index.TARGET_SQUARE.value));

        retryOnInvalidAction(() -> chessGame.move(source, target));
    }

    private void checkPromotion(ChessGame chessGame, List<String> command) {
        Square movedSquare = SquareRenderer.render(command.get(Index.TARGET_SQUARE.value));

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
