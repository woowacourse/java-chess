package domain.board;

import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;

import java.util.Arrays;
import java.util.List;

public enum InitialPiecePosition {

    PAWN(PieceType.PAWN, List.of(Rank.TWO, Rank.SEVEN), List.of(File.values())),
    ROOK(PieceType.ROOK, List.of(Rank.ONE, Rank.EIGHT), List.of(File.A, File.H)),
    KNIGHT(PieceType.KNIGHT, List.of(Rank.ONE, Rank.EIGHT), List.of(File.B, File.G)),
    BISHOP(PieceType.BISHOP, List.of(Rank.ONE, Rank.EIGHT), List.of(File.C, File.F)),
    QUEEN(PieceType.QUEEN, List.of(Rank.ONE, Rank.EIGHT), List.of(File.D)),
    KING(PieceType.KING, List.of(Rank.ONE, Rank.EIGHT), List.of(File.E)),
    NONE(PieceType.NONE, List.of(), List.of()),
    ;

    private final PieceType pieceType;
    private final List<Rank> ranks;
    private final List<File> files;

    InitialPiecePosition(PieceType pieceType, List<Rank> ranks, List<File> files) {
        this.pieceType = pieceType;
        this.ranks = ranks;
        this.files = files;
    }

    public static PieceType find(Rank rank, File file) {
        return Arrays.stream(values())
                .filter(initialPiecePosition -> initialPiecePosition.ranks.contains(rank) && initialPiecePosition.files.contains(file))
                .findFirst()
                .orElse(InitialPiecePosition.NONE)
                .pieceType;
    }
}
