package chess.view;

import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.dto.BoardStatus;
import chess.dto.PieceInfo;
import chess.view.matcher.PieceNameMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printGameStartMessage() {
        StringJoiner startMessageJoiner = new StringJoiner(System.lineSeparator());
        startMessageJoiner.add("> 체스 게임을 시작합니다.");
        startMessageJoiner.add("> 게임 시작 : start");
        startMessageJoiner.add("> 게임 종료 : end");
        startMessageJoiner.add("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        System.out.println(startMessageJoiner);
    }

    public void printChessBoard(final BoardStatus status) {
        List<List<String>> board = createInitBoard();
        applyBoardStatus(status, board);

        StringJoiner boardJoiner = new StringJoiner(System.lineSeparator());
        for (List<String> line : board) {
            boardJoiner.add(createBoardLine(line));
        }
        System.out.println(boardJoiner + System.lineSeparator());
    }

    private List<List<String>> createInitBoard() {
        List<List<String>> board = new ArrayList<>();

        for (int rank = ChessRank.minIndex(); rank <= ChessRank.maxIndex(); rank++) {
            ArrayList<String> boardLine = new ArrayList<>(Collections.nCopies(ChessFile.maxIndex() + 1, "."));
            board.add(boardLine);
        }
        return board;
    }

    private void applyBoardStatus(final BoardStatus status, final List<List<String>> board) {
        for (PieceInfo pieceInfo : status.pieceInfos()) {
            int rankIndex = ChessRank.maxIndex() - pieceInfo.rankIndex();
            int fileIndex = pieceInfo.fileIndex();
            board.get(rankIndex).set(fileIndex, PieceNameMatcher.matchByType(pieceInfo.type()));
        }
    }

    private StringBuilder createBoardLine(final List<String> line) {
        StringBuilder lineBuilder = new StringBuilder();
        for (final String point : line) {
            lineBuilder.append(point);
        }
        return lineBuilder;
    }
}
