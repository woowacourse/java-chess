package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.exception.WrongPositionException;
import chess.domain.piece.Color;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.positions.Positions;

import java.util.List;

public class ChessGame {
    private Pieces pieces;
    private Turn turn;

    public ChessGame() {
        pieces = PiecesFactory.create();
        turn = new Turn(Color.WHITE);
    }

    public void move(Position start, Position end) {
        if (start == end) {
            throw new WrongPositionException(start.toString());
        }
        pieces.move(start, end, turn.getColor());
        turn = turn.change();
    }

    public void moveAll(List<MoveCommand> commands) {
        for (MoveCommand command : commands) {
            Position start = PositionFactory.of(command.getFirstCommand());
            Position end = PositionFactory.of(command.getSecondCommand());

            move(start, end);
        }
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

    public Color getAliveKingColor() {
        return pieces.getAliveKingColor();
    }

    public Turn getTurn() {
        return turn;
    }
}
