package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class Running extends State {
    protected Running(final ChessGame ChessGame) {
        super(ChessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public State move(final Position source, final Position target) {
        Piece capturePiece = chessGame.move(source, target);
        if (capturePiece.isSamePieceType(PieceType.KING)) {
            return new GameEnd(chessGame);
        }
        return new Running(chessGame.next());
    }

    @Override
    public State start() {
        return new Running(ChessGame.create());
    }

    @Override
    public State end() {
        return new End(chessGame);
    }

    @Override
    public double calculateScore(Color color) {
        return chessGame.calculateScore(color);
    }

    @Override
    public Color getColor() {
        return chessGame.getColor();
    }
}
