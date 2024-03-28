package chess.game;

import chess.board.Board;
import chess.board.BoardInitializer;
import chess.game.state.GameState;
import chess.game.state.InitState;
import chess.piece.Color;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import chess.score.Score;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PathDto;
import chess.view.display.BoardDisplayConverter;
import chess.view.display.RankDisplay;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, CommandExecutor> executors;
    private GameState gameState;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.executors = new EnumMap<>(Command.class);
        this.gameState = new InitState();
    }

    public void run() {
        Board board = BoardInitializer.createBoard();
        BoardDisplayConverter converter = new BoardDisplayConverter();
        prepareCommandExecutors(board, converter);

        outputView.printInitMessage();
        executeCommandFromInput();
    }

    private void prepareCommandExecutors(Board board, BoardDisplayConverter converter) {
        executors.put(Command.START, () -> startGame(board, converter));
        executors.put(Command.MOVE, () -> proceedTurn(board, converter));
        executors.put(Command.STATUS, () -> showStatus(board));
        executors.put(Command.END, () -> gameState = gameState.terminate());
    }

    private void executeCommandFromInput() {
        Command command = inputView.readCommand();
        CommandExecutor commandExecutor = executors.get(command);
        commandExecutor.execute();
    }

    private void startGame(Board board, BoardDisplayConverter converter) {
        gameState = gameState.start();
        printBoard(board, converter);
        while (gameState.isPlaying()) {
            executeCommandFromInput();
        }
        outputView.printEndMessage();
    }

    private void proceedTurn(Board board, BoardDisplayConverter converter) {
        PathDto pathDto = inputView.readPosition();
        Position source = getSourceFrom(pathDto);
        Position destination = getDestinationFrom(pathDto);
        gameState = gameState.proceedTurn(board, source, destination);
        printBoard(board, converter);
    }

    private void showStatus(Board board) {
        gameState.validatePlaying();
        Score whiteScore = board.calculateScore(Color.WHITE);
        Score blackScore = board.calculateScore(Color.BLACK);
        outputView.printScore(whiteScore.getScore(), blackScore.getScore());
    }

    private void printBoard(Board board, BoardDisplayConverter converter) {
        List<RankDisplay> rankDisplays = converter.convert(board.pieces());
        outputView.printBoard(rankDisplays);
    }

    private Position getSourceFrom(PathDto pathDto) {
        return Position.of(
                File.from(pathDto.sourceFileName()),
                Rank.from(pathDto.sourceRankNumber())
        );
    }

    private Position getDestinationFrom(PathDto pathDto) {
        return Position.of(
                File.from(pathDto.destinationFileName()),
                Rank.from(pathDto.destinationRankNumber())
        );
    }
}
