package chess.controller;

import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.maker.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final int COMMAND_INDEX = 0;
    private final int MOVE_CURRENT_POSITION_INDEX = 1;
    private final int MOVE_TARGET_POSITION_INDEX = 2;

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

        while (true) {
            final List<String> commandInputs = inputView.readGameCommand();

            processStartCommand(commandInputs);

            processMoveCommand(commandInputs);

            if (GameState.valueOfCommand(commandInputs.get(COMMAND_INDEX)) == GameState.END) {
                break;
            }
        }

    }

    private void processStartCommand(final List<String> commandInputs) {
        final GameState command = GameState.valueOfCommand(commandInputs.get(COMMAND_INDEX));
        if (command == GameState.READY) {
            validateWithStartCommand();
            chessGame = ChessGame.createWith(new StartingPiecesFactory());
            outputView.printBoard(chessGame.getExistingPieces());
        }
    }

    private void validateWithStartCommand() {
        if (chessGame.isInitialized()) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }

    private void processMoveCommand(final List<String> commandInputs) {
        final GameState command = GameState.valueOfCommand(commandInputs.get(COMMAND_INDEX));
        if ((command == GameState.RUNNING)) {
            validateWithMoveCommand();
            final Position currentPosition = generatePositionBy(commandInputs.get(MOVE_CURRENT_POSITION_INDEX));
            final Position targetPosition = generatePositionBy(commandInputs.get(MOVE_TARGET_POSITION_INDEX));

            chessGame.move(currentPosition, targetPosition);

            outputView.printBoard(chessGame.getExistingPieces());
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
