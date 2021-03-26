package chess.domain.game.state;

import chess.domain.board.Game;
import chess.domain.piece.PieceColor;
import chess.domain.position.Notation;

public abstract class StageState implements GameState {

    private final Game game;

    protected StageState(final Game game) {
        this.game = game;
    }

    protected Game currentBoard() {
        return game;
    }

    protected Game moveInBoard(final Notation sourceName, final Notation targetName, final PieceColor currentTurnColor) {
        if (game.containsEnemyPiece(sourceName, currentTurnColor.reversed())) {
            throw new IllegalArgumentException("해당 체스말을 움직일 권한이 없습니다.");
        }
        return game.move(sourceName, targetName);
    }
}
