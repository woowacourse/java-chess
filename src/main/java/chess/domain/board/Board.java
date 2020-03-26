package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        this.board = BoardInitializer.initializeAll();
    }

    public Map<String, String> parse() {
        Map<String, String> parseResult = board.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toSymbol(),
                        (e1, e2) -> e1, LinkedHashMap::new));
        return Collections.unmodifiableMap(parseResult);
    }

    public void updateBoard(Position sourcePosition, Position targetPosition, Piece selectedPiece) {
        this.board.put(targetPosition, selectedPiece);
        this.board.remove(sourcePosition);
    }

    public Team checkWinner() {
        if (checkWhiteKing() && !checkBlackKing()) {
            return Team.WHITE;
        }
        if (!checkWhiteKing() && checkBlackKing()) {
            return Team.BLACK;
        }
        return null;
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

    public double calculateScore(Team team) {
        double score = 0;
        for (Piece piece : board.values()) {
            if (piece.getTeam() == team) {
                score += piece.getScore();
            }
        }
        return score;
    }
}