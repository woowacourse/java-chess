package chess.result;

import chess.domain.GameStatus;
import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import java.util.Map;

public class MoveResult {

    private final Map<Position, ChessPiece> pieceByPosition;
    private final Color currentTurn;
    private GameStatus gameStatus;

    public MoveResult(final Map<Position, ChessPiece> pieceByPosition,
                      final GameStatus gameStatus,
                      final Color currentTurn) {
        this.pieceByPosition = pieceByPosition;
        this.gameStatus = gameStatus;
        this.currentTurn = currentTurn;
    }

    public boolean isKingDie() {
        return gameStatus.equals(GameStatus.KING_DIE);
    }

    public Score score() {
        return new Score(pieceByPosition);
    }

    public Map<Position, ChessPiece> getPieceByPosition() {
        return pieceByPosition;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public void changeStatusToKingDie() {
        this.gameStatus = GameStatus.KING_DIE;
    }
}
