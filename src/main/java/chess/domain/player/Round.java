package chess.domain.player;

import chess.domain.board.ChessBoardFactory;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;

import java.util.Map;
import java.util.Queue;

public class Round {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Command command;

    public Round(final State white, final State black, final Command command) {
        this(new WhitePlayer(white), new BlackPlayer(black), command);
    }

    public Round(final Player whitePlayer, final Player blackPlayer, final Command command) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.command = command;
    }

    private void moveByTurn(final Position sourcePosition, final Position targetPosition) {
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

    private void checkPieces(final State state, final Target target) {
        if (state.isKing(target.getPosition())) {
            this.command = this.command.end();
        }
        if (state.findPiece(target.getPosition()).isPresent()) {
            state.removePiece(target.getPosition());
        }
    }

    public Map<Position, Piece> getBoard() {
        Map<Position, Piece> board = ChessBoardFactory.initializeBoard();
        Pieces whitePieces = whitePlayer.getPieces();
        Pieces blackPieces = blackPlayer.getPieces();

        whitePieces.getPieces().forEach(piece -> board.put(piece.getPosition(), piece));
        blackPieces.getPieces().forEach(piece -> board.put(piece.getPosition(), piece));

        return board;
    }

    public boolean isEnd() {
        return command.isEnd();
    }

    public void execute(final Queue<String> commands) {
        this.command = this.command.execute(commands.poll());
        if (this.command.isMove()) {
            this.command = command.ready();
            Position sourcePosition = Position.from(commands.poll());
            Position targetPosition = Position.from(commands.poll());
            moveByTurn(sourcePosition, targetPosition);
        }
    }

    public double calculateScore() {
        Player currentPlayer = currentPlayer();
        return score(currentPlayer.getState().pieces());
    }

    public String currentPlayerName() {
        return currentPlayer().getName();
    }

    private double score(final Pieces pieces) {
        return pieces.calculateScore();
    }

    private Player currentPlayer() {
        if (blackPlayer.isFinish()) {
            return blackPlayer;
        }
        return whitePlayer;
    }

    public boolean isStatus() {
        return command.isStatus();
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }
}
