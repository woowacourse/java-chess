package chess.view;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.manager.Status;

public class OutputView {

    public static void printBoard(final Board board) {
        int i =0;
        for(Piece piece : board.getBoard().values()) {
            System.out.print(piece.decideUpperOrLower(piece.getSymbol()));
            if(i++ >6){
                i =0;
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void printStatus(Status status) {
        System.out.println("White score : "+status.whiteScore());
        System.out.println("Black score : "+status.blackScore());
    }

    public static void printGameResult(Status status) {
        printStatus(status);

        System.out.println("=== 게임 결과 ===");

        if(status.whiteScore() > status.blackScore()){
            System.out.println("화이트의 승리입니다.");
        }

        if(status.whiteScore() < status.blackScore()){
            System.out.println("블랙의 승리입니다.");
        }

        if(status.whiteScore() == status.blackScore()){
            System.out.println("무승부입니다.");
        }
    }
}
