package chess.view;

import chess.dto.GameScoreResultDto;

public class ResultView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printScore(final GameScoreResultDto gameScoreResultDto) {
        System.out.println();
        System.out.println("대문자 팀의 남은 체스 점수 총 합은 : " + gameScoreResultDto.getUpperTeamScore() + "점 입니다.");
        System.out.println("소문자 팀의 남은 체스 점수 총 합은 : " + gameScoreResultDto.getLowerTeamScore() + "점 입니다.");
    }

    public void printWinner(final GameScoreResultDto gameScoreResultDto) {
        double upperTeamScore = gameScoreResultDto.getUpperTeamScore();
        double lowerTeamScore = gameScoreResultDto.getLowerTeamScore();

        if (upperTeamScore == lowerTeamScore) {
            System.out.println("체스 우승 결과는 : 무승부");
        }

        if (upperTeamScore > lowerTeamScore) {
            System.out.println("체스 우승 결과는 : 대문자팀");
        }

        if (upperTeamScore < lowerTeamScore) {
            System.out.println("체스 우승 결과는 : 소문자팀");
        }
    }

    public void printGameEnd() {
        System.out.println(LINE_SEPARATOR + "프로그램 종료");
    }

    public void printWinnerIsUpperTeam() {
        System.out.println(LINE_SEPARATOR + "소문자팀 킹이 잡혀서, 대문자팀이 체스 게임에서 승리하였습니다.");
    }

    public void printWinnerIsLowerTeam() {
        System.out.println(LINE_SEPARATOR + "대문자팀 킹이 잡혀서, 소문자팀이 체스 게임에서 승리하였습니다.");
    }

    public void printGameEndWithSaving() {
        System.out.println("게임을 중단했습니다. 현재 게임은 저장됩니다.");
    }

    public void printGameEndWithDeleting() {
        System.out.println("게임이 끝났습니다. 기존 게임은 삭제됩니다.");
    }
}
