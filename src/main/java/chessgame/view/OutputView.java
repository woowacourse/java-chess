package chessgame.view;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

import java.util.Collections;
import java.util.Map;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 점수 확인 : status");
    }

    public void printChessBoard(Board board) {
        Map<Point, Piece> chessBoard = board.getBoard();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Point point = Point.of(file, rank);
                printPoint(chessBoard, point);
            }
            System.out.println();
        }
    }

    private void printPoint(Map<Point, Piece> chessBoard, Point point) {
        Piece piece = chessBoard.get(point);
        if (chessBoard.containsKey(point)) {
            printPiece(piece);
        }
        if (!chessBoard.containsKey(point)) {
            System.out.print(".");
        }
    }

    private static void printPiece(Piece piece) {
        if (piece.team() == Team.BLACK) {
            System.out.print(piece.toString().toUpperCase());
        } else if (piece.team() == Team.WHITE) {
            System.out.print(piece.toString().toLowerCase());
        }
    }

    public void printErrorMsg(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printWinner(Team team) {
        System.out.println(team.color() + "팀이 이겼습니다.");
    }

    public void printScore(Map<Team, Double> scoreBoard) {
        for (Team team : scoreBoard.keySet()) {
            System.out.println(team + "팀 " + scoreBoard.get(team) + "점 입니다");
        }
    }

    public void printScoreWinner(Map<Team, Double> scoreBoard) {
        if (scoreBoard.get(Team.BLACK).compareTo(scoreBoard.get(Team.WHITE)) == 0) {
            System.out.println("비기고 있습니다.");
            return;
        }
        System.out.println(Collections.max(scoreBoard.keySet()) + "팀이 이기고 있습니다.");
    }

    public void printSetGameMessage() {
        System.out.println("호출할 체스게임 이름을 입력해주세요. (입력한 체스게임의 이어하기 데이터가 존재하지 않을 경우 새로운 게임이 시작됩니다.)");
    }
}
