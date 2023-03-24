package domain.chessboard;

import domain.piece.*;

import java.util.Arrays;
import java.util.List;

public enum RankFactory {

    OTHERS_BLACK(List.of(new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK), new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK))),
    PAWN_BLACK(List.of(new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK))),
    EMPTY(List.of(new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty())),
    PAWN_WHITE(List.of(new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE))),
    OTHERS_WHITE(List.of(new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE), new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE)));

    private final List<SquareStatus> squareStatus;

    RankFactory(final List<SquareStatus> squareStatus) {
        this.squareStatus = squareStatus;
    }

    public static RankFactory from(RankFactory rankFactory) {
        return Arrays.stream(values())
                .filter(value -> value == rankFactory)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<SquareStatus> getSquareStatus() {
        return squareStatus;
    }

}
