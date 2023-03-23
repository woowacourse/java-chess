package chessgame.domain;

import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final int SOURCE = 0;
    private static final int TARGET = 1;

    private final String command;
    private static List<Point> points = new ArrayList<>();

    private Command(String command) {
        this.command = command;
    }

    public static Command of(String command) {
        return validateCommand(command);
    }

    private static Command validateCommand(String command) {
        if (command.isBlank()) {
            throw new IllegalArgumentException("빈값을 입력하면 안됩니다.");
        }
        if (START.equals(command) || END.equals(command) || STATUS.equals(command)) {
            return new Command(command);
        }
        return validateMove(command);
    }

    private static Command validateMove(String command) {
        String[] moveCommand = command.split(" ");
        if (moveCommand.length == 3 && MOVE.equals(moveCommand[0])) {
            validatePoint(moveCommand[1], moveCommand[2]);
            points = makePoints(command);
            return new Command(command);
        }
        if (MOVE.equals(moveCommand[0])) {
            throw new IllegalArgumentException("move source target을 정확히 입력해주세요.");
        }
        throw new IllegalArgumentException("start, move, end만 입력 가능합니다.");
    }

    private static void validatePoint(String source, String target) {
        String pattern = "[a-h][1-8]";
        if (!(source.matches(pattern) && target.matches(pattern))) {
            throw new IllegalArgumentException("이동 좌표는 a-h 1-8 로 입력하여야합니다.");
        }
    }

    public boolean isMove() {
        return "move".equals(command.split(" ")[0]);
    }

    public boolean isStart() {
        return START.equals(command);
    }

    public boolean isStatus() {
        return STATUS.equals(command);
    }

    public boolean isEnd() {
        return END.equals(command);
    }

    private static List<Point> makePoints(String command) {
        List<Point> points = new ArrayList<>();
        String[] moveCommand = command.split(" ");
        points.add(Point.of(File.findFile(moveCommand[1].charAt(0)),
                Rank.findRank(Integer.parseInt(moveCommand[1].substring(1)))));
        points.add(Point.of(File.findFile(moveCommand[2].charAt(0)),
                Rank.findRank(Integer.parseInt(moveCommand[2].substring(1)))));
        return points;
    }

    public Point getSourcePoint() {
        return points.get(SOURCE);
    }

    public Point getTargetPoint() {
        return points.get(TARGET);
    }
}
