package view;

import domain.command.Command;
import domain.position.Position;
import java.util.Scanner;
import view.mapper.CommandInput;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readInitCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String rawCommand = scanner.nextLine();
        return CommandInput.asCommand(rawCommand);
    }

    public Command readCommand() {
        String rawCommand = scanner.next();
        return CommandInput.asCommand(rawCommand);
    }

    public Position readSourcePosition() {
        String rawPosition = scanner.next();
        return Position.generate(rawPosition);
    }

    public Position readTargetPosition() {
        String rawPosition = scanner.next();
        return Position.generate(rawPosition);
    } // TODO: file 만 입력된 경우 예외 처리 (StringIndexOutOfBoundsException)

    public void clean() {
        scanner.nextLine();
    }
}
