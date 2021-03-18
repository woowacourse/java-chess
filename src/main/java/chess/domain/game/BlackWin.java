package chess.domain.game;

import chess.domain.piece.Color;

import java.util.Optional;

public class BlackWin extends Finished {
    public BlackWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<Color> getWinnerColor() {
        return Optional.of(Color.BLACK);
    }
}
