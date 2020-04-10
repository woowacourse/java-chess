package chess.view;

import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.PositionFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Map;

public class OutputView {
    private static final char MIN_Y_POINT = '1';
    private static final char MAX_Y_POINT = '8';
    private static final char MIN_X_POINT = 'a';
    private static final char MAX_X_POINT = 'h';

    public static void printGameCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.get();

        for (char yPoint = MAX_Y_POINT; yPoint >= MIN_Y_POINT; yPoint--) {
            for (char xPoint = MIN_X_POINT; xPoint <= MAX_X_POINT; xPoint++) {
                if (xPoint == MAX_X_POINT) {
                    System.out.println(chessBoard.get(PositionFactory.of(xPoint, yPoint)).getName() + "   " + yPoint);
                } else {
                    System.out.print(chessBoard.get(PositionFactory.of(xPoint, yPoint)).getName());
                }
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println("abcdefgh");
    }

    public static void printTurn(PieceColor team) {
        System.out.println(team.getName() + " 플레이어가 움직일 차례입니다.");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStatusMessage() {
        System.out.println("> 킹이 잡혔으므로 게임을 종료합니다.");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 승패와 점수 확인 : status");
    }

    public static void printResult(GameResult gameResult) {
        System.out.println("<승자> " + gameResult.getWinner() + " <패자> " + gameResult.getLoser());
        System.out.println(String.format("점수 : White - %.1f점 / Black - %.1f점",
                                         gameResult.getAliveWhitePieceScoreSum(),
                                         gameResult.getAliveBlackPieceScoreSum()));
    }
}
