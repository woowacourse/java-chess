package domain.chessgame;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessScore {

    private final Board board;

    public ChessScore(Board board) {
        this.board = board;
    }

    public Score piecesScore(boolean isBlack){
        Score score = new Score();
        Map<Position, Piece> pieces = board.pieces(isBlack);

        for(Map.Entry<Position, Piece> entry : pieces.entrySet()){
            score = score.sum(entry.getValue().getScore());
        }
        return score;
    }

}
