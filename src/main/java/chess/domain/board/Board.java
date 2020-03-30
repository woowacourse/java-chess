package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        this(BoardInitializer.initializeAll());
    }

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void updateBoard(Position sourcePosition, Position targetPosition) {
        Piece selectedPiece = this.board.get(sourcePosition);
        this.board.put(targetPosition, selectedPiece);
        this.board.remove(sourcePosition);
    }

    public Optional<Team> checkWinner() {
        if (checkWhiteKing() && !checkBlackKing()) {
            return Optional.of(Team.WHITE);
        }
        if (!checkWhiteKing() && checkBlackKing()) {
            return Optional.of(Team.BLACK);
        }
        return Optional.empty();
    }

    private boolean checkWhiteKing() {
        for (Piece piece : board.values()) {
            if (piece.isWhiteKing()) return true;
        }
        return false;
    }

    private boolean checkBlackKing() {
        for (Piece piece : board.values()) {
            if (piece.isBlackKing()) return true;
        }
        return false;
    }

    public boolean isEmpty(final Position position) {
        return !this.board.containsKey(position);
    }

    public Piece getPiece(final Position position) {
        return this.board.get(position);
    }

    public Map<Position, Piece> get() {
        return board;
    }
}