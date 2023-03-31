package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum InitialPiece {

    WHITE_ROOK(List.of(1, 8), TeamColor.WHITE.startingRank(), new Rook(TeamColor.WHITE)),
    WHITE_KNIGHT(List.of(2, 7), TeamColor.WHITE.startingRank(), new Knight(TeamColor.WHITE)),
    WHITE_BISHOP(List.of(3, 6), TeamColor.WHITE.startingRank(), new Bishop(TeamColor.WHITE)),
    WHITE_QUEEN(List.of(4), TeamColor.WHITE.startingRank(), new Queen(TeamColor.WHITE)),
    WHITE_KING(List.of(5), TeamColor.WHITE.startingRank(), new King(TeamColor.WHITE)),
    WHITE_PAWN(List.of(1, 2, 3, 4, 5, 6, 7, 8), TeamColor.WHITE.findPawnStartRank(),
        new Pawn(TeamColor.WHITE)),

    BLACK_ROOK(List.of(1, 8), TeamColor.BLACK.startingRank(), new Rook(TeamColor.BLACK)),
    BLACK_KNIGHT(List.of(2, 7), TeamColor.BLACK.startingRank(), new Knight(TeamColor.BLACK)),
    BLACK_BISHOP(List.of(3, 6), TeamColor.BLACK.startingRank(), new Bishop(TeamColor.BLACK)),
    BLACK_QUEEN(List.of(4), TeamColor.BLACK.startingRank(), new Queen(TeamColor.BLACK)),
    BLACK_KING(List.of(5), TeamColor.BLACK.startingRank(), new King(TeamColor.BLACK)),
    BLACK_PAWN(List.of(1, 2, 3, 4, 5, 6, 7, 8), TeamColor.BLACK.findPawnStartRank(),
        new Pawn(TeamColor.BLACK));

    private final List<Integer> files;
    private final int rank;
    private final Piece piece;

    InitialPiece(final List<Integer> files, final int rank, Piece piece) {
        this.files = files;
        this.rank = rank;
        this.piece = piece;
    }

    public static Map<Position, Piece> getPiecesWithPosition() {
        Map<Position, Piece> piecesByPosition = new HashMap<>();
        Arrays.stream(values())
            .forEach(
                pieceWithStartPosition -> putPieceToMap(piecesByPosition, pieceWithStartPosition));
        return piecesByPosition;
    }

    private static void putPieceToMap(final Map<Position, Piece> map,
        final InitialPiece pieceWithStartPosition) {
        for (int file : pieceWithStartPosition.files) {
            map.put(Position.of(file, pieceWithStartPosition.rank), pieceWithStartPosition.piece);
        }
    }

    public static Piece findPieceByTypeAndColor(final PieceType type, final TeamColor color) {
        return Arrays.stream(values())
            .map(InitialPiece::getPiece)
            .filter(piece -> piece.isSameColorAndType(color, type))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 색과 타입의 체스말은 존재하지 않습니다."));
    }

    public Piece getPiece() {
        return piece;
    }

}
