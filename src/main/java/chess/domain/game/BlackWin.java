package chess.domain.game;

import java.util.Optional;

public class BlackWin extends Finished {
    public BlackWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return chessGame.getWinnerColorNotation();
    }
}
