package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardFixture {
    public Map<Position, Piece> setUpBoard() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        board.put(new Position(File.A, Rank.ONE), new Pawn(Camp.WHITE));
        board.put(new Position(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        board.put(new Position(File.A, Rank.THREE), new Empty());
        board.put(new Position(File.A, Rank.FOUR), new Empty());
        board.put(new Position(File.A, Rank.FIVE), new Empty());
        board.put(new Position(File.A, Rank.SIX), new Empty());
        board.put(new Position(File.A, Rank.SEVEN), new Empty());
        board.put(new Position(File.A, Rank.EIGHT), new Empty());
        board.put(new Position(File.B, Rank.ONE), new Empty());
        board.put(new Position(File.B, Rank.TWO), new Empty());
        board.put(new Position(File.B, Rank.THREE), new Empty());
        board.put(new Position(File.B, Rank.FOUR), new Empty());
        board.put(new Position(File.B, Rank.FIVE), new Empty());
        board.put(new Position(File.B, Rank.SIX), new Empty());
        board.put(new Position(File.B, Rank.SEVEN), new Empty());
        board.put(new Position(File.B, Rank.EIGHT), new Empty());
        board.put(new Position(File.C, Rank.ONE), new Empty());
        board.put(new Position(File.C, Rank.TWO), new Empty());
        board.put(new Position(File.C, Rank.THREE), new Empty());
        board.put(new Position(File.C, Rank.FOUR), new Empty());
        board.put(new Position(File.C, Rank.FIVE), new Empty());
        board.put(new Position(File.C, Rank.SIX), new Empty());
        board.put(new Position(File.C, Rank.SEVEN), new Empty());
        board.put(new Position(File.C, Rank.EIGHT), new Empty());
        board.put(new Position(File.D, Rank.ONE), new Empty());
        board.put(new Position(File.D, Rank.TWO), new Empty());
        board.put(new Position(File.D, Rank.THREE), new Empty());
        board.put(new Position(File.D, Rank.FOUR), new Empty());
        board.put(new Position(File.D, Rank.FIVE), new Empty());
        board.put(new Position(File.D, Rank.SIX), new Empty());
        board.put(new Position(File.D, Rank.SEVEN), new Empty());
        board.put(new Position(File.D, Rank.EIGHT), new Empty());
        board.put(new Position(File.E, Rank.ONE), new Empty());
        board.put(new Position(File.E, Rank.TWO), new Empty());
        board.put(new Position(File.E, Rank.THREE), new Empty());
        board.put(new Position(File.E, Rank.FOUR), new Empty());
        board.put(new Position(File.E, Rank.FIVE), new Empty());
        board.put(new Position(File.E, Rank.SIX), new Empty());
        board.put(new Position(File.E, Rank.SEVEN), new Empty());
        board.put(new Position(File.E, Rank.EIGHT), new Empty());
        board.put(new Position(File.F, Rank.ONE), new Empty());
        board.put(new Position(File.F, Rank.TWO), new Empty());
        board.put(new Position(File.F, Rank.THREE), new Empty());
        board.put(new Position(File.F, Rank.FOUR), new Empty());
        board.put(new Position(File.F, Rank.FIVE), new Empty());
        board.put(new Position(File.F, Rank.SIX), new Empty());
        board.put(new Position(File.F, Rank.SEVEN), new Empty());
        board.put(new Position(File.F, Rank.EIGHT), new Empty());
        board.put(new Position(File.G, Rank.ONE), new Empty());
        board.put(new Position(File.G, Rank.TWO), new Empty());
        board.put(new Position(File.G, Rank.THREE), new Empty());
        board.put(new Position(File.G, Rank.FOUR), new Empty());
        board.put(new Position(File.G, Rank.FIVE), new Empty());
        board.put(new Position(File.G, Rank.SIX), new Empty());
        board.put(new Position(File.G, Rank.SEVEN), new Empty());
        board.put(new Position(File.G, Rank.EIGHT), new Empty());
        board.put(new Position(File.H, Rank.ONE), new Empty());
        board.put(new Position(File.H, Rank.TWO), new Empty());
        board.put(new Position(File.H, Rank.THREE), new Empty());
        board.put(new Position(File.H, Rank.FOUR), new Empty());
        board.put(new Position(File.H, Rank.FIVE), new Empty());
        board.put(new Position(File.H, Rank.SIX), new Empty());
        board.put(new Position(File.H, Rank.SEVEN), new Empty());
        board.put(new Position(File.H, Rank.EIGHT), new Empty());

        return board;
    }
}
