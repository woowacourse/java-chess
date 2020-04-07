package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;
import chess.domain.board.WebBoard;

public class ChessGame {
    private Pieces pieces;
    private Turn turn;

    public ChessGame() {
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

    public Board createBoard() {
        return new Board(pieces);
    }

    public WebBoard createWebBoard() {
        return new WebBoard(pieces);
    }

    public Color getAliveKingColor() {
        return pieces.getAliveKingColor();
    }

    public Turn getTurn() {
        return turn;
    }
}
