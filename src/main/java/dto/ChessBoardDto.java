package dto;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.ChessBoard;
import model.piece.Piece;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import view.message.PieceType;

public class ChessBoardDto {

    private static final String FILE_GUIDE_LINE = "abcdefgh";

    private final String value;

    private ChessBoardDto(final String value) {
        this.value = value;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) {
        final Map<Position, Piece> pieceOfPosition = chessBoard.getBoard();
        final String result = IntStream.range(0, 8)
                .mapToObj(Rank::from)
                .map(rank -> IntStream.range(0, 8)
                        .mapToObj(File::from)
                        .map(file -> convertToString(pieceOfPosition, file, rank))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()))
                .concat(String.format("%n%n%s%n", FILE_GUIDE_LINE));
        return new ChessBoardDto(result);
    }

    private static String convertToString(final Map<Position, Piece> board, final File file, final Rank rank) {
        final Position position = new Position(file, rank);
        final Piece piece = board.get(position);
        if (piece != null) {
            return PieceType.from(piece).getValue() + paddedRankGuidLine(file, rank);
        }
        return "." + paddedRankGuidLine(file, rank);
    }

    private static String paddedRankGuidLine(final File file, final Rank rank) {
        if (file.isLast()) {
            return "  " + rank.getValue();
        }
        return "";
    }

    public String getValue() {
        return value;
    }
}
