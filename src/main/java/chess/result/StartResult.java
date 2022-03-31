package chess.result;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import java.util.Map;

public class StartResult {

    private final Map<Position, ChessPiece> pieceByPosition;

    public StartResult(final Map<Position, ChessPiece> pieceByPosition) {
        this.pieceByPosition = pieceByPosition;
    }

    public Map<Position, ChessPiece> getPieceByPosition() {
        return pieceByPosition;
    }
}
