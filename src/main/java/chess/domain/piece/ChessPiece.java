package chess.domain.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public enum ChessPiece {

    KING("k", Collections.singletonList(Position.of(File.E, Rank.ONE))),
    QUEEN("q", Collections.singletonList(Position.of(File.D, Rank.ONE))),
    ROOK("r", Arrays.asList(Position.of(File.A, Rank.ONE), Position.of(File.H, Rank.ONE))),
    BISHOP("b", Arrays.asList(Position.of(File.C, Rank.ONE), Position.of(File.F, Rank.ONE))),
    KNIGHT("n", Arrays.asList(Position.of(File.B, Rank.ONE), Position.of(File.G, Rank.ONE))),
    PAWN("p", Arrays.stream(File.values()).map(file -> Position.of(file, Rank.TWO)).collect(Collectors.toList())),
    EMPTY_PIECE(".", Collections.EMPTY_LIST);

    private final String name;

    private final List<Position> initialPositions;

    ChessPiece(String name, List<Position> initialPositions) {
        this.name = name;
        this.initialPositions = initialPositions;
    }

    public List<Position> getInitialPositions() {
        return initialPositions;
    }

    public String getName() {
        return name;
    }
}
