package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public Map<Position, Pawn> getInitialPawns() {
        Map<Position, Pawn> pawns = new HashMap<>();
        for (Abscissa value : Abscissa.values()) {
            pawns.put(Position.valueOf(value, Ordinate.TWO), new Pawn());
            pawns.put(Position.valueOf(value, Ordinate.SEVEN), new Pawn());
        }
        return pawns;
    }

    public Map<Position, Rook> getInitialRooks() {
        Map<Position, Rook> rooks = new HashMap<>();
        rooks.put(Position.valueOf(Abscissa.a, Ordinate.ONE), new Rook());
        rooks.put(Position.valueOf(Abscissa.a, Ordinate.EIGHT), new Rook());
        rooks.put(Position.valueOf(Abscissa.h, Ordinate.ONE), new Rook());
        rooks.put(Position.valueOf(Abscissa.h, Ordinate.EIGHT), new Rook());
        return rooks;
    }

    public Map<Position, Knight> getInitialKnights() {
        Map<Position, Knight> kinghts = new HashMap<>();
        kinghts.put(Position.valueOf(Abscissa.b, Ordinate.ONE), new Knight());
        kinghts.put(Position.valueOf(Abscissa.b, Ordinate.EIGHT), new Knight());
        kinghts.put(Position.valueOf(Abscissa.g, Ordinate.ONE), new Knight());
        kinghts.put(Position.valueOf(Abscissa.g, Ordinate.EIGHT), new Knight());
        return kinghts;
    }

    public Map<Position, Bishop> getInitialBishops() {
        Map<Position, Bishop> bishops = new HashMap<>();
        bishops.put(Position.valueOf(Abscissa.c, Ordinate.ONE), new Bishop());
        bishops.put(Position.valueOf(Abscissa.c, Ordinate.EIGHT), new Bishop());
        bishops.put(Position.valueOf(Abscissa.f, Ordinate.ONE), new Bishop());
        bishops.put(Position.valueOf(Abscissa.f, Ordinate.EIGHT), new Bishop());

        return bishops;
    }

    public Map<Position, Queen> getInitialQueens() {
        Map<Position, Queen> queen = new HashMap<>();
        queen.put(Position.valueOf(Abscissa.d, Ordinate.ONE), new Queen());
        queen.put(Position.valueOf(Abscissa.d, Ordinate.EIGHT), new Queen());

        return queen;
    }

    public Map<Position, King> getInitialKings() {
        Map<Position, King> king =new HashMap<>();
        king.put(Position.valueOf(Abscissa.e, Ordinate.ONE), new King());
        king.put(Position.valueOf(Abscissa.e, Ordinate.EIGHT), new King());

        return king;
    }
}
