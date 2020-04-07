package chess.domain.manager;

import chess.domain.board.ForwardChessBoard;
import chess.domain.board.Tile;
import chess.domain.coordinate.Coordinate;
import chess.domain.observer.Observable;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.Map;

public class ChessManager implements Observable {
    private final ForwardChessBoard chessBoard;
    private Team currentTeam = Team.WHITE;
    private boolean isKingAlive = true;

    public ChessManager(final ForwardChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.subscribe(this);
    }

    public void move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            return;
        }
        try {
            chessBoard.move(source, target);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public double calculateCurrentTeamScore() {
        return this.chessBoard.calculateScore(this.currentTeam);
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
