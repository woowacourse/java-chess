package chess.view;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printGameInformation() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 점수 : status\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(Map<Square, Piece> chessBoard) {
        for (Rank rank : Rank.values()) {
            printOneChessBoardRank(chessBoard, rank);
        }
    }

    private static void printOneChessBoardRank(Map<Square, Piece> chessBoard, Rank rank) {
        for (File file : File.values()) {
            if (chessBoard.containsKey(Square.of(file.getName(), rank.getName()))) {
                System.out.print(chessBoard.get(Square.of(file.getName(), rank.getName())).getLetter());
                continue;
            }
            System.out.print(".");
        }
        System.out.println();
    }

    public static void printInputError() {
        System.out.println("입력이 잘못되었습니다. 바르게 입력해주세요.");
    }

    public static void printStartGameFirstMessage() {
        System.out.println("게임이 시작되지 않았습니다. 먼저 시작해주세요.");
    }

    public static void printAlreadyGameStartedMessage() {
        System.out.println("이미 게임이 시작되었습니다.");
    }

    public static void printCanNotMoveMessage() {
        System.out.println("이동할 수 없는 위치입니다.");
    }

    public static void printStatus(Map<Color, Double> teamScore) {
        teamScore.keySet()
                .forEach(color -> System.out.println(color + "의 점수는 " + teamScore.get(color) + "점 입니다."));
    }

    public static void printWinner(List<Color> winner) {
        System.out.print("우승 팀은 ");
        winner.forEach(team -> System.out.print(team.getName() + " "));
        System.out.println("입니다.");
    }
}
