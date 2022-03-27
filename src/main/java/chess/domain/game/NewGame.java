package chess.domain.game;

import static chess.util.PieceGeneratorUtil.initAllChessmen;

import chess.domain.piece.Piece;
import chess.dto.GameResultDto;
import chess.dto.MoveCommandDto;
import java.util.List;

public final class NewGame implements Game {

    private static final String GAME_NOT_STARTED_EXCEPTION_MESSAGE = "아직 시작되지 않은 게임입니다.";

    @Override
    public Game init() {
        return new WhiteTurn(new ActivePieces(initAllChessmen()));
    }

    @Override
    public Game moveChessmen(MoveCommandDto dto) {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public GameResultDto getGameResult() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Piece> getChessmen() {
        throw new UnsupportedOperationException(GAME_NOT_STARTED_EXCEPTION_MESSAGE);
    }
}
