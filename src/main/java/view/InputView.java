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

    public Command readCommand() {
        System.out.println(String.join(LINE_SEPARATOR, "체스 게임을 시작합니다.", "게임 시작은 start, 종료는 end 명령을 입력하세요."));
        return CommandMapper.from(scanner.next());
    }

    public Position readPosition() {
        if (scanner.hasNext()) {
            String position = scanner.next();
            if (position.length() != 2) {
                throw new IllegalArgumentException();
            }
            File file = FileMapper.from(position.substring(0, 1));
            Rank rank = RankMapper.from(position.substring(1, 2));

            return new Position(file, rank);
        }
        throw new IllegalArgumentException();
    }

    public void readNextLine() {
        scanner.nextLine();
    }
}
