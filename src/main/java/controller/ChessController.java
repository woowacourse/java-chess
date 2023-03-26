package controller;

import domain.game.Board;
import domain.game.ChessBoardGenerator;
import domain.game.ChessGame;
import domain.game.GameState;
import domain.piece.Position;
import domain.piece.Side;
import dto.ChessGameSaveRequestDto;
import dto.service.ChessGameControllerResponseDto;
import service.ChessGameService;
import view.GameCommand;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ChessController {
    // TODO: 2023/03/26 현재 turn side출력 
    private static final String POSITION_DELIMITER = "";
    private static final int SOURCE_TEXT_INDEX = 1;
    private static final int TARGET_TEXT_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private final Map<GameCommand, BiConsumer<ChessGame, List<String>>> commands;

    public ChessController(InputView inputView, OutputView outputView, ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
        this.commands = new EnumMap<>(GameCommand.class);

        commands.put(GameCommand.END, (chessGame, positions) -> endCommandExecute(chessGame));
        commands.put(GameCommand.STATUS, (chessGame, positions) -> statusCommandExecute(chessGame));
        commands.put(GameCommand.MOVE, (chessGame, positions) -> moveCommandExecute(chessGame, positions));
    }

    public void run() {
        this.outputView.printGameGuideMessage();
        repeatByRunnable(inputView::requestStartCommand);
        GameCommand chessGameCommand = repeatNewGameOrLoadGameCommand(inputView::requestLoadGameOrNewGame);
        ChessGame chessGame = createChessGameByCommand(chessGameCommand);

        this.outputView.printChessBoard(chessGame.getBoard());
        while (chessGame.isPlayable()) {
            play(chessGame);
            outputView.printChessBoard(chessGame.getBoard());
        }
    }

    private ChessGame createChessGameByCommand(GameCommand command) {
        if (command == GameCommand.LOAD) {
            ChessGameControllerResponseDto chessGameResponseDto = chessGameService.loadChessGame();
            return new ChessGame(new Board(chessGameResponseDto.getBoard()), chessGameResponseDto.getLastTurn(), GameState.RUN);
        }
        return new ChessGame(new Board(new ChessBoardGenerator().generate()), Side.WHITE, GameState.RUN);
    }

    private void play(ChessGame chessGame) {
        List<String> userCommandInput = repeatBySupplier(inputView::requestUserCommandInGame);
        try {
            GameCommand command = GameCommand.from(userCommandInput);
            commands.get(command).accept(chessGame, userCommandInput);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private boolean isExitCommand(GameCommand command) {
        return command.equals(GameCommand.END);
    }

    private Runnable repeatByRunnable(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatByRunnable(runnable);
        }
    }

    private List<String> repeatBySupplier(Supplier<List<String>> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }

    private GameCommand repeatNewGameOrLoadGameCommand(Supplier<GameCommand> supplier) {
        try {
            this.outputView.printLoadGameMessage();
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatNewGameOrLoadGameCommand(supplier);
        }
    }

    private void endCommandExecute(ChessGame chessGame) {
        chessGameService.saveChessGame(ChessGameSaveRequestDto.from(chessGame));
        chessGame.end();
    }

    private void statusCommandExecute(ChessGame chessGame) {
        Map<Side, Double> scores = chessGame.calculateScores();
        outputView.printGameScores(scores);
        outputView.printWinner(chessGame.calculateWinner());
    }

    private void moveCommandExecute(ChessGame chessGame, List<String> commands) {
        String sourceText = commands.get(SOURCE_TEXT_INDEX);
        String targetText = commands.get(TARGET_TEXT_INDEX);

        GameState gameState = chessGame.move(convertPosition(sourceText), convertPosition(targetText));
        if (gameState == GameState.KING_DEAD) {
            outputView.printWinner(chessGame.calculateWinner());
        }
    }

    private Position convertPosition(String positionText) {
        List<String> positionTexts = Arrays.asList(positionText.split(POSITION_DELIMITER));
        return Position.of(positionTexts.get(FILE_INDEX), positionTexts.get(RANK_INDEX));
    }
}
