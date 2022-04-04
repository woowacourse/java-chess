package chess.view;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WebViewMapper {
    private static final Map<Position, String> POSITION_MAPPER;
    private static final Map<Piece, String> PIECE_MAPPER = new HashMap<>();

    static {
        POSITION_MAPPER = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> Position.of(file, rank)))
                .collect(Collectors.toMap(Function.identity(), WebViewMapper::parsePosition));

        final List<Piece> whitePieces = piecesByColor(Color.WHITE);
        final List<Piece> blackPieces = piecesByColor(Color.BLACK);
        final List<String> blackValues = List.of("pb", "rb", "nb", "bb", "qb", "kb");
        final List<String> whiteValues = List.of("pw", "rw", "nw", "bw", "qw", "kw");
        for (int i = 0; i < whitePieces.size(); i++) {
            PIECE_MAPPER.put(whitePieces.get(i), whiteValues.get(i));
            PIECE_MAPPER.put(blackPieces.get(i), blackValues.get(i));
        }
    }

    private static List<Piece> piecesByColor(Color color) {
        return List.of(new Pawn(color), new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                new King(color));
    }

    private static String parsePosition(Position position) {
        return getFileNameByPosition(position) + getRankNameByPosition(position);
    }

    private static String getRankNameByPosition(Position position) {
        return Arrays.stream(Rank.values())
                .filter(position::isSameRank)
                .findAny()
                .orElseThrow(NoSuchElementException::new)
                .name();
    }

    private static String getFileNameByPosition(Position position) {
        return Arrays.stream(File.values())
                .filter(position::isSameFile)
                .findAny()
                .orElseThrow(NoSuchElementException::new)
                .name();
    }

    public static String parse(Position position) {
        return POSITION_MAPPER.get(position);
    }

    public static String parse(Piece piece) {
        return PIECE_MAPPER.get(piece);
    }

    public static List<PositionDto> parse(Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .map(entry -> PositionDto.of(WebViewMapper.parse(entry.getKey()),
                        WebViewMapper.parse(entry.getValue())))
                .collect(Collectors.toList());
    }

}
