package console.controller;

import chess.domain.board.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Request {

    private static final int ARGUMENT_SIZE = 2;
    public static final String ARGUMENT_SEPARATOR = " ";
    private final String command;
    private final List<Point> arguments;

    private Request(String command, List<Point> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static Request of(String input) {
        String[] split = input.split(ARGUMENT_SEPARATOR);
        List<Point> arguments = toArguments(split);
        return new Request(split[0], arguments);
    }

    private static List<Point> toArguments(String[] split) {
        return Arrays.stream(split)
                .skip(1)
                .map(Point::of)
                .collect(Collectors.toList());
    }

    public static void validateArgumentSize(List<Point> arguments) {
        if (arguments.size() != ARGUMENT_SIZE) {
            throw new IllegalArgumentException("[ERROR] 출발지와 도착자를 입력해주세요.(move a1 a2)");
        }
    }

    public String getCommand() {
        return command;
    }

    public List<Point> getArguments() {
        return List.copyOf(arguments);
    }
}
