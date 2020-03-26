package chess.view;

import chess.board.*;
import chess.board.piece.Piece;
import chess.board.piece.Team;

import java.util.Map;

public class OutputView {

    public static void showAllCommand() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n" +
                "> 점수 출력 : status");
    }

    public static void showChessBoard(ChessBoard chessBoard) {
        Map<Coordinate, Tile> board = chessBoard.getChessBoard();
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.get(Coordinate.of(file, rank)).getPiece();
                System.out.print(PieceRender.findTokenByPiece(piece));
            }
            System.out.println();
        }
    }

    public static void showScore(Team team, double score) {
        System.out.println(team + "팀 : " + score + "점");
    }
}
