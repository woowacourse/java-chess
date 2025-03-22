package chess.domain.initializer;

import chess.domain.pieces.ChessPieces;

public interface ChessPieceInitializer {

    ChessPieces blackPieces();
    ChessPieces whitePieces();
}
