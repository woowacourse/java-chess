package view;

import domain.Command;
import domain.File;
import domain.Position;
import domain.Rank;
import view.mapper.CommandMapper;
import view.mapper.FileMapper;
import view.mapper.RankMapper;

import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public Command readInitCommand() {
        System.out.println(String.join(LINE_SEPARATOR, "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"));
        return CommandMapper.toInitCommand(scanner.nextLine());
    }

    public Command readMoveCommand() {
        return CommandMapper.toMoveCommand(scanner.next());
    }

    public Position readPosition() {
        if (scanner.hasNext()) {
            String position = scanner.next();
            if (position.length() != 2) {
                throw new IllegalArgumentException();
            }
            File file = FileMapper.from(position.substring(0, 1));
            Rank rank = RankMapper.from(position.substring(1, 2));

            return Position.valueOf(file, rank);
        }
        throw new IllegalArgumentException();
    }

    public void readNextLine() {
        scanner.nextLine();
    }
}
