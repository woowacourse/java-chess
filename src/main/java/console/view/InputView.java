package console.view;

import static chess.position.File.*;
import static chess.position.Rank.*;

import chess.position.*;
import console.command.*;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final Map<String, Rank> RANKS = Map.of(
        "1", ONE, "2", TWO, "3", THREE, "4", FOUR, "5", FIVE, "6", SIX, "7", SEVEN, "8", EIGHT
    );
    private static final Map<String, File> FILES = Map.of(
        "a", A, "b", B, "c", C, "d", D, "e", E, "f", F, "g", G, "h", H
    );
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Command inputStartOrEndCommand() {
        String command = SCANNER.nextLine();
        if (command.equals("start")) {
            return new Command(CommandType.START);
        }
        if (command.equals("end")) {
            return new Command(CommandType.END);
        }
        throw new IllegalArgumentException("잘못된 명령어 입니다.");
    }

    public static Command inputCommand() {
        String command = SCANNER.nextLine();
        if (command.equals("end")) {
            return new Command(CommandType.END);
        }
        if (command.equals("status")) {
            return new Command(CommandType.STATUS);
        }
        if (command.matches("^move [a-h][1-8] [a-h][1-8]$")) {
            String from = command.split(" ")[1];
            String to = command.split(" ")[2];
            return new Command(CommandType.MOVE, createPosition(from), createPosition(to));
        }
        throw new IllegalArgumentException("잘못된 명령어 입니다.");
    }

    private static Position createPosition(String position) {
        String file = position.substring(0, 1);
        String rank = position.substring(1, 2);
        return new Position(FILES.get(file), RANKS.get(rank));
    }
}
