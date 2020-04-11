package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import java.util.Collections;
import java.util.Map;

public class ChessGameDTO {
    private final Map<Position, Piece> board;
    private final boolean isGameFinished;
    private final Score score;

    public ChessGameDTO(Map<Position, Piece> board, Score score, boolean isGameFinished) {
        this.board = board;
        this.score = score;
        this.isGameFinished = isGameFinished;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public boolean isGameFinished() {
        return this.isGameFinished;
    }

    public Score getScore() {
        return this.score;
    }
}
