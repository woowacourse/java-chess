package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.command.CommandType;
import chess.domain.Team;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println(String.format("> 게임 시작 : %s", CommandType.START));
        System.out.println(String.format("> 게임 종료 : %s", CommandType.END));
        System.out.println(String.format("> 게임 이동 : %s SOURCE위치 TARGET위치 - 예. %s b2 b3"
                , CommandType.MOVE, CommandType.MOVE));
        System.out.println(String.format("> 게임 상태 : %s", CommandType.STATUS));
    }

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        chessBoardDto.getBoard().forEach(System.out::println);
    }

    public static void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printScore(final Map<Team, Double> scores) {
        scores.forEach((key, value) -> System.out.println(key.name() + ":" + value));
    }

    public static void printWinningTeam(final Team winningTeam) {
        if (winningTeam == Team.NONE) {
            System.out.println("우승자가 없습니다.");
            return;
        }
        System.out.println("우승자: " + winningTeam);
    }

    public static void printRoomList(final List<Integer> roomNumbers) {
        System.out.println(roomNumbers.size() + "개의 방이 존재합니다.");
        System.out.println("방 번호 목록  ");
        roomNumbers.forEach(System.out::println);
    }
}
