package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.game.GameCommand;
import chess.domain.piece.Team;
import chess.exception.CustomException;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Optional;

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
        } catch (CustomException e) {
            outputView.printErrorMessage(e.getErrorCode());
            start(generateGameCommand());
        }
    }

    private Game loadGame() {
        Optional<Game> game = chessService.makeGame();
        return game.orElseGet(() -> new Game(new Board(new BoardFactory().generateBoard()), Team.WHITE));
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
        } catch (CustomException e) {
            outputView.printErrorMessage(e.getErrorCode());
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
        } catch (CustomException e) {
            outputView.printErrorMessage(e.getErrorCode());
        }
    }

    private void end() {
        outputView.printGameEndMessage();
        printGameStatus();
        chessService.delete();
    }

    private void printGameStatus() {
        double whiteScore = game.calculateScoreOfTeam(Team.WHITE);
        double blackScore = game.calculateScoreOfTeam(Team.BLACK);
        Team winner = Team.calculateWinner(whiteScore, blackScore);

        outputView.printScoreMessage(whiteScore, blackScore);
        outputView.printWinnerMessage(winner);
    }
}
