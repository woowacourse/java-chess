package chess.view;

import chess.controller.Command;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.piece.*;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum PieceMapper {
    VACANT_PIECE(Role.VACANT_PIECE, " . ", " . "),
    PAWN(Role.PAWN, " p ", " P "),
    INITIAL_PAWN(Role.INITIAL_PAWN, " p ", " P "),
    ROOK(Role.ROOK, " r ", " R "),
    KNIGHT(Role.KNIGHT, " n ", " N "),
    BISHOP(Role.BISHOP, " b ", " B "),
    QUEEN(Role.QUEEN, " q ", " Q "),
    KING(Role.KING, " k ", " K ");

    private final Role role;
    private final String whitePattern;
    private final String blackPattern;

    PieceMapper(Role role, String whitePattern, String blackPattern) {
        this.role = role;
        this.whitePattern = whitePattern;
        this.blackPattern = blackPattern;
    }

    public static String getPattern(Piece piece) {
        PieceMapper targetMapper = Arrays.stream(values())
                .filter(pieceMapper -> piece.isRole(pieceMapper.role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 역할의 기물이 없습니다."));
        if (piece.getColor() == Color.BLACK) {
            return targetMapper.blackPattern;
        }
        return targetMapper.whitePattern;
    }
}
