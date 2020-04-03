package chess.manager;

import chess.board.ChessBoardAdapter;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.observer.Observable;
import chess.piece.Piece;
import chess.piece.Team;
import chess.repository.entity.Movement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessManager implements Observable {
    private final ChessBoardAdapter chessBoard;
    private Team currentTeam = Team.WHITE;
    private boolean isKingAlive = true;

    public ChessManager(final ChessBoardAdapter chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.subscribe(this);
    }

    public Piece move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            throw new IllegalArgumentException(String.format("적군의 체스말(%s)을 움직일 수 없습니다.", source));
        }
        return chessBoard.move(source, target);
    }

    public void moveAll(List<Movement> movements) {
        Collections.sort(movements);
        for (Movement movement : movements) {
            this.move(movement.getSourceKey(), movement.getTargetKey());
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public double calculateCurrentTeamScore() {
        return this.chessBoard.calculateScore(this.currentTeam);
    }

    public double calculateScoreByTeam(Team team) {
        return this.chessBoard.calculateScore(team);
    }

    public boolean isKingAlive() {
        return isKingAlive;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public Piece findByKey(String key) {
        return chessBoard.findByKey(key);
    }

    @Override
    public void update(final Object object) {
        Piece.checkInstance(object);

        Piece piece = (Piece) object;
        if (piece.isKing()) {
            isKingAlive = false;
            return;
        }
        currentTeam = currentTeam.getOppositeTeam();
    }
}
