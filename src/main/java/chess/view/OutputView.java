package chess.view;

import chess.domain.team.Team;
import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import java.util.List;

public class OutputView {

    private final static int BOARD_SIZE = 8;
    private final static int DEFAULT_POSITION = 1;
    private final static char EMPTY = '.';

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.print("게임 시작은 start, 종료는 end ");
    }

    public static void printBoard(PiecesDto piecesDto) {
        List<PieceDto> pieces = piecesDto.getPieces();
        char[][] board = generateEmptyBoard();
        fillBoard(board, pieces);

        for (int y = 0; y < BOARD_SIZE; y++) {
            printRow(board[y]);
            System.out.println(" | " + (BOARD_SIZE - y));
        }
        System.out.println("---------");
        System.out.println("abcdefgh");
    }

    private static void printRow(char[] chars) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            System.out.print(chars[x]);
        }
    }

    private static char[][] generateEmptyBoard() {
        char[][] emptyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int y = 0; y < BOARD_SIZE; y++) {
            fillColumn(emptyBoard, y, EMPTY);
        }
        return emptyBoard;
    }

    private static void fillColumn(char[][] emptyBoard, int y, char content) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            emptyBoard[y][x] = content;
        }
    }

    private static void fillBoard(char[][] board, List<PieceDto> pieces) {
        for (PieceDto piece : pieces) {
            fillBoardWith(board, piece);
        }
    }

    private static void fillBoardWith(char[][] board, PieceDto piece) {
        int x = piece.getX();
        int y = piece.getY();
        Team team = piece.getTeam();
        char pieceLetter = piece.getPieceLetter();
        board[BOARD_SIZE - y][x - DEFAULT_POSITION] = makePieceLetter(team, pieceLetter);
    }

    private static char makePieceLetter(Team team, char pieceLetter) {
        if (team.isBlack()) {
            return Character.toUpperCase(pieceLetter);
        }
        return pieceLetter;
    }

    public static void printScore(double blackScore, double whiteScore) {
        System.out.printf("검은색은 %.2f점. 흰색은 %.2f점입니다.%n", blackScore, whiteScore);
    }

    public static void printGameEndMessage() {
        System.out.println("게임이 끝났습니다.");
    }

    public static void printException(String message) {
        System.out.printf("[Error] %s %n", message);
    }
}
