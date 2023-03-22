package chess.controller;

import static chess.controller.ExecuteState.END;
import static chess.controller.ExecuteState.INIT;
import static chess.controller.ExecuteState.MOVE;
import static chess.controller.ExecuteState.START;
import static chess.controller.ExecuteState.STATUS;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.ChessGame;
import chess.domain.board.Square;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;
import chess.view.dto.ChessStatusDto;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGame chessGame;
    private ExecuteState executeState = INIT;
    private final Map<ExecuteState, Consumer<List<String>>> actions = Map.of(
            START, this::start,
            STATUS, this::status,
            MOVE, this::move,
            END, this::end
    );

    public ChessController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printStartMessage();
        while (executeState != END) {
            playChessGame();
        }
    }

    private void playChessGame() {
        try {
            final List<String> commands = inputView.readExecuteCommands();
            final ExecuteState executeState = readExecuteState(commands);
            actions.get(executeState).accept(commands);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private ExecuteState readExecuteState(final List<String> commands) {
        validateCommands(commands);
        return ExecuteState.from(commands.get(0));
    }

    private void validateCommands(final List<String> commands) {
        if (commands.size() == 0) {
            throw new IllegalArgumentException("게임 실행을 위해서는 명령 입력이 필요합니다.");
        }
    }

    private void start(final List<String> commands) {
        validateForNotMove(commands);
        validateInit();
        executeState = START;
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
    }

    private void status(final List<String> commands) {
        validateForNotMove(commands);
        validateStart();
        outputView.printChessStatus(
                ChessStatusDto.of(chessGame.calculateScoreOfColor(BLACK), chessGame.calculateScoreOfColor(WHITE)));
    }

    private void move(final List<String> commands) {
        validateForMove(commands);
        validateStart();
        chessGame.move(Square.from(commands.get(1)), Square.from(commands.get(2)));
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
    }

    private void end(final List<String> commands) {
        validateForNotMove(commands);
        executeState = END;
    }

    private static void validateForNotMove(final List<String> commands) {
        if (commands.size() != 1) {
            throw new IllegalArgumentException("실행 명령만 정확히 입력이 필요합니다.");
        }
    }

    private void validateInit() {
        if (executeState != INIT) {
            throw new IllegalArgumentException("게임 시작은 처음 상태일때만 가능합니다.");
        }
    }

    private void validateStart() {
        if (executeState != START) {
            throw new IllegalArgumentException("게임 진행중일때만 가능합니다.");
        }
    }

    private static void validateForMove(final List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("이동을 위해서는 예시와 같이 입력이 필요합니다. 예) move b2 b3");
        }
    }
}
