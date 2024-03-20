package view;

import domain.position.ChessFile;
import domain.position.ChessRank;
import dto.BoardStatus;
import dto.PieceInfo;

import java.util.Arrays;
import java.util.StringJoiner;

public class OutputView {

    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printGameStartMessage() {
        StringJoiner startMessageJoiner = new StringJoiner("%n");
        startMessageJoiner.add("> 체스 게임을 시작합니다.");
        startMessageJoiner.add("> 게임 시작 : start");
        startMessageJoiner.add("> 게임 종료 : end");
        startMessageJoiner.add("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println(String.format(startMessageJoiner.toString()));
    }

    public void printChessBoard(final BoardStatus status) {
        String[][] board = createInitBoard();
        applyBoardStatus(status, board);

        StringJoiner boardJoiner = new StringJoiner("%n");
        for (String[] line : board) {
            boardJoiner.add(createBoardLine(line));
        }
        System.out.println(String.format(boardJoiner + System.lineSeparator()));
    }

    private String[][] createInitBoard() {
        String[][] board = new String[ChessRank.maxIndex() + 1][ChessFile.maxIndex() + 1];
        for (int rank = ChessRank.minIndex(); rank <= ChessRank.maxIndex(); rank++) {
            Arrays.fill(board[rank], ".");
        }
        return board;
    }

    private void applyBoardStatus(final BoardStatus status, final String[][] board) {
        for (PieceInfo pieceInfo : status.pieceInfos()) {
            board[ChessRank.maxIndex() - pieceInfo.rankIndex()][pieceInfo.fileIndex()] = pieceInfo.name();
        }
    }

    private StringBuilder createBoardLine(final String[] line) {
        StringBuilder lineBuilder = new StringBuilder();
        for (String point : line) {
            lineBuilder.append(point);
        }
        return lineBuilder;
    }
}
