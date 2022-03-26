package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int NEXT = 1;
    private final Map<Position, Piece> value = new HashMap<>();

    public Board() {
        BoardInitializer.init(value);
    }

    public Map<Position, Piece> toMap() {
        return Collections.unmodifiableMap(value);
    }

    public void move(final Position from, final Position to) {
        Piece piece = value.get(from);
        piece.checkPieceMoveRange(this, from, to);
        value.put(to, piece);
    }

    public boolean isMatchingColor(final Position from, final Color color) {
        Piece piece = value.get(from);
        return piece.isSameColor(color);
    }

    public Piece getPiece(final Position position) {
        return value.get(position);
    }

    public boolean hasPieceInFile(final Position from, final Position to) {
        int minRank = Math.min(from.getRankNumber(), to.getRankNumber());
        int maxRank = Math.max(from.getRankNumber(), to.getRankNumber());

        return value.keySet().stream()
                .filter(position -> position.getFile().equals(from.getFile()))
                .filter(position -> position.getRankNumber() > minRank)
                .anyMatch(position -> position.getRankNumber() < maxRank);
    }

    public boolean hasPieceInRank(final Position from, final Position to) {
        int minFile = File.min(from.getFile(), to.getFile());
        int maxFile = File.max(from.getFile(), to.getFile());

        return value.keySet().stream()
                .filter(position -> position.getRankNumber() == from.getRankNumber())
                .filter(position -> position.getFileOrder() > minFile)
                .anyMatch(position -> position.getFileOrder() < maxFile);
    }

    public void checkPieceInDiagonal(final Position from, final Position to) {
        checkRisingDiagonal(from, to);
        checkDescendingDiagonal(from, to);
    }

    private void checkRisingDiagonal(Position from, Position to) {
        int minFile = File.min(from.getFile(), to.getFile());
        int maxFile = File.max(from.getFile(), to.getFile());
        int minRank = Math.min(from.getRankNumber(), to.getRankNumber());

        int rank = minRank + NEXT;
        for (int file = minFile + NEXT; file < maxFile; file++, rank++) {
            checkHasPiece(rank, file);
        }
    }

    private void checkHasPiece(int rank, int file) {
        if (value.get(Position.of(File.from(file), Rank.from(rank))) != null) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    private void checkDescendingDiagonal(Position from, Position to) {
        int nextRank = Math.min(from.getRankNumber(), to.getRankNumber()) + NEXT;
        int maxRank = Math.max(from.getRankNumber(), to.getRankNumber());
        int file = File.max(from.getFile(), to.getFile()) - NEXT;

        for (int rank = nextRank; rank < maxRank; rank++, file--) {
            checkHasPiece(rank, file);
        }
    }
}
