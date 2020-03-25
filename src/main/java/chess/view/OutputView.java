package chess.view;

import chess.board.*;

import java.util.Map;

public class OutputView {

    public static void showAllCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void showChessBoard(ChessBoard chessBoard) {
        Map<Coordinate, Tile> board = chessBoard.getChessBoard();
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Tile tile = board.get(Coordinate.of(file, rank));
                System.out.print(PieceRender.findTokenByPiece(tile.getPiece()));
            }
            System.out.println();
        }
    }
}
