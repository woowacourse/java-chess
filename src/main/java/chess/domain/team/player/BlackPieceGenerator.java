package chess.domain.team.player;

import chess.domain.piece.*;

import java.util.List;

import static chess.domain.team.Team.BLACK;

public class BlackPieceGenerator implements PieceGenerator {

    @Override
    public List<Piece> generate() {
        return List.of(new King(BLACK), new Queen(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK), new Pawn(BLACK));
    }
}
