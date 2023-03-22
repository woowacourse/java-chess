package chess.view;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
        }
        return instance;
    }

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, String> pieceNames) {
        List<List<String>> board = initBoard();

        for (Position position : pieceNames.keySet()) {
            List<Integer> coordinate = position.getCoordinate();
            List<String> oneLine = board.get(coordinate.get(0));
            oneLine.set(coordinate.get(1), pieceNames.get(position));
        }

        for (int i = 0; i < 8; i++) {
            for (String name : board.get(i)) {
                System.out.print(name);
            }
            System.out.println();
        }
        System.out.println();
    }

    private List<List<String>> initBoard() {
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(initOneLine());
        }
        return board;
    }

    private List<String> initOneLine() {
        List<String> oneLine = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            oneLine.add(".");
        }
        return oneLine;
    }
}
