package chess.domain.command;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Command {
    START("start", unsupportedExecution()),
    MOVE("move", ((chessBoard, commandRequest) -> {
        Coordinate current = commandRequest.getCurrentCoordinate();
        Coordinate destination = commandRequest.getDestinationCoordinate();
        TeamType teamType = commandRequest.getTeamType();
        chessBoard.move(current, destination, teamType);
        return Result.getEmptyResult();
    })),
    STATUS("status", ((chessBoard, commandRequest) -> chessBoard.calculateScores())),
    END("end", unsupportedExecution());

    private final String signature;
    private final BiFunction<ChessBoard, CommandRequest, Result> execution;

    Command(String signature, BiFunction<ChessBoard, CommandRequest, Result> execution) {
        this.signature = signature;
        this.execution = execution;
    }

    private static BiFunction<ChessBoard, CommandRequest, Result> unsupportedExecution() {
        return ((chessBoard, commandRequest) -> {
            throw new UnsupportedOperationException();
        });
    }

    public static Command findCommand(String commandInput) {
        return Arrays.stream(Command.values())
                .filter(command -> command.hasSameSignature(commandInput))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력했습니다."));
    }

    private boolean hasSameSignature(String value) {
        return this.signature.equals(value);
    }

    public Result execute(ChessBoard chessBoard, CommandRequest commandRequest) {
        return execution.apply(chessBoard, commandRequest);
    }
}
