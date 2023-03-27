package chess.controller.mapper;

import chess.domain.piece.*;

import java.util.Arrays;

public enum PieceMapper {
    BISHOP(PieceType.BISHOP, "b", "B"),
    KING(PieceType.KING, "k", "K"),
    KNIGHT(PieceType.KNIGHT, "n", "N"),
    PAWN(PieceType.PAWN, "p", "P"),
    QUEEN(PieceType.QUEEN, "q", "Q"),
    ROOK(PieceType.ROOK, "r", "R"),
    EMPTY(PieceType.EMPTY, ".", "."),
    ;

    private static final String INVALID_PIECE_MESSAGE = "잘못된 말을 입력했습니다.";

    private final PieceType pieceType;
    private final String whiteTeamView;
    private final String blackTeamView;

    PieceMapper(PieceType piece, String whiteTeamView, String blackTeamView) {
        this.pieceType = piece;
        this.whiteTeamView = whiteTeamView;
        this.blackTeamView = blackTeamView;
    }

    public static PieceMapper from(PieceType pieceType) {
        return Arrays.stream(values())
                .filter(it -> it.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PIECE_MESSAGE));
    }

    public static PieceMapper from(String pieceView) {
        return Arrays.stream(values())
                .filter(it -> it.whiteTeamView.equalsIgnoreCase(pieceView))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PIECE_MESSAGE));
    }

    public String getPieceViewBy(Team team) {
        if (team == Team.BLACK) {
            return this.blackTeamView;
        }
        return this.whiteTeamView;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
