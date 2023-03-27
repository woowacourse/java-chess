package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum File {

    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private static final String WRONG_FILE_ERROR_MESSAGE = "잘못된 file 입니다.";

    private final int address;
    private final String index;

    File(final int address, final String index) {
        this.address = address;
        this.index = index;
    }

    public static int findByIndex(final String index) {
        File foundFile = Arrays.stream(values())
            .filter(file -> file.index.equalsIgnoreCase(index))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_ERROR_MESSAGE));
        return foundFile.address;
    }

    public static String findByAddress(final int address) {
        return Arrays.stream(values())
            .filter(file -> file.address == address)
            .findAny()
            .map(file -> file.index)
            .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_ERROR_MESSAGE));
    }

    public static Set<Pieces> collectPiecesByFile(final Map<Position, Piece> piecesByPosition) {
        return Arrays.stream(values())
            .map(file -> findPiecesInSameFile(file.address, piecesByPosition))
            .collect(Collectors.toSet());
    }

    private static Pieces findPiecesInSameFile(final int file,
        final Map<Position, Piece> piecesByPosition) {
        return new Pieces(piecesByPosition.keySet()
            .stream()
            .filter(position -> position.getFileAddress() == file)
            .map(piecesByPosition::get)
            .collect(Collectors.toList()));
    }

}
