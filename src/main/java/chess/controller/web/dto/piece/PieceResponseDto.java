package chess.controller.web.dto.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.util.PieceConverter;

public class PieceResponseDto {

    private final String symbol;
    private final String position;

    public PieceResponseDto(final String symbol, final String position) {
        this.symbol = symbol;
        this.position = position;
    }

    public Piece toPiece() {
        return PieceConverter.parsePiece(symbol);
    }

    public Position toPosition() {
        return Position.of(position);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPosition() {
        return position;
    }
}
