package chess.view;

public class ResultView {

    public void printScore(final double upperTeamScore, final double lowerTeamScore) {
        System.out.println();
        System.out.println("대문자 팀의 남은 체스 점수 총 합은 : " + upperTeamScore + "점 입니다.");
        System.out.println("소문자 팀의 남은 체스 점수 총 합은 : " + lowerTeamScore + "점 입니다.");
    }

    public void printWinner(final double upperTeamScore, final double lowerTeamScore) {
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
}
