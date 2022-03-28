package chess.domain.game.state;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;

public class RunningGame extends StartedGame {

    private final ChessBoard board;
    private Player player;

    public RunningGame(ChessBoard board, Player player) {
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

        if (board.isKing(target)) {
            return new EndGame();
        }

        board.move(source, target);
        player = player.change();

        return new RunningGame(board, player);
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
}
