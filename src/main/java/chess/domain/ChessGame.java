package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Objects;

public class ChessGame {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DEST_POSITION_INDEX = 2;
    private static final int FILE_RANK_DIVIDING_INDEX = 1;

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
        if (Objects.isNull(command) || (Command.from(command) != Command.START)) {
            throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
        }
    }

    public boolean isRunning() {
        return state == GameState.RUNNING;
    }

    public void executeCommand(final List<String> commandAndParameters) {
        Command command = Command.from(commandAndParameters.get(COMMAND_INDEX));
        validateCommandSize(command, commandAndParameters.size());
        if (command == Command.END) {
            state = GameState.FINISHED;
            return;
        }
        if (command == Command.MOVE) {
            executeMove(commandAndParameters);
            checkGameNotFinished();
        }
    }

    private void validateCommandSize(final Command command, final int size) {
        if (!command.isAppropriateSize(size)) {
            throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
        }
    }

    private void executeMove(final List<String> commandAndParameters) {
        String startRank = commandAndParameters.get(SOURCE_POSITION_INDEX)
                .substring(0, FILE_RANK_DIVIDING_INDEX);
        String startFile = commandAndParameters.get(SOURCE_POSITION_INDEX)
                .substring(FILE_RANK_DIVIDING_INDEX);
        String endRank = commandAndParameters.get(DEST_POSITION_INDEX)
                .substring(0, FILE_RANK_DIVIDING_INDEX);
        String endFile = commandAndParameters.get(DEST_POSITION_INDEX)
                .substring(FILE_RANK_DIVIDING_INDEX);
        Position source = Position.of(Rank.from(startRank), File.from(Integer.parseInt(startFile)));
        Position destination = Position.of(Rank.from(endRank), File.from(Integer.parseInt(endFile)));
        chessBoard.move(source, destination);
    }

    private void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            state = GameState.FINISHED;
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
