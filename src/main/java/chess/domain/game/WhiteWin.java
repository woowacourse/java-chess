package chess.domain.game;

import chess.domain.piece.Color;

import java.util.Optional;

public class WhiteWin extends Finished {
    public WhiteWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<Color> getWinnerColor() {
        return Optional.of(Color.WHITE);
    }
}
