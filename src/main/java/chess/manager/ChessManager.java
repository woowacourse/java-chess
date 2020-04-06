package chess.manager;

import chess.board.ChessBoardAdapter;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.entity.Movement;
import chess.handler.exception.AlreadyEndGameException;
import chess.observer.Observable;
import chess.piece.Piece;
import chess.piece.Team;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessManager implements Observable<Piece> {
    private final ChessBoardAdapter chessBoard;
    private Team currentTeam = Team.WHITE;
    private boolean isKingAlive = true;

    public ChessManager(final ChessBoardAdapter chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.subscribe(this);
    }

    public Piece move(String source, String target) {
        if (!isKingAlive) {
            throw new AlreadyEndGameException(String.format("%s의 승리로 끝난 게임입니다.", currentTeam.name()));
        }
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            throw new IllegalArgumentException(String.format("%s팀의 말을 움직여 주세요.", currentTeam.name()));
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
    public void update(final Piece piece) {
        if (piece.isKing()) {
            isKingAlive = false;
            return;
        }
        currentTeam = currentTeam.getOppositeTeam();
    }
}
