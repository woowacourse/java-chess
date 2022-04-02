package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import chess.domain.board.Board;
import chess.domain.GameResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class OutputView {
    private static final String MESSAGE_START = "> 체스 게임을 시작합니다.";
    private static final String MESSAGE_INPUT_START = "> 게임 시작 : start";
    private static final String MESSAGE_INPUT_END = "> 게임 종료 : end";
    private static final String MESSAGE_INPUT_MOVE = "> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3";
    private static final String MESSAGE_INPUT_STATUS = "> 결과 출력 : status";
    private static final String RESULT_FORMAT = "%s : %.1f점%n";
    private static final String MESSAGE_GAME_END = "king 잡았다!";

    public static void announceStart() {
        System.out.println(MESSAGE_START);
        System.out.println(MESSAGE_INPUT_START);
        System.out.println(MESSAGE_INPUT_END);
        System.out.println(MESSAGE_INPUT_MOVE);
        System.out.println(MESSAGE_INPUT_STATUS);
    }

    public static void showBoard(Board board) {
        for (List<Piece> pieces : splitByRank(board.getBoard())) {
            pieces.stream()
                    .map(Piece::getEmoji)
                    .forEach(System.out::print);
            System.out.println();
        }
    }

    private static List<List<Piece>> splitByRank(Map<Square, Piece> board) {
        List<Piece> pieces = new ArrayList<>(board.values());
        List<List<Piece>> splitPieces = Lists.partition(pieces, Rank.values().length);
        return reverse(splitPieces);
    }

    private static List<List<Piece>> reverse(List<List<Piece>> splitPieces) {
        List<List<Piece>> result = new ArrayList<>();
        for (List<Piece> splitPiece : splitPieces) {
            result.add(0, splitPiece);
        }
        return result;
    }

    public static void showScore(GameResult gameResult, Color color) {
        System.out.printf(RESULT_FORMAT, color.getName(), gameResult.calculateScore(color));
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printKingDieMessage() {
        System.out.println(MESSAGE_GAME_END);
    }
}
