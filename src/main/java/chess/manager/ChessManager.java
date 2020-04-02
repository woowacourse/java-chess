package chess.manager;

import chess.board.ForwardChessBoard;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.observer.Observable;
import chess.piece.King;
import chess.piece.Piece;
import chess.piece.Team;

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
