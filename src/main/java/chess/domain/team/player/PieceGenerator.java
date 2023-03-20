package chess.domain.team.player;

import chess.domain.piece.Piece;

import java.util.List;

@FunctionalInterface
public interface PieceGenerator {

    List<Piece> generate();
}
