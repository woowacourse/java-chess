package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.board.Tile;
import chess.domain.coordinate.Coordinate;
import chess.domain.observer.Observable;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;
import java.util.Map;

public class Chess implements Observable {
    private final ChessBoard chessBoard;
    private Team currentTeam = Team.WHITE;
    private boolean isKingAlive = true;

    public Chess(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.subscribe(this);
    }

    public void move(Coordinate source, Coordinate target) {
        chessBoard.move(source, target);
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public boolean isTurnOf(Team team) {
        return this.currentTeam.equals(team);
    }

    public boolean isTurnOf(Coordinate source) {
        return chessBoard.isNotSameTeam(source, currentTeam);
    }

    public List<String> getMovableWay(Coordinate sourceCoordinate) {
        return chessBoard.getMovableWay(sourceCoordinate);
    }

    public double calculateCurrentTeamScore() {
        return this.chessBoard.calculateScore(this.currentTeam);
    }

    public double calculateBlackScore() {
        return this.chessBoard.calculateScore(Team.BLACK);
    }

    public double calculateWhiteScore() {
        return this.chessBoard.calculateScore(Team.WHITE);
    }

    public boolean isKingAlive() {
        return isKingAlive;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    @Override
    public void update(final Object object) {
        if (!(object instanceof Piece)) {
            throw new IllegalArgumentException();
        }
        if (object instanceof King) {
            isKingAlive = false;
            return;
        }
        currentTeam = currentTeam.getOppositeTeam();
    }
}
