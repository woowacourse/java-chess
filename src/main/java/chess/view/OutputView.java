package chess.view;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    private static final String TEAM_SCORE_DELIMITER = ": ";
    private static final String WINNER_FORMAT = "우승 팀: %s\n";

    private static final List<Integer> rows = List.of(8, 7, 6, 5, 4, 3, 2, 1);
    private static final List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);


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

    public static void printCurrentChessBoard(ChessBoardDto chessBoardDto) {
        Map<ChessBoardPosition, ChessPiece> mapInformation = chessBoardDto.getMapInformation();
        for (int row : rows) {
            printRow(mapInformation, row);
            System.out.println();
        }
    }

    private static void printRow(Map<ChessBoardPosition, ChessPiece> mapInformation, int row) {
        for (int column : columns) {
            printSector(mapInformation, row, column);
        }
    }

    private static void printSector(Map<ChessBoardPosition, ChessPiece> mapInformation, int row, int column) {
        ChessBoardPosition target = ChessBoardPosition.of(column, row);
        if (mapInformation.keySet().contains(target)) {
            printChessPiece(mapInformation.get(target));
            return;
        }
        System.out.print(".");
    }

    private static void printChessPiece(ChessPiece chessPiece) {
        if (chessPiece.isSameTeam(Team.BLACK)) {
            System.out.print(ChessPieceName.of(chessPiece).toUpperCase());
            return;
        }
        System.out.print(ChessPieceName.of(chessPiece));
    }



    public static void printStatus(ChessStatusDto chessStatusDto) {
        for (Entry<Team, Double> entry : chessStatusDto.getTeamScore().entrySet()) {
            System.out.println(entry.getKey() + TEAM_SCORE_DELIMITER + entry.getValue());
        }
        System.out.printf(WINNER_FORMAT, chessStatusDto.getWinner());
    }
}
