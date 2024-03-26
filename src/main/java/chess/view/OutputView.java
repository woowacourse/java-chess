package chess.view;

import chess.dto.PieceDrawing;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    private static final int BOARD_SIZE = 8;
    private static final char EMPTY_PIECE = '.';
    private static final String TITLE_START = "> 체스 게임을 시작합니다.%n" +
            "> 게임 시작 : start%n" +
            "> 게임 종료 : end%n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printStartMessage() {
        System.out.printf(TITLE_START);
    }

    public void printBoard(final List<PieceDrawing> pieceDrawings) {
        char[][] board = generateEmptyBoard();
        setPiecesOnBoard(board, pieceDrawings);
        for (char[] line : board) {
            System.out.println(line);
        }
        System.out.println();
    }

    private char[][] generateEmptyBoard() {
        char[][] emptyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : emptyBoard) {
            Arrays.fill(line, EMPTY_PIECE);
        }
        return emptyBoard;
    }

    private void setPiecesOnBoard(final char[][] board, final List<PieceDrawing> pieceDrawings) {
        for (PieceDrawing pieceDrawing : pieceDrawings) {
            char pieceSymbol = PieceMapper.map(pieceDrawing.typeName(), pieceDrawing.colorName());
            board[pieceDrawing.rankOrdinal()][pieceDrawing.fileOrdinal()] = pieceSymbol;
        }
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
