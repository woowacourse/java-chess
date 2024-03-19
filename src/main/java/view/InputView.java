package view;

import domain.Command;
import view.mapper.CommandMapper;

import java.util.Scanner;

public class InputView {

    private final String LINE_SEPARATOR = System.lineSeparator();

    private Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public Command readCommand() {
        System.out.println(String.join(LINE_SEPARATOR, "체스 게임을 시작합니다.", "게임 시작은 start, 종료는 end 명령을 입력하세요."));
        return CommandMapper.from(scanner.nextLine());
    }
}
