package techcourse.fp.chess.view;

import java.util.List;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.dto.response.BoardResponse;
import techcourse.fp.chess.dto.response.ChessGameInfo;
import techcourse.fp.chess.dto.response.ScoreResponse;

public final class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 가져오기 : load");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 점수 확인 : status");
    }

    public void printBoard(final BoardResponse boardResponse) {
        int count = 0;

        for (String name : boardResponse.getNames()) {
            System.out.print(name);
            count++;
            if (count % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void printSaveSuccessMessage() {
        System.out.println("게임이 성공적으로 저장됐습니다.");
        System.out.println("게임을 진행하여 주세요.");
    }

    public void printEndMessage() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printErrorMessage(final String exceptionMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + exceptionMessage);
    }

    public void printWinningMessage(final Color winner) {
        System.out.println(winner +" 이(가) 승리하였습니다!");
    }

    public void printStatus(final List<ScoreResponse> scores) {
        for (ScoreResponse response : scores) {
            System.out.println(response.getColor() +" " + response.getScore() + "점");
        }
    }

    public void printGameInfos(final List<ChessGameInfo> info) {
        System.out.println("id - 게임 이름                              - 차례      -  저장일자");
        for (ChessGameInfo chessGameInfo : info) {
            System.out.printf("%s - %-20s                - %s      -  %s", chessGameInfo.getId(), chessGameInfo.getName(),
                    chessGameInfo.getTurn(), chessGameInfo.getCreateTime());
            System.out.println();
        }

        System.out.println("실행하길 원하는 게임의 id를 입력해주세요");
    }
}
