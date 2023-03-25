package chess.view;

import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.renderer.CampRenderer;
import chess.renderer.PieceOutputRenderer;

public class OutputView {

    private static final String SCORE_DELIMITER = " 진영 점수: ";

    public static void printChessBoard(Chessboard chessboard) {
        System.out.println();
        for (Rank rank : Rank.values()) {
            printRankAt(chessboard, rank);
        }
    }

    private static void printRankAt(Chessboard chessboard, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            Piece piece = chessboard.getPieceAt(new Square(file, rank));
            stringBuilder.append(PieceOutputRenderer.getPieceName(piece));
        }
        System.out.println(stringBuilder);
    }

    public static void printScore(Chessboard chessboard, Camp camp) {
        System.out.println(CampRenderer.getCampOutput(camp) + SCORE_DELIMITER + chessboard.countScore(camp));
        System.out.println();
    }

    public static void printError(Exception error) {
        System.out.println(error);
    }
}
