package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.board.Square;
import chess.domain.game.Result;
import chess.domain.piece.Color;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static chess.domain.board.ChessBoard.BOARD_SIZE;
import static chess.domain.board.ChessBoard.LAST_BOARD_INDEX;

public class OutputView {
    public static final String RESULT_FORMAT = "%s: %.1f점 - %s%n";

    private OutputView() {
    }

    public static void gameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료: end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        List<Square> squares = chessBoard.getChessBoard();
        for (int i = 0; i < squares.size(); i++) {
            System.out.print(squares.get(i).getName());
            if (i % BOARD_SIZE == LAST_BOARD_INDEX) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void printResult(Result result) {
        Map<Color, Double> score = result.getResult();
        Map<Color, String> winOrLose = result.getWinOrLose();
        System.out.printf(RESULT_FORMAT, Color.BLACK.name(), score.get(Color.BLACK),
            winOrLose.get(Color.BLACK));
        System.out.printf(RESULT_FORMAT, Color.WHITE.name(), score.get(Color.WHITE),
            winOrLose.get(Color.WHITE));
    }
}
