package chess.controller;

import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.game.GameCommand;
import chess.domain.piece.Team;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;
    private Game game;

    public ChessController(InputView inputView, OutputView outputView, ChessService chessService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessService = chessService;
    }


    public void run() {
        outputView.printGameStartMessage();
        start(generateGameCommand());
        play();
        end();
    }

    private void start(GameCommand gameCommand) {
        try {
            gameCommand.isStart();
            game = loadGame();
            outputView.printChessBoard(game.getPieces());
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            start(generateGameCommand());
        }
    }

    private Game loadGame() {
        Game game = chessService.makeGame();
        if (chessService.makeGame() == null) {
            return new Game();
        }
        return game;
    }

    private void play() {
        boolean isEnd = false;
        while (!isEnd) {
            GameCommand gameCommand = generateGameCommand();
            isEnd = executeGameCommand(gameCommand);
        }
    }

    private GameCommand generateGameCommand() {
        try {
            return new GameCommand(inputView.readGameCommand());
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return generateGameCommand();
        }
    }

    private boolean executeGameCommand(GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            round(gameCommand);
            return game.isGameEnd();
        }
        if (gameCommand.isStatus()) {
            printGameStatus();
            return false;
        }
        if (gameCommand.isSave()) {
            chessService.save(game);
            return false;
        }
        return true;
    }

    private void round(GameCommand gameCommand) {
        try {
            List<Square> squares = gameCommand.convertToSquare();
            Square source = squares.get(SOURCE_INDEX);
            Square target = squares.get(TARGET_INDEX);
            game.move(source, target);
            outputView.printChessBoard(game.getPieces());
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void end() {
        outputView.printGameEndMessage();
        printGameStatus();
        chessService.delete();
    }

    private void printGameStatus() {
        double whiteScore = game.calculateWhiteScore();
        double blackScore = game.calculateBlackScore();
        Team winner = game.calculateWinner(whiteScore, blackScore);

        outputView.printScoreMessage(whiteScore, blackScore);
        outputView.printWinnerMessage(winner);
    }
}
