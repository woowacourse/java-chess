package chess.domain.game.state.finished;

import chess.domain.game.ChessGame;
import chess.domain.piece.white.WhitePiece;

import java.util.Optional;

public class WhiteWin extends Finished {
    public WhiteWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String getStatus() {
        return "whiteWin";
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return Optional.of(WhitePiece.NOTATION);
    }
}
