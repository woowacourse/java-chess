package chess.view;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]";

    public void printCommandInformation() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final ChessBoard chessBoard) {
        List<Piece> pieces = chessBoard.findAllPieces();
        String chessBoardExpression = ChessBoardExpression.toExpression(pieces);
        System.out.println(chessBoardExpression);
    }

    public void printErrorMessage(final String message) {
        System.out.printf("%s %s%n", ERROR_PREFIX, message);
    }
}
