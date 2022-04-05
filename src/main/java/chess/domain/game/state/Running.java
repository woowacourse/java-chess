package chess.domain.game.state;

import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

public class Running extends Waiting {

    private final ChessBoard board;
    private Player player;

    public Running(ChessBoard board, Player player) {
        this.board = board;
        this.player = player;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public GameState move(Position source, Position target) {
        validatePosition(source);
        Piece targetPiece = board.getPiece(target);
        board.move(source, target);
        player = player.change();

        if (board.isKing(targetPiece)) {
            return new End();
        }

        return new Running(board, player);
    }

    private void validatePosition(Position position) {
        board.validateExist(position);
        checkMyPiece(board.getPiece(position));
    }

    private void checkMyPiece(Piece piece) {
        if (!player.hasPiece(piece)) {
            throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
        }
    }

    @Override
    public Map<Color, Double> status() {
        return board.computeScore();
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
