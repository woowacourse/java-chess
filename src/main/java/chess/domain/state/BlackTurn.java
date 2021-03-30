package chess.domain.state;

import chess.domain.grid.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public final class BlackTurn extends Playing {
    @Override
    public GameState start() {
        throw new IllegalArgumentException("게임이 이미 시작하였습니다.");
    }

    @Override
    public GameState status() {
        return new Status();
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public final GameState move(final ChessGame game, final Position sourcePosition, final Position targetPosition) {
        Piece sourcePiece = game.grid().piece(sourcePosition);
        Piece targetPiece = game.grid().piece(targetPosition);
        validateSourcePieceIsEmpty(sourcePiece);
        validateIfBlack(sourcePiece);
        if(isKingCaught(targetPiece)){
            game.winner(sourcePiece.color());
            return new End();
        }
        sourcePiece.validateRoute(targetPiece, game.grid().lines());
        game.grid().update(sourcePiece, targetPiece);
        return new WhiteTurn();
    }

    private void validateIfBlack(final Piece sourcePiece) {
        if (sourcePiece.color() != Color.BLACK) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
