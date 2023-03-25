package chess.controller;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.chessgame.ChessGame;
import chess.domain.piecesfactory.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ChessController {

    private final Map<GameState, GameAction> actionByGameState = Map.of(
            GameState.READY, this::start,
            GameState.RUNNING, this::move,
            GameState.STATUS, this::status,
            GameState.END, this::end
    );

    public void run() {
        OutputView.printGameStartGuideMessage();
        ChessGame chessGame = ChessGame.from(new StartingPiecesFactory());
        while (!chessGame.isGameOver()) {
            chessGame = play(chessGame);
        }
    }

    private ChessGame play(ChessGame chessGame) {
        try {
            final Command command = readCommand();
            final GameAction gameAction = actionByGameState.get(command.getGameState());
            chessGame = gameAction.execute(command, chessGame);
            return chessGame;
        } catch (IllegalArgumentException | IllegalStateException | UnsupportedOperationException e) {
            System.out.println(e.getMessage());
            return chessGame;
        }
    }

    private Command readCommand() {
        while (true) {
            try {
                return new Command(InputView.readGameCommand());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ChessGame start(final Command command, ChessGame chessGame) {
        final ChessGame playingChessGame = chessGame.start();
        OutputView.printBoard(playingChessGame.getPieces());
        return playingChessGame;
    }

    private ChessGame move(final Command command, ChessGame chessGame) {
        command.validateCommandSize();
        final Position currentPosition = command.getCurrentPosition();
        final Position targetPosition = command.getTargetPosition();

        chessGame = chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(chessGame.getPieces());
        return chessGame;
    }

    private ChessGame status(final Command command, final ChessGame chessGame) {
        final Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();
        OutputView.printScores(scoreByColor);
        OutputView.printWinner(chessGame.findScoreWinner());
        return chessGame;
    }

    private ChessGame end(final Command command, final ChessGame chessGame) {
        return chessGame.end();
    }
}
