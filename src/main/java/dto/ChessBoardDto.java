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
    private static final String RANK_GUIDE_LINE_FORM = "  %s";
    private static final String EMPTY_POINT = ".";
    private static final int BOARD_SIZE = 8;

    private final String board;
    private final String currentTurn;

    public ChessBoardDto(final String board, final String currentTurn) {
        this.board = board;
        this.currentTurn = currentTurn;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) { // TODO 리팩터링
        final Map<Position, Piece> pieceOfPosition = chessBoard.getBoard();
        final String result = IntStream.range(0, BOARD_SIZE)
                .mapToObj(Rank::from)
                .map(rank -> IntStream.range(0, BOARD_SIZE)
                        .mapToObj(File::from)
                        .map(file -> convertToString(pieceOfPosition, file, rank))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()))
                .concat(String.format("%n%n%s%n", FILE_GUIDE_LINE));

        return new ChessBoardDto(result, chessBoard.getCamp().toString());
    }

    private static String convertToString(final Map<Position, Piece> board, final File file, final Rank rank) {
        final Position position = new Position(file, rank);
        final Piece piece = board.get(position);
        if (piece != null) {
            return PieceType.from(piece).getValue() + paddedRankGuidLine(file, rank);
        }
        return EMPTY_POINT + paddedRankGuidLine(file, rank);
    }

    private static String paddedRankGuidLine(final File file, final Rank rank) {
        if (file.isLast()) {
            return String.format(RANK_GUIDE_LINE_FORM, rank.getValue());
        }
        return "";
    }

    public String getBoard() {
        return board;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }
}
