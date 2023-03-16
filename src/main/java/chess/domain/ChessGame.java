package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Objects;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameState state;


    private ChessGame(ChessBoard chessBoard, GameState state) {
        this.chessBoard = chessBoard;
        this.state = state;
    }

    public static ChessGame startNewGame(final String command) {
        validateStartCommand(command);
        ChessBoard chessBoard = ChessBoardFactory.create();
        return new ChessGame(chessBoard, GameState.RUNNING);
    }

    private static void validateStartCommand(final String command) {
        if (Objects.isNull(command) || !command.equals("start")) {
            throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
        }
    }

    public boolean isRunning() {
        return state == GameState.RUNNING;
    }

    public void executeCommands(final List<String> commands) {
        Command command = Command.from(commands.get(0));
        if (command == Command.END && command.isAppropriateSize(commands.size())) {
            //게임 종료 로직
            state = GameState.FINISHED;
            return;
        }
        if (command == Command.MOVE && command.isAppropriateSize(commands.size())) {
            executeMove(commands);
            checkGameNotFinished();
            return;
        }
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    private void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            state = GameState.FINISHED;
        }
    }

    private void executeMove(final List<String> commands) {
        String startRank = commands.get(1).substring(0, 1);
        String startFile = commands.get(1).substring(1);
        String endRank = commands.get(2).substring(0, 1);
        String endFile = commands.get(2).substring(1);
        Position startPosition = Position.of(Rank.from(startRank), File.from(startFile));
        Position endPosition = Position.of(Rank.from(endRank), File.from(endFile));
        chessBoard.move(startPosition, endPosition);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
