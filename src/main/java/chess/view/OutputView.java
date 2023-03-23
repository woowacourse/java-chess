package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.Command;
import chess.domain.Score;
import chess.domain.piece.info.Team;
import java.util.Arrays;
import java.util.Map;

public class OutputView {

    public void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println(String.format("> 게임 시작 : %s", Command.START.getValue()));
        System.out.println(String.format("> 게임 종료 : %s", Command.END.getValue()));
        System.out.println(String.format("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
            , Command.MOVE.getValue(), Command.MOVE.getValue()));
        System.out.println(String.format("> 게임 점수 확인 : %s", Command.STATUS.getValue()));
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        System.out.println();
        for (String oneFile : chessBoardDto.getBoard()) {
            System.out.println(oneFile);
        }
    }

    public void printGameStatus(Map<Team, Score> scoreBoard, Team winner) {
        Team.RealTeams.forEach((team) -> {
            System.out.println(
                String.format("%s 팀 점수 : %.1f점", team, scoreBoard.get(team).getValue()));
        });
        if (winner.isEmpty()) {
            System.out.println("무승부 상황입니다.");
            return;
        }
        System.out.println(String.format("점수 기준으로 %s팀이 이기고 있습니다.", winner));
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
