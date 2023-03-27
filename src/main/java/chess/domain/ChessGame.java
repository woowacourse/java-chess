package chess.domain;

import chess.dao.ChessDao;
import chess.domain.board.Board;
import chess.domain.square.Color;
import chess.domain.square.Square;
import chess.domain.square.Team;
import chess.domain.state.State;
import chess.domain.state.WaitingStart;
import chess.dto.MoveDto;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final int notFinishedGameId;
    private State state;

    public ChessGame(final ChessDao chessDao) {
        this.state = new WaitingStart();
        if (!chessDao.existCurrentGame()) {
            chessDao.saveInitialGame();
        }
        this.notFinishedGameId = chessDao.findNotFinishedGameId();
    }

    public void start(final ChessDao chessDao) {
        this.state = state.start();
        if (chessDao.existCurrentGame()) {
            int notFinishedGameId = chessDao.findNotFinishedGameId();
            List<MoveDto> moveDtos = chessDao.selectAllHistory(notFinishedGameId);
            moveByHistory(moveDtos);
        }
    }

    private void moveByHistory(final List<MoveDto> moveDtos) {
        for (MoveDto moveDto : moveDtos) {
            Square source = Square.from(moveDto.getSource());
            Square target = Square.from(moveDto.getTarget());
            this.state = state.move(source, target);
        }
    }

    public void move(final Square sourceSquare, final Square targetSquare, final ChessDao chessDao) {
        this.state = state.move(sourceSquare, targetSquare);
        chessDao.saveHistory(MoveDto.of(sourceSquare, targetSquare), notFinishedGameId);
        if (state.isKingDead()) {
            chessDao.setGameFinished(notFinishedGameId);
        }
    }

    public Map<Team, Double> status() {
        return Map.of(
                Team.from(Color.WHITE), state.calculateScore(Team.from(Color.WHITE)),
                Team.from(Color.BLACK), state.calculateScore(Team.from(Color.BLACK))
        );
    }

    public void end() {
        this.state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Team getWinner() {
        return state.getWinner();
    }
}
