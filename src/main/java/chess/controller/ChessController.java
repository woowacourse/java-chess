package chess.controller;

import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.maker.StartingPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final int COMMAND_INDEX = 0;
    private final int MOVE_CURRENT_POSITION_INDEX = 1;
    private final int MOVE_TARGET_POSITION_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStartGuideMessage();

        GameCommand command;
        ChessGame chessGame = null;
        do {
            final List<String> commandInputs = inputView.readGameCommand();
            command = GameCommand.findBy(commandInputs.get(COMMAND_INDEX));


            if (command == GameCommand.START) {
                chessGame = new ChessGame(new StartingPiecesGenerator());
                outputView.printBoard(chessGame.getExistingPieces());
            }
            if ((command == GameCommand.MOVE) && (chessGame != null)) {
                final Position currentPosition = generatePositionBy(commandInputs.get(MOVE_CURRENT_POSITION_INDEX));
                final Position targetPosition = generatePositionBy(commandInputs.get(MOVE_TARGET_POSITION_INDEX));

                chessGame.move(currentPosition, targetPosition);

                outputView.printBoard(chessGame.getExistingPieces());
            }

        } while (command != GameCommand.END);

    }

    private Position generatePositionBy(String fileRankInput) {
        final String fileValue = String.valueOf(fileRankInput.charAt(0));
        final String rankValue = String.valueOf(fileRankInput.charAt(1));

        return new Position(File.findByValue(fileValue), Rank.findByValue(rankValue));
    }

}
