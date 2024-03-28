package chess.domain.chessGame.generator;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public interface PieceGenerator {

    List<Piece> makeSpecialPieces(Color color);

    List<Piece> makePawnPieces(Color color, int amount);

    List<Piece> makeEmptyPieces(int amount);
}
