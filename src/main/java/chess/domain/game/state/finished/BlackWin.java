package chess.domain.game.state.finished;

import chess.domain.game.ChessGame;
import chess.domain.piece.black.BlackPiece;

import java.util.Optional;

public class BlackWin extends Finished {
    public BlackWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String getStatus() {
        return "blackWin";
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return Optional.of(BlackPiece.NOTATION);
    }


}
