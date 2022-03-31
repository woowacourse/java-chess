package chess.controller.result;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;

public class MoveResult {

    private final boolean isKingDie;
    private final Map<Position, ChessPiece> pieceByPosition;

    public MoveResult(final boolean isKingDie, final Map<Position, ChessPiece> pieceByPosition) {
        this.isKingDie = isKingDie;
        this.pieceByPosition = pieceByPosition;
    }

    public boolean isKingDie() {
        return isKingDie;
    }

    public Map<Position, ChessPiece> getPieceByPosition() {
        return pieceByPosition;
    }

    public Score score() {
        return new Score(pieceByPosition);
    }
}
