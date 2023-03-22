package chessgame.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Points;
import chessgame.domain.point.Rank;

public class Command {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    private final String command;
    private final Points points;

    private Command(String command, Points points) {
        this.command = command;
        this.points = points;
    }

    public static Command of(String command) {
        return validateStartOrEnd(command);
    }

    private static Command validateStartOrEnd(String command) {
        if (command.isBlank()) {
            throw new IllegalArgumentException("빈값을 입력하면 안됩니다.");
        }
        if (START.equals(command) || END.equals(command)) {
            return new Command(command, new Points(Collections.emptyList()));
        }
        return validateMove(command);
    }

    private static Command validateMove(String command) {
        String[] moveCommand = command.split(" ");
        if (moveCommand.length == 3 && MOVE.equals(moveCommand[0])) {
            List<String> points = List.of(moveCommand[1], moveCommand[2]);
            validatePoint(points);
            return new Command(moveCommand[0], makePoints(points));
        }
        if (MOVE.equals(moveCommand[0])) {
            throw new IllegalArgumentException("move source target을 정확히 입력해주세요");
        }
        throw new IllegalArgumentException("start, move, end만 입력 가능합니다.");
    }

    private static void validatePoint(List<String> points) {
        boolean isPoint = points.stream().anyMatch(point -> point.length() != 2);
        if (isPoint) {
            throw new IllegalArgumentException("좌표를 정확하게 입력하세요");
        }
    }

    private static Points makePoints(List<String> inputs) {
        List<Point> points = new ArrayList<>();
        points.add(Point.of(File.find(inputs.get(0).charAt(0)),
            Rank.find(Integer.parseInt(inputs.get(0).substring(1)))));
        points.add(Point.of(File.find(inputs.get(1).charAt(0)),
            Rank.find(Integer.parseInt(inputs.get(1).substring(1)))));
        return new Points(points);
    }

    public Points points() {
        return points;
    }

    public boolean isMove() {
        return MOVE.equals(command);
    }

    public boolean isNotStart() {
        return !START.equals(command);
    }

    public boolean isEnd() {
        return END.equals(command);
    }
}
