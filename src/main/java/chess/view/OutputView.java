package chess.view;

import chess.controller.ChessBoardDto;
import chess.controller.Command;
import chess.domain.Score;
import chess.domain.piece.info.Team;
import java.util.Arrays;
import java.util.Map;

public class OutputView {

    public void printReadyMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }
    public void printGameGuide() {
        System.out.println(String.format("> 게임 시작 : %s", Command.START.getValue()));
        System.out.println(String.format("> 이전 게임 조회 : %s", Command.LOAD.getValue()));
        System.out.println(String.format("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
            , Command.MOVE.getValue(), Command.MOVE.getValue()));
        System.out.println(String.format("> 게임 점수 확인 : %s", Command.STATUS.getValue()));
        System.out.println(String.format("> 게임 종료 : %s", Command.END.getValue()));
    }

    public void printLoadGuide() {
        System.out.println("> 이전에 하던 게임 기록이 남아있습니다. 이어하시겠습니까?");
        System.out.println(String.format("> 이어하기 : %s", Command.CONTINUE.getValue()));
        System.out.println(String.format("> 돌아가기 : %s", Command.CANCEL.getValue()));
    }

    public void printCannotLoadMessage() {
        System.out.println("> 이전에 하던 게임 기록이 없습니다.");
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

    public void printWinner(Team winner){
        System.out.println(String.format("%s팀이 승리하였습니다.", winner));
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
