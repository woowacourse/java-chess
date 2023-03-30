package controller;

import domain.game.*;
import domain.piece.Position;
import domain.piece.Side;
import dto.service.ChessGameCreateResponseDto;
import service.ChessGameService;
import view.GameCommand;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChessController {

    private static final String POSITION_DELIMITER = "";
    private static final int SOURCE_TEXT_INDEX = 1;
    private static final int TARGET_TEXT_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int POSITION_TEXT_SIZE = 2;

    private final ChessGameService chessGameService;
    private final Map<GameCommand, TriConsumer<ChessGame, List<String>, Long>> commands;
    private final Map<GameCommand, Function<List<String>, ChessGameCreateResponseDto>> gameMakeCommands;

    public ChessController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
        this.commands = new EnumMap<>(GameCommand.class);
        this.gameMakeCommands = new EnumMap<>(GameCommand.class);

        commands.put(GameCommand.END, (chessGame, positions, roomId) -> endCommandExecute(chessGame));
        commands.put(GameCommand.STATUS, (chessGame, positions, roomId) -> statusCommandExecute(chessGame));
        commands.put(GameCommand.MOVE, (chessGame, positions, roomId) -> moveCommandExecute(chessGame, positions));
        commands.put(GameCommand.SAVE, (chessGame, positions, roomId) -> saveCommandExecute(chessGame, roomId));

        gameMakeCommands.put(GameCommand.LOAD, (roomId) -> loadCommandExecute(roomId));
        gameMakeCommands.put(GameCommand.NEW, (roomId) -> newCommandExecute());
    }

    public void run() {
        OutputView.printGameGuideMessage();
        repeatByRunnableUntilStartGame(InputView::requestStartCommand);

        OutputView.printGameCreateMessage();
        OutputView.printGameRooms(chessGameService.findAllRooms());
        ChessGameCreateResponseDto chessGameDto = repeatCreateGame();
        ChessGame chessGame = chessGameDto.getChessGame();
        OutputView.printChessBoard(chessGame.getBoard());

        while (chessGame.isPlayable()) {
            play(chessGame, chessGameDto.getRoomId());
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }

    private Runnable repeatByRunnableUntilStartGame(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatByRunnableUntilStartGame(runnable);
        }
    }

    private ChessGameCreateResponseDto createChessGameByCommand(List<String> commands) {
        GameCommand command = GameCommand.from(commands);
        if (command == GameCommand.NEW || command == GameCommand.LOAD) {
            return gameMakeCommands.get(command).apply(commands);
        }
        throw new IllegalArgumentException("new 또는 load명령어를 입력해주세요");
    }

    private ChessGameCreateResponseDto newCommandExecute() {
        OutputView.printNewGameMessage();
        return chessGameService.createGameRoom(new ChessGame(new Board(new ChessBoardGenerator().generate()), Side.WHITE, GameState.RUN));
    }

    private ChessGameCreateResponseDto loadCommandExecute(List<String> commands) {
        Long roomId = Long.valueOf(commands.get(1));
        return chessGameService.loadChessGame(roomId);
    }

    private void play(ChessGame chessGame, Long roomId) {
        try {
            OutputView.printSide(chessGame.getCurrentTurn());
            List<String> userCommandInput = repeatBySupplier(InputView::requestUserCommandInGame);
            GameCommand command = GameCommand.from(userCommandInput);
            commands.get(command).accept(chessGame, userCommandInput, roomId);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame, roomId);
        }
    }

    private List<String> repeatBySupplier(Supplier<List<String>> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }

    private ChessGameCreateResponseDto repeatCreateGame() {
        try {
            List<String> commands = InputView.requestLoadGameOrNewGame();
            return createChessGameByCommand(commands);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatCreateGame();
        }
    }

    private void endCommandExecute(ChessGame chessGame) {
        chessGame.end();
    }

    private void statusCommandExecute(ChessGame chessGame) {
        ScoreBoard scoreBoard = chessGame.makeScoreBoard();
        Map<Side, Double> scores = scoreBoard.calculateScore();
        OutputView.printGameScores(scores);

        Side winner = scoreBoard.calculateWinner(scores);
        OutputView.printWinner(winner);
    }

    private void moveCommandExecute(ChessGame chessGame, List<String> commands) {
        String sourceText = commands.get(SOURCE_TEXT_INDEX);
        String targetText = commands.get(TARGET_TEXT_INDEX);

        GameState gameState = chessGame.move(convertPosition(sourceText), convertPosition(targetText));
        if (gameState == GameState.KING_DEAD) {
            ScoreBoard scoreBoard = chessGame.makeScoreBoard();
            Map<Side, Double> gameScore = scoreBoard.calculateScore();
            Side winner = scoreBoard.calculateWinner(gameScore);
            OutputView.printKingDeadMessage(winner.nextSide());
            OutputView.printWinner(winner);
        }
    }

    private Position convertPosition(String positionText) {
        if (positionText.length() != POSITION_TEXT_SIZE) {
            throw new IllegalArgumentException("위치 값은 a1형식만 가능합니다.");
        }
        List<String> positionTexts = Arrays.asList(positionText.split(POSITION_DELIMITER));
        return Position.of(positionTexts.get(FILE_INDEX), positionTexts.get(RANK_INDEX));
    }

    private void saveCommandExecute(ChessGame chessGame, Long roomId) {
        chessGameService.updateChessGame(chessGame, roomId);
        OutputView.printGameSaveMessage();
    }
}
