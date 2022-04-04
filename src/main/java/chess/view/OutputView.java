package chess.view;

import chess.domain.piece.Piece;
import chess.domain.state.State;
import java.util.Optional;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class OutputView {

    public void displayGameRule() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 점수 확인 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void displayChessBoard(Board board) {
        for (int i = Position.MAX; i >= Position.MIN; i--) {
            displayLine(board, i);
            System.out.println();
        }
        System.out.println();
    }

    public void displayTurn(State status) {
        if (status.isWhite()) {
            System.out.print("하얀색 차례 - ");
            return;
        }
        System.out.print("검은색 차례 - ");
    }

    private void displayLine(Board board, int i) {
        for (int j = Position.MIN; j <= Position.MAX; j++) {
            displaySymbol(board, i, j);
        }
    }

    private void displaySymbol(Board board, int i, int j) {
        Position nowPosition = new Position(i, j);
        if (board.getPieces().containsKey(nowPosition)) {
            System.out.print(PieceView.from(board.findPiece(nowPosition)));
            return;
        }
        System.out.print("ꕤ");
    }

    public void displayErrorMessage(RuntimeException exception) {
        System.out.println(exception.getMessage());
    }

    public void displayScore(ChessScore chessScore) {
        System.out.println("검은색 팀 점수 : " + chessScore.getBlackScore());
        System.out.println("흰색 팀 점수 : " + chessScore.getWhiteScore());
    }
}
