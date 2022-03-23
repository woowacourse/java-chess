package chess.view;

import chess.domain.Type;
import chess.dto.ChessMenDto;
import chess.dto.ChessPieceDto;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String CHESS_GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final Map<Type, Character> chessPieceTypeToCharacter = new HashMap<>();
    private static final char EMPTY_CHESS_BLOCK = '.';
    private static final char ADJUST_COLUMN_INDEX = 'a';
    private static final int ADJUST_ROW_INDEX = 1;
    private static final int CHESSBOARD_SIZE = 8;
    private static final String EMPTY_STRING = "";

    static {
        chessPieceTypeToCharacter.put(Type.PAWN, 'p');
        chessPieceTypeToCharacter.put(Type.BISHOP, 'b');
        chessPieceTypeToCharacter.put(Type.KNIGHT, 'n');
        chessPieceTypeToCharacter.put(Type.ROOK, 'r');
        chessPieceTypeToCharacter.put(Type.KING, 'k');
        chessPieceTypeToCharacter.put(Type.QUEEN, 'q');
    }

    public static void printChessGameStart() {
        System.out.println(CHESS_GAME_START_MESSAGE);
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
            Type type = chessPieceDto.getType();
            char chessPieceAbbreviation = chessTypeToUpperCase(chessPieceTypeToCharacter.get(type));
            chessBoard.get(getRowIndex(chessPieceDto)).set(getColumnIndex(chessPieceDto), chessPieceAbbreviation);
        }
    }

    private static void setWhiteChessMenOnBoard(List<List<Character>> chessBoard, ChessMenDto chessMenDto) {
        for (ChessPieceDto chessPieceDto : chessMenDto) {
            Type type = chessPieceDto.getType();
            Character chessPieceAbbreviation = chessPieceTypeToCharacter.get(type);
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
