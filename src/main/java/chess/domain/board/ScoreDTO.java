package chess.domain.board;

import java.util.List;

import chess.domain.chess.Color;
import chess.domain.piece.PieceDTO;

public class ScoreDTO {
    private final double blackScore;
    private final double whiteScore;

    public ScoreDTO(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static ScoreDTO from(List<PieceDTO> pieceDTOS) {
        Board board = Board.from(new BoardDTO(pieceDTOS));
        return new ScoreDTO(board.score(Color.BLACK), board.score(Color.WHITE));
    }
}
