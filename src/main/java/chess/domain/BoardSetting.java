package chess.domain;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static java.util.stream.Collectors.toList;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.List;

public enum BoardSetting {

    BLACK_KING(new King(BLACK), List.of("e8")),
    BLACK_QUEEN(new Queen(BLACK), List.of("d8")),
    BLACK_BISHOP(new Bishop(BLACK), List.of("c8", "f8")),
    BLACK_KNIGHT(new Knight(BLACK), List.of("b8", "g8")),
    BLACK_ROOK(new Rook(BLACK), List.of("a8", "h8")),
    BLACK_PAWN(new Pawn(BLACK), List.of("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7")),

    WHITE_KING(new King(WHITE), List.of("e1")),
    WHITE_QUEEN(new Queen(WHITE), List.of("d1")),
    WHITE_BISHOP(new Bishop(WHITE), List.of("c1", "f1")),
    WHITE_KNIGHT(new Knight(WHITE), List.of("b1", "g1")),
    WHITE_ROOK(new Rook(WHITE), List.of("a1", "h1")),
    WHITE_PAWN(new Pawn(WHITE), List.of("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2")),

    EMPTY_PIECE(new EmptyPiece(), List.of("a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
            "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
            "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
            "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"))
    ;

    private final Piece piece;

    private final List<String> positions;

    BoardSetting(Piece piece, List<String> positions) {
        this.piece = piece;
        this.positions = positions;
    }

    public Piece getPiece() {
        return piece;
    }

    public List<Position> getPositions() {
        return positions.stream()
                .map(Position::new)
                .collect(toList());
    }
}
