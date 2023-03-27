package chess.Service;

import chess.database.DbChessBoardDao;
import chess.domain.ChessGame;
import chess.dto.MoveDto;

import java.util.Collections;
import java.util.List;

public class ChessService {

    private static final int POSITION_SET_INDEX = 2;

    private final DbChessBoardDao dbChessBoardDao;
    private final ChessGame chessGame;

    public ChessService(final ChessGame chessGame, final DbChessBoardDao dbChessBoardDao) {
        this.chessGame = chessGame;
        this.dbChessBoardDao = dbChessBoardDao;
    }

    public void move(final MoveDto moveDto) {
        chessGame.move(moveDto.getFromPosition(), moveDto.getToPosition());
        dbChessBoardDao.saveNotation(moveDto.getViewFromPosition(), moveDto.getViewToPosition());
    }

    public void checkNotation() {
        List<MoveDto> gameNotation = dbChessBoardDao.selectNotation();

        if (!gameNotation.equals(Collections.emptyList())) {
            moveNotation(gameNotation);
        }
    }

    private void moveNotation(final List<MoveDto> gameNotation) {
        for (int i = 0; i < gameNotation.size(); i += POSITION_SET_INDEX) {
            chessGame.move(gameNotation.get(i).getFromPosition(), gameNotation.get(i).getToPosition());
        }
    }

    public void checkDeleteData() {
        if (!chessGame.isKingsLive()) {
            dbChessBoardDao.delete();
        }
    }

    public boolean isKingLive() {
        return chessGame.isKingsLive();
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
