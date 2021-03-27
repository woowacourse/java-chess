package chess.view;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (int i = 0; i < Position.POSITIONS.size(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            Piece piece = chessBoard.findByPosition(Position.POSITIONS.get(i));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    public static void printStatus(ChessBoard chessBoard) {
        System.out.println("black: " + chessBoard.sumScoreByColor(Color.BLACK));
        System.out.println("white: " + chessBoard.sumScoreByColor(Color.WHITE));
    }
}
