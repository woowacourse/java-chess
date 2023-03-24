package chess.domain.game.state;

import chess.domain.game.result.GameResult;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface ChessGame {

    ChessGame startGame();

    ChessGame move(Position fromPosition, Position toPosition);

    boolean isRunnableGame();

    ChessGame endGame();

    ChessGame status();

    Map<Position, Piece> getPiecesPosition();

    GameResult calculateResult();
}
