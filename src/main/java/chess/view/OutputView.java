package chess.view;

import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static void printBeginGame() {
        System.out.println(">체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        Map<Square, Piece> gameBoard = chessBoard.getChessBoard();
        IntStream.rangeClosed(1, 8)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(rank -> printOneRow(gameBoard, Rank.of(rank)));
    }

    private static void printOneRow(Map<Square, Piece> gameBoard, Rank rank) {
        IntStream.rangeClosed(1, 8).forEach(file -> {
            Square currentSquare = Square.of(File.of(file), rank);
            if (gameBoard.containsKey(currentSquare)) {
                System.out.print(gameBoard.get(currentSquare));
            }
            if (!gameBoard.containsKey(currentSquare)) {
                System.out.print(".");
            }
        });
        System.out.println();
    }

    public static void printAlreadyStarted() {
        System.out.println("이미 시작하였습니다, 다른 명령어를 입력해주세요");
    }

    public static void printCanNotMove() {
        System.out.println("움직일 수 없습니다");
    }

    public static void printWinner(Color color) {
        System.out.println(color.getName() + "가 이겼습니다");
    }

    public static void printScore(TeamScore teamScore) {
        Map<Color, Double> currentScore = teamScore.getTeamScore();
        currentScore.keySet()
                .forEach(color -> System.out.println(color.getName() + "의 점수는 " + currentScore.get(color) + "입니다"));
    }

    public static void printWinners(List<Color> winners) {
        System.out.print("우승자는 ");
        System.out.print(winners.stream()
                .map(Color::getName)
                .collect(Collectors.joining(", ")));
        System.out.println("입니다.");
    }
}

