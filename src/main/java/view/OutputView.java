package view;

import dao.GameDto;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Score;
import domain.game.Side;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final int ID_MAXIMUM_LENGTH = 8;
    private static final int USERNAME_MAXIMUM_LENGTH = 20;
    private static final String SPACE = " ";
    private static final String DIVEDE_PREFIX = "|";

    public void printChessBoard(Map<Position, String> chessBoardOfForPrint) {
        Rank currentRank = Rank.EIGHT;
        for (Map.Entry<Position, String> positionPieceEntry : chessBoardOfForPrint.entrySet()) {
            currentRank = moveNextRankIfLast(currentRank, positionPieceEntry);
            System.out.print(positionPieceEntry.getValue());
        }
        System.out.println();
        System.out.println();
    }

    private static Rank moveNextRankIfLast(Rank currentRank, Entry<Position, String> positionPieceEntry) {
        if (!currentRank.equals(positionPieceEntry.getKey().getRank())) {
            currentRank = currentRank.getPrevious();
            System.out.println();
        }
        return currentRank;
    }

    public void printGameGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 찾기 : search");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 현황 : status");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void printSideOfTurn(Side side) {
        System.out.println(side + " 진영의 말을 움직이세요");
    }

    public void printServerErrorMessage() {
        System.out.println("서버 내부 오류가 발생했습니다. 개발자에게 문의하세요.");
    }

    public void printGameResult(String gameStatusText, Score whiteScore, Score blackScore) {
        System.out.println("게임 상태 : " + gameStatusText);
        System.out.println("White 점수 : " + whiteScore.getNumber());
        System.out.println("Black 점수 : " + blackScore.getNumber());
        System.out.println();
    }

    public void printGamesOfUser(List<GameDto> gameDtos) {
        System.out.println("ID     |사용자 이름        | 제목");
        for (GameDto gameDto : gameDtos) {
            printGamId(gameDto);
            printUserName(gameDto);
            printTitle(gameDto);
        }
        System.out.println();
    }

    private static void printGamId(GameDto gameDto) {
        String gameId = gameDto.getGameId();
        int lengthOfGameId = gameId.length();
        System.out.print(gameId);
        System.out.print(SPACE.repeat(ID_MAXIMUM_LENGTH - lengthOfGameId));
    }

    private static void printUserName(GameDto gameDto) {
        String userName = gameDto.getUserName();
        int lengthOfUserName = userName.length();
        System.out.print(userName);
        System.out.print(SPACE.repeat(USERNAME_MAXIMUM_LENGTH - lengthOfUserName));
    }

    private static void printTitle(GameDto gameDto) {
        System.out.println(gameDto.getTitle());
    }
}
