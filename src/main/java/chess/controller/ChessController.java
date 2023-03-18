package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.Type;
import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.maker.StartingPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final int MOVE_CURRENT_POSITION_INDEX = 0;
    private final int MOVE_TARGET_POSITION_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        chessGame = ChessGame.createWithUninitializedBoard();
    }

    public void run() {
        outputView.printGameStartGuideMessage();

        Command command;
        do {
            command = Command.from(inputView.readGameCommand());

            processCommand(command);

            outputView.printBoard(chessGame.getExistingPieces());

        } while (!command.is(Type.END));

    }

    private void processCommand(final Command command) {
        if (command.is(Type.START)) {
            processStart();
        }
        if (command.is(Type.MOVE)) {
            processMove(command);
        }
    }

    private void processStart() {
        validateWithStartCommand();
        chessGame = ChessGame.createWith(new StartingPiecesGenerator());
    }

    private void processMove(final Command command) {
        validateWithMoveCommand();
        final Position currentPosition = generatePositionBy(command.getParameterAt(MOVE_CURRENT_POSITION_INDEX));
        final Position targetPosition = generatePositionBy(command.getParameterAt(MOVE_TARGET_POSITION_INDEX));
        chessGame.move(currentPosition, targetPosition);
    }

    private void validateWithStartCommand() {
        if (chessGame.isInitialized()) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }

    private void validateWithMoveCommand() {
        if (!chessGame.isInitialized()) {
            throw new IllegalArgumentException("아직 게임이 생성되지 않았습니다.");
        }
    }

    private Position generatePositionBy(String fileRankInput) {
        final String fileValue = String.valueOf(fileRankInput.charAt(0));
        final String rankValue = String.valueOf(fileRankInput.charAt(1));

        return new Position(File.findByValue(fileValue), Rank.findByValue(rankValue));
    }

}
