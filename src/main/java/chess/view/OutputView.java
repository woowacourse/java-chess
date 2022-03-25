package chess.view;

import chess.dto.ChessMenDto;
import chess.dto.ChessPieceDto;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int ADJUST_ROW_INDEX = 1;
    private static final int CHESSBOARD_SIZE = 8;
    private static final char EMPTY_CHESS_BLOCK = '.';
    private static final char ADJUST_COLUMN_INDEX = 'a';
    private static final String EMPTY_STRING = "";
    private static final String CHESS_GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String GAME_MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final Map<String, Character> chessPieceNameToCharacter = new HashMap<>();

    static {
        chessPieceNameToCharacter.put("PAWN", 'p');
        chessPieceNameToCharacter.put("BISHOP", 'b');
        chessPieceNameToCharacter.put("KNIGHT", 'n');
        chessPieceNameToCharacter.put("ROOK", 'r');
        chessPieceNameToCharacter.put("KING", 'k');
        chessPieceNameToCharacter.put("QUEEN", 'q');
    }

    public static void printChessGameStart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CHESS_GAME_START_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_START_COMMAND_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_END_COMMAND_MESSAGE)
                .append(System.lineSeparator())
                .append(GAME_MOVE_COMMAND_MESSAGE);
        System.out.println(stringBuilder);
    }

    public static void printCurrentChessBoard(ChessMenDto blackChessMenDto, ChessMenDto whiteChessMenDto) {
        List<List<Character>> chessBoard = initializeChessBoard();
        setBlackChessMenOnBoard(chessBoard, blackChessMenDto);
        setWhiteChessMenOnBoard(chessBoard, whiteChessMenDto);

        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            List<Character> chessBoardRow = chessBoard.get(i);
            String visualizedRow = Joiner.on(EMPTY_STRING).join(chessBoardRow);
            System.out.println(visualizedRow);
        }
    }

    private static List<List<Character>> initializeChessBoard() {
        List<List<Character>> chessBoard = new ArrayList<>();
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            List<Character> emptyRow = new ArrayList<>(Collections.nCopies(8, EMPTY_CHESS_BLOCK));
            chessBoard.add(emptyRow);
        }
        return chessBoard;
    }

    private static void setBlackChessMenOnBoard(List<List<Character>> chessBoard, ChessMenDto chessMenDto) {
        for (ChessPieceDto chessPieceDto : chessMenDto) {
            String name = chessPieceDto.getName();
            char chessPieceAbbreviation = chessTypeToUpperCase(chessPieceNameToCharacter.get(name));
            chessBoard.get(getRowIndex(chessPieceDto)).set(getColumnIndex(chessPieceDto), chessPieceAbbreviation);
        }
    }

    private static void setWhiteChessMenOnBoard(List<List<Character>> chessBoard, ChessMenDto chessMenDto) {
        for (ChessPieceDto chessPieceDto : chessMenDto) {
            String name = chessPieceDto.getName();
            Character chessPieceAbbreviation = chessPieceNameToCharacter.get(name);
            chessBoard.get(getRowIndex(chessPieceDto)).set(getColumnIndex(chessPieceDto), chessPieceAbbreviation);
        }
    }

    private static int getRowIndex(ChessPieceDto chessPieceDto) {
        return chessPieceDto.getRow() - ADJUST_ROW_INDEX;
    }

    private static int getColumnIndex(ChessPieceDto chessPieceDto) {
        return chessPieceDto.getColumn() - ADJUST_COLUMN_INDEX;
    }

    private static Character chessTypeToUpperCase(Character type) {
        return Character.toUpperCase(type);
    }
}
