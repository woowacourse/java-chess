package chess.domain.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public enum ChessPiece {

    KING("k", Collections.singletonList(Position.of(File.E, Rank.ONE)), new KingStrategy(), 0),
    QUEEN("q", Collections.singletonList(Position.of(File.D, Rank.ONE)), new QueenStrategy(), 9),
    ROOK("r", Arrays.asList(Position.of(File.A, Rank.ONE), Position.of(File.H, Rank.ONE)), new RookStrategy(), 5),
    BISHOP("b", Arrays.asList(Position.of(File.C, Rank.ONE), Position.of(File.F, Rank.ONE)), new BishopStrategy(), 3),
    KNIGHT("n", Arrays.asList(Position.of(File.B, Rank.ONE), Position.of(File.G, Rank.ONE)), new KnightStrategy(), 2.5),
    PAWN("p", Arrays.stream(File.values()).map(file -> Position.of(file, Rank.TWO)).collect(Collectors.toList()),
            new PawnStrategy(), 1);

    private final String name;
    private final List<Position> initialPositions;
    private final MoveStrategy moveStrategy;
    private final double score;

    ChessPiece(String name, List<Position> initialPositions, MoveStrategy moveStrategy, double score) {
        this.name = name;
        this.initialPositions = Collections.unmodifiableList(initialPositions);
        this.moveStrategy = moveStrategy;
        this.score = score;
    }

    public List<Position> getOriginalPositions() {
        return initialPositions;
    }

    public List<Position> searchPath(Position source, Position target, boolean isKill) {
        return moveStrategy.findMovePath(source, target, isKill);
    }

    public double calculateScore(int count) {
        return score * count;
    }

    public String getName() {
        return name;
    }
}
