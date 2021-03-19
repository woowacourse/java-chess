package chess.view;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String GAME_INIT = "체스 게임을 시작합니다.";
    private static final String GAME_INIT_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String INIT_END = "END";
    public static void printInitMessage() {
        System.out.println(GAME_INIT);
        System.out.println(GAME_INIT_COMMAND);
    }

    public static void printBoard(Game game) {
        Board board = game.getBoard();
        for (YPosition yPosition : YPosition.values()) {
            printXAxis(board, yPosition);
        }
    }

    private static void printXAxis(Board board, YPosition yPosition) {
        for (XPosition xPosition : XPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            Piece piece = board.pieceAtPosition(position);
            System.out.print(piece.getSymbol());
        }
        System.out.println();
    }
}
