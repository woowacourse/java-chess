package chess.view;

import chess.domain.PieceDto;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final int RANK_INDEX = 0;
    public static final int FILE_INDEX = 1;
    public static final int BLACK_SOCRE_INDEX = 0;
    public static final int WHITE_SCORE_INDEX = 1;
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

    public void printBoard(Map<Position, PieceDto> pieces) {
        List<List<String>> board = initBoard();

        for (Position position : pieces.keySet()) {
            List<Integer> coordinate = position.getCoordinate();
            List<String> oneLine = board.get(coordinate.get(RANK_INDEX));
            PieceDto pieceDto = pieces.get(position);

            setPrintingSymbol(coordinate, oneLine, pieceDto);
        }

        printPieces(board);
    }

    public void printStatus(List<Double> score) {
        double blackScore = score.get(BLACK_SOCRE_INDEX);
        double whiteScore = score.get(WHITE_SCORE_INDEX);

        System.out.println("흰색 진영 점수:" + whiteScore + " 검은색 진영 점수:" + blackScore);
        double diff = whiteScore - blackScore;
        if (diff > 0) {
            System.out.println("흰색 진영이 " + diff + "점 앞서고 있습니다.");
        }
        if (diff < 0) {
            System.out.println("검은색 진영이 " + -diff + "점 앞서고 있습니다.");
        }
        System.out.println();
    }

    private void setPrintingSymbol(List<Integer> coordinate, List<String> oneLine, PieceDto pieceDto) {
        PieceSymbol pieceSymbol = PieceSymbol.getByPieceType(pieceDto.getPieceType());
        oneLine.set(coordinate.get(FILE_INDEX), pieceSymbol.getPrintingSymbol(pieceDto.getColor()));
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

    private void printPieces(List<List<String>> board) {
        for (int i = 0; i < 8; i++) {
            for (String name : board.get(i)) {
                System.out.print(name);
            }
            System.out.println();
        }
        System.out.println();
    }
}
