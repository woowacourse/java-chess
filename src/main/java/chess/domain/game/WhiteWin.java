package chess.domain.game;

import java.util.Optional;

public class WhiteWin extends Finished {
    public WhiteWin(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public Optional<String> getWinnerColorNotation() {
        return chessGame.getWinnerColorNotation();
    }
}
