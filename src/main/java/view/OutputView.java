package view;

import domain.piece.ChessFile;
import domain.piece.ChessRank;
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
        System.out.println(String.format("체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요."));
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
            board[pieceInfo.rankIndex()][pieceInfo.fileIndex()] = pieceInfo.name();
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
