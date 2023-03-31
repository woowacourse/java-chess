package chess.domain.game;

import java.util.Map;

import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.exception.DifferentTeamException;
import chess.domain.exception.NotPlayableException;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Game {

    private final int id;
    private final GameDao gameDao;
    private Team turn;
    private final Board board;

    public Game(GameDao gameDao) {
        this(gameDao, Team.WHITE, BoardFactory.createBoard());
    }

    public Game(GameDao gameDao, Team turn, Board board) {
        this.gameDao = gameDao;
        if (gameDao.hasUnfinished()) {
            Integer lastUnfinishedId = gameDao.findIdOfLastUnfinished();
            this.id = lastUnfinishedId;
            this.turn = gameDao.findTurnBy(lastUnfinishedId);
            this.board = new Board(gameDao.findPiecesBy(lastUnfinishedId));
            return;
        }
        this.turn = turn;
        this.board = board;
        this.id = gameDao.save(this);
    }

    public void movePiece(Position source, Position target) {
        validateNotFinished(board);
        validateTurn(turn, source);
        board.move(source, target);
        changeTurn();
        gameDao.put(id, this);
    }

    private void validateNotFinished(Board board) {
        if (isFinished(board)) {
            throw new NotPlayableException("왕 없이 플레이할 수 없습니다");
        }
    }

    private boolean isFinished(Board board) {
        return !board.hasKing(Team.WHITE) || !board.hasKing(Team.BLACK);
    }

    private void validateTurn(Team turn, Position position) {
        if (!board.hasPositionTeamOf(position, turn)) {
            throw new DifferentTeamException("자신의 기물만 움직일 수 있습니다");
        }
    }

    private void changeTurn() {
        turn = turn.alter();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    public boolean isFinished() {
        return isFinished(board);
    }

    public double getScoreOf(Team team) {
        return board.score(team);
    }

    public Team getWinner() {
        if (isFinished(board)) {
            return getWinnerByKing();
        }
        return getWinnerByScore();
    }

    private Team getWinnerByKing() {
        if (board.hasKing(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    private Team getWinnerByScore() {
        if (getScoreOf(Team.WHITE) > getScoreOf(Team.BLACK)) {
            return Team.WHITE;
        }
        if (getScoreOf(Team.WHITE) < getScoreOf(Team.BLACK)) {
            return Team.BLACK;
        }
        return Team.NEUTRAL;
    }

    public Team getTurn() {
        return turn;
    }

    public Integer getId() {
        return id;
    }

    public void finish() {
        gameDao.end(id);
    }
}
