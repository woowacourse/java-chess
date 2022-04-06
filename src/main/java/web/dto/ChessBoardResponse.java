package web.dto;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import console.view.Symbol;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardResponse {

    Map<String, Object> result = new HashMap<>();

    public ChessBoardResponse(ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.chessBoard();
        Map<Position, Piece> pieces = chessBoard.getBoard();

        for (Position position : pieces.keySet()) {
            Piece piece = pieces.get(position);
            String symbol = makePieceName(piece);

            result.put(position.toString(), symbol);
        }
    }

    private String makePieceName(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return "W" + Symbol.findBySymbol(piece.getClass());
        }
        return "B" + Symbol.findBySymbol(piece.getClass());
    }

    public Map<String, Object> getResult() {
        return result;
    }
}
