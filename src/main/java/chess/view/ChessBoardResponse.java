package chess.view;

import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.view.messsage.PieceMessageConverter;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardResponse {

    private final Map<String, String> squares;

    public ChessBoardResponse(final Map<Position, Piece> board) {
        this.squares = convertToChessBoardMessage(board);
    }

    private Map<String, String> convertToChessBoardMessage(final Map<Position, Piece> board) {
        return board.keySet().stream()
                .collect(Collectors.toMap(
                        Position::getPosition, position -> convertToPieceMessage(board, position)));
    }

    private String convertToPieceMessage(final Map<Position, Piece> board, final Position position) {
        final Piece piece = board.get(position);

        return PieceMessageConverter.convert(piece.getClass(), piece.camp());
    }

    public Map<String, String> getSquares() {
        return squares;
    }
}
