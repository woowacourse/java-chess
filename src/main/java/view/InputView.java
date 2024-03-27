package view;

import domain.Team;
import domain.player.PlayerName;

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
                > 게임 전적 검색 : record
                > 프로그램 종료 : quit%n""");

        return SCANNER.nextLine();
    }

    public String readGameCommand(final Team team, final PlayerName playerName) {
        System.out.printf("%n%s팀 %s의 차례입니다.%n", team.name(), playerName.getName());

        return SCANNER.nextLine();
    }

    public int readContinueGame(final List<Integer> runningGame) {
        System.out.printf("진행 중인 게임 목록 : %s%n게임 ID를 입력하세요.", runningGame.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

        return readGameId();
    }

    private static int readGameId() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (final NumberFormatException e) {
            System.out.println("숫자로 입력 바랍니다.");
            return readGameId();
        }
    }

    public String readTeamPlayerName(final Team team) {
        System.out.print(team.name() + "팀의 플레이어 이름 : ");

        return SCANNER.nextLine();
    }

    public String readPlayerName() {
        System.out.println("이름을 입력 하세요.");

        return SCANNER.nextLine();
    }
}
