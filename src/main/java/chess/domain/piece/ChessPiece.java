package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum ChessPiece {

    KING("k", Collections.singletonList(Position.of(File.E, Rank.ONE)), new KingStrategy()),
    QUEEN("q", Collections.singletonList(Position.of(File.D, Rank.ONE)), new QueenStrategy()),
    ROOK("r", Arrays.asList(Position.of(File.A, Rank.ONE), Position.of(File.H, Rank.ONE)), new RookStrategy()),
    BISHOP("b", Arrays.asList(Position.of(File.C, Rank.ONE), Position.of(File.F, Rank.ONE)), new BishopStrategy()),
    KNIGHT("n", Arrays.asList(Position.of(File.B, Rank.ONE), Position.of(File.G, Rank.ONE)), new KnightStrategy()),
    PAWN("p", Arrays.stream(File.values()).map(file -> Position.of(file, Rank.TWO)).collect(Collectors.toList()), new PawnStrategy());

    private final String name;
    private final List<Position> initialPositions;
    private final MoveStrategy moveStrategy;

    ChessPiece(String name, List<Position> initialPositions, MoveStrategy moveStrategy) {
        this.name = name;
        this.initialPositions = Collections.unmodifiableList(initialPositions);
        this.moveStrategy = moveStrategy;
    }

    public List<Position> getOriginalPositions() {
        return initialPositions;
    }

    public List<Position> searchPath(Position source, Position target) {
        return moveStrategy.findMovePath(source, target);
    }

    public String getName() {
        return name;
    }
}
