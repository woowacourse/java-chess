package chess.domain.player;

import chess.domain.board.ChessBoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;

import java.util.Map;

import static chess.view.Command.*;

public class Round {
    private final Player whitePlayer;
    private final Player blackPlayer;

    public Round(final State white, final State black) {
        this(new WhitePlayer(white), new BlackPlayer(black));
    }

    public Round(final Player whitePlayer, final Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public void move(final String command, final String sourceText, final String targetText) {
        if (isMove(command)) {
            Position sourcePosition = Position.find(sourceText);
            Position targetPosition = Position.find(targetText);
            moveByTurn(sourcePosition, targetPosition);
        }
    }

    private void moveByTurn(Position sourcePosition, Position targetPosition) {
        if (whitePlayer.isFinish()) {
            Source source = Source.valueOf(sourcePosition, blackPlayer.getState());
            Target target = Target.valueOf(source, targetPosition, blackPlayer.getState());
            blackPlayer.move(source, target, whitePlayer.getState());
            whitePlayer.toRunningState(blackPlayer.getState());
            checkPieces(whitePlayer.getState(), target);
            return;
        }
        Source source = Source.valueOf(sourcePosition, whitePlayer.getState());
        Target target = Target.valueOf(source, targetPosition, whitePlayer.getState());
        whitePlayer.move(source, target, blackPlayer.getState());
        blackPlayer.toRunningState(whitePlayer.getState());
        checkPieces(blackPlayer.getState(), target);
    }

    private void checkPieces(State state, Target target) {
        if (state.findPiece(target.getPosition()).isPresent()) {
            state.removePiece(target.getPosition());
        }
    }

    private boolean isMove(final String command) {
        return MOVE.isSame(command);
    }

    public Map<Position, Piece> getBoard() {
        Map<Position, Piece> board = ChessBoardFactory.initializeBoard();
        Pieces whitePieces = whitePlayer.getState().pieces();
        Pieces blackPieces = blackPlayer.getState().pieces();

        whitePieces.getPieces().forEach(piece -> board.put(piece.getPosition(), piece));
        blackPieces.getPieces().forEach(piece -> board.put(piece.getPosition(), piece));

        return board;
    }
}
