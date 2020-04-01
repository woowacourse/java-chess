package chess.manager;

import chess.board.ChessBoardAdapter;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.observer.Observable;
import chess.piece.Piece;
import chess.piece.Team;

import java.util.Map;

public class ChessManager implements Observable {
    private final ChessBoardAdapter chessBoard;
    private Team currentTeam = Team.WHITE;
    private boolean isKingAlive = true;

    public ChessManager(final ChessBoardAdapter chessBoard) {
        this.chessBoard = chessBoard;
        chessBoard.subscribe(this);
    }

    public boolean move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            return false;
        }
        return chessBoard.move(source, target);
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
