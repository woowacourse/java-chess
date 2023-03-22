package chess.domain.score;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class ScoreCalculator {

    private int blackScore = 0;
    private int whiteScore = 0;


    public void calculateScores(Map<Position, Piece> chessBoard){
        for (Piece piece : chessBoard.values()) {
            if(piece.getColor() == Color.BLACK){
                blackScore += piece.getScore();
            }

            if(piece.getColor() == Color.WHITE){
                whiteScore += piece.getScore();
            }
        }
    }
}
