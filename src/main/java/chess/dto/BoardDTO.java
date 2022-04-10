package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.piece.unit.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public final class BoardDTO {

    private Map<String, Object> result = new HashMap<>();

    public BoardDTO(final ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getBoard();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            String symbol = convertPieceSymbol(piece);
            result.put(position.toString(), symbol);
        }
    }

    private String convertPieceSymbol(final Piece piece) {
        String symbol = "";
        if (piece != null) {
            symbol = piece.getSymbolByTeam();
        }
        return symbol;
    }

    public Map<String, Object> getResult() {
        return result;
    }
}
