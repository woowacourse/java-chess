package chess.view;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

public class OutputView {

    private static final String GAME_INIT = "체스 게임을 시작합니다.";
    private static final String GAME_INIT_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String END = "END";

    public static void printInitMessage() {
        System.out.println(GAME_INIT);
        System.out.println(GAME_INIT_COMMAND);
    }

    public static void printBoard(final Game game) {
        Board board = game.getBoard();
        System.out.println();
        for (YPosition yPosition : YPosition.values()) {
            printXAxis(board, yPosition);
        }
    }

    private static void printXAxis(final Board board, final YPosition yPosition) {
        for (XPosition xPosition : XPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            Piece piece = board.pieceAtPosition(position);
            System.out.print(piece.symbol());
        }
        System.out.println();
    }

    public static void printScore(final Game game) {
        System.out.printf("백팀 점수 : %f\n", game.computeWhitePoint());
        System.out.printf("흑팀 점수 : %f\n", game.computeBlackPoint());
    }

    public static void printGameWinner(final Game game) {
        if (game.winnerColor() == PieceColor.VOID) {
            System.out.println("무승부입니다.");
            return;
        }
        System.out.printf("%s이 승리했습니다\n", game.winnerColor().getName());
        System.out.println(END);
    }
}
