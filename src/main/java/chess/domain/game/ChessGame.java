package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.position.Position;

public class ChessGame {
    private final Pieces pieces;
    private Turn turn;

    public ChessGame() {
        pieces = PiecesFactory.create();
        turn = new Turn(Color.WHITE);
    }

    public void move(Position start, Position end) {
        pieces.move(start, end, turn.getColor());
        turn = turn.change();
    }

    public ScoreResult calculateScore() {
        return new ScoreResult(pieces);
    }

    public boolean isKingDead() {
        return pieces.isKingDead();
    }

    public Color getAliveKingColor() {
        return pieces.getAliveKingColor();
    }

    public Pieces getPieces() {
        return pieces;
    }
}
