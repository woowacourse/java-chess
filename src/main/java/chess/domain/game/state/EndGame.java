package chess.domain.game.state;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import java.util.List;

public class EndGame extends FinishedGame {

    protected EndGame(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public GameResult calculateResult() {
        List<Piece> whitePieces = chessBoard.getPiecesOfCamp(Camp.WHITE);

        if (whitePieces.contains(new King(Camp.WHITE))) {
            return new GameResult(MatchResult.WHITE_WIN);
        }

        return new GameResult(MatchResult.BLACK_WIN);
    }
}
