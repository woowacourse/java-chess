package chess.Service;

import chess.database.DBChessBoardDao;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.piece.Camp;
import chess.dto.MoveDto;

import java.util.Collections;
import java.util.List;

public class ChessService {

    public static final int POSITION_SET_INDEX = 2;

    private final DBChessBoardDao dbChessBoardDao;
    private final ChessGame chessGame;

    public ChessService(final ChessGame chessGame, final DBChessBoardDao dbChessBoardDao) {
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

    public ChessBoard getChessBoard() {
        return chessGame.getChessBoard();
    }

    public Camp getWinner() {
        return chessGame.getWinnerCamp();
    }

    public double getWhiteScore() {
        return chessGame.getWhiteScore();
    }

    public double getBlackScore() {
        return chessGame.getBlackScore();
    }

    public boolean isKingLive() {
        return chessGame.isKingsLive();
    }

}
