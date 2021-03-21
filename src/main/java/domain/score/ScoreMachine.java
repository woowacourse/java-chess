package domain.score;

import domain.Board;
import domain.piece.objects.Pawn;
import domain.piece.Piece;
import domain.piece.Position;

import java.util.Map;

public class ScoreMachine {
    private ScoreMachine() {

    }

    public static Score blackPiecesScore(Board board) {
        return getScore(board, board.getBlackTeam());
    }

    public static Score whitePiecesScore(Board board) {
        return getScore(board, board.getWhiteTeam());
    }

    private static Score getScore(Board board, Map<Position, Piece> team) {
        Score score = Score.ZERO;
        for (Map.Entry<Position, Piece> entry : team.entrySet()) {
            score = addScore(score, entry, board);
        }
        return score;
    }

    private static Score addScore(Score score, Map.Entry<Position, Piece> entry, Board board) {
        if (entry.getValue() instanceof Pawn) {
            score = addPawnScore(entry, score, board);
            return score;
        }
        score = score.add(entry.getValue().getScore());
        return score;
    }

    private static Score addPawnScore(Map.Entry<Position, Piece> pawn, Score score, Board board) {
        if (board.isExistSamePawn(pawn)) {
            return score.add(pawn.getValue().getScore().half());
        }
        return score.add(pawn.getValue().getScore());
    }
//
//
//    public Score whitePiecesScore() {
//        Score score = new Score();
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (board[i][j] != null && !board[i][j].isBlack()) {
//                    if (board[i][j] instanceof Pawn) {
//                        score = addPawnScore(i, j, score);
//                        continue;
//                    }
//                    score = score.sum(board[i][j].getScore());
//                }
//            }
//        }
//        return score;
//    }
}
