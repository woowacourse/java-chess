package chess.view;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String GAME_INIT = "> 체스 게임을 시작합니다.";
    private static final String GAME_INIT_COMMAND = "> 게임 시작 : start \n>게임 종료 : end\n게임 이동 : move source 위치 target 위치 - 예. move b2 b3";
    private static final String DIVIDER = "--------";
    private static final String BOARD_ROW = "abcdefgh";
    private static final String END = "END";

    public static void printInitMessage() {
        System.out.println(GAME_INIT);
        System.out.println(GAME_INIT_COMMAND);
    }

    public static void printBoard(Game game) {
        Board board = game.getBoard();
        for (YPosition yPosition : YPosition.values()) {
            printXAxis(board, yPosition);
        }
        System.out.println(DIVIDER);
        System.out.println(BOARD_ROW);
        System.out.println();
    }

    private static void printXAxis(Board board, YPosition yPosition) {
        for (XPosition xPosition : XPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            Piece piece = board.pieceAtPosition(position);
            System.out.print(piece.symbol());
        }
        System.out.print(" |" + yPosition.getValue());
        System.out.println();
    }

    public static void printScore(Game game) {
        System.out.printf("백팀 점수 : %f\n", game.computeWhitePoint());
        System.out.printf("흑팀 점수 : %f\n", game.computeBlackPoint());
    }

    public static void printGameWinner(Game game) {
        System.out.printf("%s이 승리했습니다\n", game.winnerColor().getName());
        System.out.println(END);
    }
}
