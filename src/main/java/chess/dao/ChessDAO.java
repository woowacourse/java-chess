package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.ArrayList;
import java.util.List;

public interface ChessDAO {

    Long saveGame(List<Piece> pieces, Long gameId);

    List<Piece> loadGame(Long gameId);

    void removeGame(Long gameId);

    class Fake implements ChessDAO {

        private List<Piece> pieces = PieceFactory.initialPieces(8, 0, 7);

        @Override
        public Long saveGame(List<Piece> pieces, Long gameId) {
            this.pieces = pieces;
            return gameId;
        }

        @Override
        public List<Piece> loadGame(Long gameId) {
            return pieces;
        }

        @Override
        public void removeGame(Long gameId) {
            pieces = new ArrayList<>();
        }
    }
}