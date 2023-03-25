package chess.service;

import chess.controller.GameCommand;
import chess.dao.ChessDao;
import chess.dao.DbChessGameDao;
import chess.domain.game.ChessGame;
import chess.dto.outputView.PrintBoardDto;
import chess.dto.outputView.PrintTotalScoreDto;
import chess.dto.outputView.PrintWinnerDto;

import java.util.List;

import static chess.controller.GameCommand.SOURCE_INDEX;
import static chess.controller.GameCommand.TARGET_INDEX;
import static chess.controller.GameCommand.getPosition;

public final class ChessGameService {

    private final ChessDao dao;
    private ChessGame chessGame;

    public ChessGameService() {
        this.dao = new DbChessGameDao();
    }

    public boolean hasHistory() {
        return dao.hasHistory();
    }

    public void delete() {
        dao.delete();
    }

    public void save() {
        dao.delete();
        dao.save(chessGame);
    }

    public void initChessGame() {
        chessGame = dao.loadGame();
    }

    public void initChessGame(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void move(final List<String> input) {
        chessGame.move(GameCommand.getPosition(input, SOURCE_INDEX), getPosition(input, TARGET_INDEX));
    }

    public boolean isKingDead() {
        if (chessGame.isKingDead()) {
            dao.delete();
            return true;
        }
        return false;
    }

    public PrintTotalScoreDto calculateScore() {
        return chessGame.calculateScore();
    }

    public PrintWinnerDto getWinner() {
        return chessGame.getWinner();
    }

    public PrintBoardDto getBoard() {
        return chessGame.getBoard();
    }

    public void checkStart() {
        if (chessGame != null) {
            throw new IllegalArgumentException("체스 게임은 이미 진행되고 있습니다.");
        }
    }

    public void checkMove() {
        if (chessGame == null) {
            throw new IllegalArgumentException("체스 게임은 아직 시작하지 않았습니다.");
        }
    }
}
