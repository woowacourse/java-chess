package chess.view;

import dto.ChessBoardDto;
import dto.PieceDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final int CHESSBOARD_SIZE = 8;
    private static final int RANK_TO_ROW_CONVERTER = 8;
    private static final int FILE_TO_COLUMN_CONVERTER = 1;
    private static final String PROMPT = "> ";
    private static final List<List<String>> EMPTY_CHESSBOARD;

    static {
        List<List<String>> emptyChessBoard = new ArrayList<>();
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            emptyChessBoard.add(new ArrayList<>(Collections.nCopies(CHESSBOARD_SIZE, " . ")));
        }
        EMPTY_CHESSBOARD = List.copyOf(emptyChessBoard);
    }

    private static int calculateColumnIndex(final int file) {
        return file - FILE_TO_COLUMN_CONVERTER;
    }

    public void printInitialMessage() {
        printMessageWithPrompt("체스 게임을 시작합니다.");
        printMessageWithPrompt("게임 시작 : start");
        printMessageWithPrompt("게임 종료 : end");
        printMessageWithPrompt("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private void printMessageWithPrompt(final String message) {
        System.out.println(PROMPT + message);
    }

    public void printInvalidMoveMessage() {
        System.out.println("이동할 수 없습니다.");
    }

    public void printChessBoard(ChessBoardDto chessBoardDto) {
        List<List<String>> presentChessBoard = makePresentChessBoard(chessBoardDto.getChessBoardDto());
        System.out.println();
        for (List<String> rank : presentChessBoard) {
            printRank(rank);
            System.out.println(String.format("   %s", 8 - presentChessBoard.indexOf(rank)));
        }
        System.out.println();
        System.out.println(" a  b  c  d  e  f  g  h");
        System.out.println();
    }

    private List<List<String>> makePresentChessBoard(final List<PieceDto> pieceDtos) {
        List<List<String>> chessBoard = new ArrayList<>(EMPTY_CHESSBOARD);
        for (PieceDto pieceDto : pieceDtos) {
            int row = calculateRowIndex(pieceDto.getRank());
            int col = calculateColumnIndex(pieceDto.getFile());
            chessBoard.get(row)
                    .set(col, convertLetterByPiece(pieceDto.getType(), pieceDto.getSide()));
        }
        return chessBoard;
    }

    private int calculateRowIndex(final int rank) {
        return RANK_TO_ROW_CONVERTER - rank;
    }

    private String convertLetterByPiece(final String type, final String side) {
        if (Objects.equals(type, "PAWN")) {
            return convertLetterBySide(" p ", side);
        }
        if (Objects.equals(type, "ROOK")) {
            return convertLetterBySide(" r ", side);
        }
        if (Objects.equals(type, "KNIGHT")) {
            return convertLetterBySide(" n ", side);
        }
        if (Objects.equals(type, "BISHOP")) {
            return convertLetterBySide(" b ", side);
        }
        if (Objects.equals(type, "QUEEN")) {
            return convertLetterBySide(" q ", side);
        }
        if (Objects.equals(type, "KING")) {
            return convertLetterBySide(" k ", side);
        }
        return " . ";
    }

    private String convertLetterBySide(final String letter, final String side) {
        if (Objects.equals(side, "BLACK")) {
            return letter.toUpperCase();

        }
        return letter.toLowerCase();
    }

    private void printRank(final List<String> rank) {
        for (String piece : rank) {
            System.out.print(piece);
        }
    }
}
