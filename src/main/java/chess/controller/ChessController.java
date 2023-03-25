package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.maker.EmptyPiecesFactory;
import chess.domain.board.maker.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ChessController {


    private final Map<GameState, GameAction> actionByGameState = Map.of(
            GameState.READY, this::start,
            GameState.RUNNING, this::move,
            GameState.STATUS, this::status
    );


    public void run() {
        OutputView.printGameStartGuideMessage();
        ChessGame chessGame = ChessGame.from(new EmptyPiecesFactory());
        do {
            final Command command = readCommand();
            if (command.isEnd()) {
                break;
            }
            chessGame = play(chessGame, command);
        } while (!chessGame.isGameOver());
    }

    private Command readCommand() {
        while (true) {
            try {
                return new Command(InputView.readGameCommand());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ChessGame play(ChessGame chessGame, final Command command) {
        try {
            final GameAction gameAction = actionByGameState.get(command.getGameState());
            chessGame = gameAction.execute(command, chessGame);
            return chessGame;
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return chessGame;
        }
    }

    private ChessGame start(final Command command, ChessGame chessGame) {
        validateChessGameSetting(chessGame);
        chessGame = ChessGame.from(new StartingPiecesFactory());
        OutputView.printBoard(chessGame.getExistingPieces());
        return chessGame;
    }

    private void validateChessGameSetting(final ChessGame chessGame) {
        if (chessGame.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 실행되고 있습니다.");
        }
    }

    private ChessGame move(final Command command, ChessGame chessGame) {
        command.validateCommandSize();
        final Position currentPosition = command.getCurrentPosition();
        final Position targetPosition = command.getTargetPosition();

        chessGame = chessGame.move(currentPosition, targetPosition);

        OutputView.printBoard(chessGame.getExistingPieces());
        return chessGame;
    }

    private ChessGame status(final Command command, final ChessGame chessGame) {
        final Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();
        OutputView.printScores(scoreByColor);
        OutputView.printWinner(chessGame.findScoreWinner());
        OutputView.printBoard(chessGame.getExistingPieces());
        return chessGame;
    }
}
