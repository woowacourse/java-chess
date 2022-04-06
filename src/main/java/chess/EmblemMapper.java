package chess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.model.File;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.Piece;

public class EmblemMapper {
    private static final Map<String, String> emblemToFullNameTable = new HashMap<>();

    static {
        emblemToFullNameTable.put("p", "whitePawn");
        emblemToFullNameTable.put("r", "whiteRook");
        emblemToFullNameTable.put("n", "whiteKnight");
        emblemToFullNameTable.put("b", "whiteBishop");
        emblemToFullNameTable.put("q", "whiteQueen");
        emblemToFullNameTable.put("k", "whiteKing");

        emblemToFullNameTable.put("P", "blackPawn");
        emblemToFullNameTable.put("R", "blackRook");
        emblemToFullNameTable.put("N", "blackKnight");
        emblemToFullNameTable.put("B", "blackBishop");
        emblemToFullNameTable.put("Q", "blackQueen");
        emblemToFullNameTable.put("K", "blackKing");
    }

    public static String fullNameFrom(Piece piece) {
        if (Objects.isNull(piece)) {
            return "empty";
        }

        return emblemToFullNameTable.get(piece.getEmblem());
    }

    public static Map<String, String> StringPieceMapByPiecesByPositions(Map<Position, Piece> piecesByPositions) {
        Map<String, String> stringPieces = new HashMap<>();
        for (Rank rank : Rank.values()) {
            stringPieces.putAll(getStringPiecesInOneRank(piecesByPositions, rank));
        }

        return stringPieces;
    }

    private static Map<String, String> getStringPiecesInOneRank(Map<Position, Piece> piecesByPositions, Rank rank) {
        return Arrays.stream(File.values())
            .collect(Collectors.toUnmodifiableMap(file -> file.getValue() + rank.getValue(),
                file -> fullNameFrom(piecesByPositions.get(Position.of(file, rank)))));
    }

    public static String emblemFrom(String fullName) {
        return getKey(emblemToFullNameTable, fullName);
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
}
