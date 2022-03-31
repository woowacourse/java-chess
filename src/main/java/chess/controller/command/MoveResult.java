package chess.controller.command;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;

public class MoveResult {

    private final boolean kingDie;
    private final Map<Position, ChessPiece> pieceByPosition;

    public MoveResult(final boolean kingDie, final Map<Position, ChessPiece> pieceByPosition) {
        this.kingDie = kingDie;
        this.pieceByPosition = pieceByPosition;
    }

    public boolean isKingDie() {
        return kingDie;
    }

    public Map<Position, ChessPiece> getPieceByPosition() {
        return pieceByPosition;
    }

    public Score score() {
        return new Score(pieceByPosition);
    }
}
