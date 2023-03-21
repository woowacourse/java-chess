package chess.controller;

import chess.domain.position.Position;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {

    private static final String POSITION_LENGTH_VALIDATE = "올바른 체스 좌표를 입력해주십시오";
    private static final Map<GameCommand, Consumer<ChessService>> commandMapper = Map.of(
            GameCommand.START, ChessController::start,
            GameCommand.MOVE, ChessController::move,
            GameCommand.END, ChessController::end
    );
    private static final int FROM_POSITION_INDEX = 0;
    private static final int TO_POSITION_INDEX = 1;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public void run() {
        OutputView.printGameStartInfo();
        final ChessService chessService = new ChessService();

        while (!chessService.isEnd()) {
            playChess(chessService);
        }
    }

    private void playChess(final ChessService chessService) {
        GameCommand gameCommand = readCommand();
        try {
            commandMapper.get(gameCommand).accept(chessService);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            playChess(chessService);
        }
    }

    private static void start(final ChessService chessService) {
        chessService.start();
        OutputView.printBoard(chessService.getBoard());
    }

    private static void move(final ChessService chessService) {
        chessService.move(readPositions());
        OutputView.printBoard(chessService.getBoard());
    }

    private static void end(final ChessService chessService) {
        chessService.end();
    }

    private GameCommand readCommand() {
        try {
            return GameCommand.from(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readCommand();
        }
    }

    private static List<Position> readPositions() {
        final List<String> positions = InputView.readPositions();
        final String from = positions.get(FROM_POSITION_INDEX);
        final String to = positions.get(TO_POSITION_INDEX);
        return List.of(renderPosition(from), renderPosition(to));
    }

    private static Position renderPosition(final String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException(POSITION_LENGTH_VALIDATE);
        }
        final int file = position.charAt(FILE_INDEX) - 'a' + 1;
        final int rank = position.charAt(RANK_INDEX) - '0';
        return new Position(file, rank);
    }
}
