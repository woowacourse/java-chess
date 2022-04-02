package chess.dto.response;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.util.PieceDisplayUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RowDto {

    private final List<SquareDto> squares;

    public RowDto(Map<Position, Piece> board, Rank rank) {
        this.squares = File.allFilesAscending()
                .stream()
                .map(file -> Position.of(file, rank))
                .map(position -> initSquare(board, position))
                .collect(Collectors.toUnmodifiableList());
    }

    private SquareDto initSquare(Map<Position, Piece> board, Position position) {
        if (!board.containsKey(position)) {
            return SquareDto.ofEmpty();
        }
        Piece piece = board.get(position);
        return SquareDto.ofOccupied(piece);
    }

    public List<SquareDto> getSquares() {
        return squares;
    }

    private static class SquareDto {

        static final String WHITE_PIECE_COLOR_CLASSNAME = "white";
        static final String EMPTY_PIECE_DISPLAY = "";
        static final String EMPTY_CLASSNAME = "";

        final String pieceDisplay;
        final String textColor;

        SquareDto(String pieceDisplay, String textColor) {
            this.pieceDisplay = pieceDisplay;
            this.textColor = textColor;
        }

        static SquareDto ofOccupied(Piece piece) {
            String pieceDisplay = PieceDisplayUtil.toWebDisplay(piece);
            return new SquareDto(pieceDisplay, toPieceColorIfWhite(piece));
        }

        static SquareDto ofEmpty() {
            return new SquareDto(EMPTY_PIECE_DISPLAY, EMPTY_CLASSNAME);
        }

        static String toPieceColorIfWhite(Piece piece) {
            if (piece.hasColorOf(Color.WHITE)) {
                return WHITE_PIECE_COLOR_CLASSNAME;
            }
            return EMPTY_CLASSNAME;
        }

        public String getValue() {
            return pieceDisplay;
        }

        public String getColor() {
            return textColor;
        }
    }
}
