package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Piece;
import chess.dto.GameResultDto;
import java.util.List;

abstract class Started implements Game {

    protected final ActivePieces chessmen;

    protected Started(ActivePieces chessmen) {
        this.chessmen = chessmen;
    }

    @Override
    public final Game init() {
        return new NewGame().init();
    }

    @Override
    public final List<Piece> getChessmen() {
        return chessmen.findAll();
    }

    @Override
    public final GameResultDto getGameResult() {
        double whiteScore = chessmen.calculateScore(WHITE);
        double blackScore = chessmen.calculateScore(BLACK);

        return new GameResultDto(whiteScore, blackScore);
    }
}
