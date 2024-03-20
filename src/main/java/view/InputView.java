package view;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import java.util.Scanner;
import view.dto.MovePositionDto;

public class InputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    private final Scanner scanner = new Scanner(System.in);

    public boolean readStartCommand() {
        String command = scanner.nextLine();
        if (command.equals(START_COMMAND)) {
            return true;
        }
        if (command.equals(END_COMMAND)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    public MovePositionDto readMoveCommand() {
        String command = scanner.nextLine();
        String[] split = command.split(" ");
        if (split[0].equals(MOVE_COMMAND)) {
            File sourceFile = FileResolver.resolveFile(Character.toString(split[1].charAt(0)));
            Rank sourceRank = RankResolver.resolveRank(Character.toString(split[1].charAt(1)));
            Position sourcePosition = new Position(sourceFile, sourceRank);
            File targetFile = FileResolver.resolveFile(Character.toString(split[2].charAt(0)));
            Rank targetRank = RankResolver.resolveRank(Character.toString(split[2].charAt(1)));
            Position targetPosition = new Position(targetFile, targetRank);

            return new MovePositionDto(sourcePosition, targetPosition);
        }
        throw new IllegalArgumentException("");
    }
}
