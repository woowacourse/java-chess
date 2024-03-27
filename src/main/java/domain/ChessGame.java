package domain;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.position.Position;
import domain.command.Command;
import domain.command.Commands;
import domain.command.End;
import domain.command.Ready;
import domain.piece.Color;
import domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

public class ChessGame {
    private final Board board;
    private Command command;

    public ChessGame() {
        this.command = Ready.getInstance();
        this.board = new Board(BoardInitiator.init());
    }

    public void execute(final String value) {
        this.command = this.command.next(Commands.from(value));

        if (this.command.isMove()) {
            final StringTokenizer tokens = skipFirstToken(value);
            board.move(tokens.nextToken(), tokens.nextToken());

        }
        if (board.isKingDead()) {
            this.command = command.next(End.getInstance());
        }
    }

    private StringTokenizer skipFirstToken(final String command) {
        final StringTokenizer stringTokenizer = new StringTokenizer(command);
        stringTokenizer.nextToken();
        return stringTokenizer;
    }

    public boolean isRunning() {
        return command.isRunning();
    }

    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(board.getSquares());
    }

    public Color getColor() {
        return board.getColor();
    }

    public Double calculateWhiteScore() {
        return board.calculateScore(Color.WHITE);
    }

    public Double calculateBlackScore() {
        return board.calculateScore(Color.BLACK);
    }

    public Color winnerColor() {
        if (board.isKingDead()) {
            return board.getColor();
        }
        throw new IllegalCallerException();
    }
}
