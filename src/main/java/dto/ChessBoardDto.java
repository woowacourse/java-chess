package dto;

import java.util.Map;
import model.ChessGame;
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

    private ChessBoardDto(final String board, final String currentTurn) {
        this.board = board;
        this.currentTurn = currentTurn;
    }

    public static ChessBoardDto from(final ChessGame chessGame) {
        final Map<Position, Piece> pieceOfPosition = chessGame.getBoard();

        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            stringBuilder.append(generateBoardLine(pieceOfPosition, Rank.from(i)));
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append(String.format("%n%s%n", FILE_GUIDE_LINE));

        return new ChessBoardDto(stringBuilder.toString(), chessGame.getCamp().toString());
    }

    private static String generateBoardLine(final Map<Position, Piece> pieceOfPosition, final Rank rank) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < BOARD_SIZE; j++) {
            final File file = File.from(j);
            final Position position = new Position(file, rank);
            final Piece piece = pieceOfPosition.get(position);
            stringBuilder.append(convertToString(piece, file, rank));
        }
        return stringBuilder.toString();
    }

    private static String convertToString(final Piece piece, final File file, final Rank rank) {
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
