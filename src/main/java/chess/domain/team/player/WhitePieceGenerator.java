package chess.domain.team.player;

import chess.domain.piece.*;

import java.util.List;

import static chess.domain.team.Team.WHITE;

public class WhitePieceGenerator implements PieceGenerator {

    @Override
    public List<Piece> generate() {
        return List.of(new King(WHITE), new Queen(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE), new Pawn(WHITE));
    }
}
