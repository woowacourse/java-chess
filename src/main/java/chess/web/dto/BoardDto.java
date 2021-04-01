package chess.web.dto;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    Map<String, Object> result = new HashMap<>();

    public BoardDto(ChessGame chessGame) {
        Board board = chessGame.board();
        Map<Position, Piece> pieceMap = board.asMap();
        for (Position position : pieceMap.keySet()) {
            Piece piece = pieceMap.get(position);

            String initial = piece.getInitial();
            if (initial.equals(".")) {
                initial = "";
            }

            result.put(position.toString(), initial);
        }
        result.put("currentTurn", chessGame.currentTurn());
    }

    public Map<String, Object> getResult() {
        return result;
    }
}
