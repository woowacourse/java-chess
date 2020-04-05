package chess.domain.web;

import chess.domain.game.ScoreResult;
import chess.domain.game.Turn;
import chess.domain.game.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

public class WebChessGame {
    private Pieces pieces;
    private Turn turn;

    public WebChessGame() {
        pieces = PiecesFactory.create();
        turn = new Turn(Color.WHITE);
    }

    public void move(Position start, Position end) {
        pieces.move(start, end, turn.getColor());
        turn = turn.change();
    }

    public void reset() {
        pieces = PiecesFactory.create();
        turn = new Turn(Color.WHITE);
    }

    public Positions findMovablePositions(Position position) {
        return pieces.findMovablePositions(position, turn.getColor());
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

    public WebBoard createBoard() {
        return new WebBoard(pieces);
    }

    public Turn getTurn() {
        return turn;
    }
}
