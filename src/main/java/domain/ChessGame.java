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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import service.ChessService;

public class ChessGame {
    private final ChessService chessService;
    private final Board board;
    private Command command;

    public ChessGame() {
        this.chessService = new ChessService();
        this.command = Ready.getInstance();
        this.board = new Board(BoardInitiator.init());
    }

    public void execute(final String value) {
        this.command = this.command.next(Commands.from(value));

        if (this.command.isMove()) {
            moveBoard(value);

        }
        if (board.isKingDead()) {
            this.command = command.next(End.getInstance());
        }
    }

    private void moveBoard(final String value) {
        final StringTokenizer tokens = skipFirstToken(value);
        final String source = tokens.nextToken();
        final String target = tokens.nextToken();
        board.move(source, target);
        chessService.save(source, target, board.getPiece(target).getName(), board.getPiece(target).getColor().name());
    }

    public void recover() {
        final List<Entry<Position, Position>> positions = chessService.findPositions();
        for (final var movement : positions) {
            board.move(movement.getKey(), movement.getValue());
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

    public Double calculateWhiteScore() {
        return board.calculateScore(Color.WHITE);
    }

    public Double calculateBlackScore() {
        return board.calculateScore(Color.BLACK);
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public boolean isKingDeadOf(final Color color) {
        return board.isKingDeadOf(color);
    }

    public void reset() {
        chessService.removeAll();
    }

    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(board.getSquares());
    }

    public Color getColor() {
        return board.getColor();
    }
}
