package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum InGameDecision {
    MOVE("move", (board, multiArguments) -> {
        validateMultiArguments(multiArguments);
        Position source = new Position(multiArguments.get(1));
        Position destination = new Position(multiArguments.get(2));
        board.movePiece(source, destination);
    }),
    STATUS("status", (board, multiArguments) -> {
        OutputView.printScore(board);
        OutputView.printTeamWithHigherScore(board);
    }),
    END("end", (board, multiArguments) -> {
    });

    private static final int COMMAND_ARGUMENTS_SIZE = 3;
    private static final String NOT_ENOUGH_ARGUMENTS = "명령에 인자가 부족합니다.";
    private static final String WRONG_COMMAND_DIALOGUE = "올바른 명령을 입력해 주십시오.";
    private final String command;
    private final BiConsumer<Board, List<String>> action;

    InGameDecision(String command, BiConsumer<Board, List<String>> action) {
        this.command = command;
        this.action = action;
    }

    private static void validateMultiArguments(List<String> multiArguments) {
        if (multiArguments.size() != COMMAND_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(NOT_ENOUGH_ARGUMENTS);
        }
    }

    public static InGameDecision of(Board board, String decision) {
        List<String> multiArguments = Arrays.asList(decision.split(" "));
        return Arrays.stream(values())
                .filter(c -> c.command.equals(multiArguments.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_DIALOGUE));
    }

    public BiConsumer<Board, List<String>> getAction() {
        return action;
    }
}
