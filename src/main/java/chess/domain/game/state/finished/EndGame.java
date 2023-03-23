package chess.domain.game.state.finished;

import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;
import java.util.Map;

public class EndGame extends FinishedGame {

    public EndGame(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public Map<Position, Piece> getPiecesPosition() {
        return null;
    }
}
