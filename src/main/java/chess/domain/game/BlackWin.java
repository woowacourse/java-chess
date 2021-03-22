package chess.domain.game;

import chess.domain.piece.black.BlackPiece;

import java.util.Optional;

public class BlackWin extends Finished {
    public BlackWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return Optional.of(BlackPiece.NOTATION);
    }
}
