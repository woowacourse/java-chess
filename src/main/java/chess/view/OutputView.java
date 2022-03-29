package chess.view;

import chess.model.position.File;
import chess.model.Team;
import chess.model.position.Position;
import chess.model.position.Rank;
import chess.model.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.get(Position.of(rank, file));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }

    public void printScores(Map<Team, Double> calculateScore) {
        for (Team team : calculateScore.keySet()) {
            if (team == Team.BLACK) {
                System.out.printf("검은색: %f", calculateScore.get(team));
            }
            if (team == Team.WHITE) {
                System.out.printf("흰색: %f", calculateScore.get(team));
            }
        }
        printWinner(calculateScore);
    }

    private void printWinner(Map<Team, Double> calculateScore) {
        if (calculateScore.get(Team.BLACK) > calculateScore.get(Team.WHITE)) {
            System.out.println("블랙 승");
            return;
        }
        System.out.println("흰색 승");
    }
}
