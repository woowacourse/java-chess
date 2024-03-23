package chess.domain.chessBoard.generator;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public interface PieceGenerator {

    List<Piece> makeSpecialPieces(Color color);
    List<Piece> makePawnPieces(Color color);
    List<Piece> makeEmptyPieces();
}
