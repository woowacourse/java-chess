package chess.dto;

import chess.domain.board.Position;
import chess.domain.piece.piecefigure.Piece;

import java.util.Map;

public class ChessGameDTO {
    public static class GameLoading {
        private Map<Position, Piece> board;
        private String turn;

        public GameLoading(Map<Position, Piece> board, String turn) {
            this.board = board;
            this.turn = turn;
        }

        public Map<Position, Piece> getBoard() {
            return board;
        }

        public String getTurn() {
            return turn;
        }
    }
}
