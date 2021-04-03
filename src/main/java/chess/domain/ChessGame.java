package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public final class ChessGame {
    private Board board;
    private Team currentTurn;
    private boolean isPlaying = true;
    private Team winner;

    public void initialize() {
        BoardFactory boardFactory = new BoardFactory();
        this.board = boardFactory.board();
        this.currentTurn = Team.WHITE;
        this.isPlaying = true;
    }

    public void move(final String startPoint, final String endPoint) {
        board.move(position(startPoint), position(endPoint), currentTurn);
        if (board.isEnemyKingDead(currentTurn)) {
            winner = currentTurn;
            finish();
        }
        currentTurn = Team.enemyTeam(currentTurn);
    }

    private Position position(final String point) {
        return new Position(
                Row.location(String.valueOf(point.charAt(1))),
                Col.location(String.valueOf(point.charAt(0)))
        );
    }

    public void finish() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public double scoreByTeam(final Team team) {
        return board.scoreByTeam(team);
    }

    public boolean checkRightTurn(final String clickedSection) {
        Pieces pieces = board.piecesByTeam(currentTurn);
        return pieces.containsPosition(position(clickedSection));
    }

    public List<String> movablePositionsByStartPoint(final String startPoint) {
        Pieces pieces = board.piecesByTeam(currentTurn);
        Piece piece = pieces.pieceByPosition(position(startPoint));
        return piece.movablePositions(board);
    }

    public Board board() {
        return board;
    }

    public Team winner() {
        return winner;
    }

    public Team turn() {
        return currentTurn;
    }
}
