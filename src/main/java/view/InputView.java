package view;

import domain.Team;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String readStartOption() {
        System.out.printf("""
                > 체스 게임을 시작합니다.
                > 게임 새로 시작 : start
                > 게임 이어하기 : continue
                > 프로그램 종료 : quit%n""");

        return SCANNER.nextLine();
    }

    public String readGameCommand(final Team team) {
        System.out.printf("%s팀 차례입니다.%n", team.name());

        return SCANNER.nextLine();
    }

    public int readContinueGame(final List<Integer> runningGame) {
        System.out.printf("진행 중인 게임 목록 : %s%n", runningGame.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 게임 ID 입니다");
        }
    }
}
