package domain;

import db.Movement;
import db.MovementDao;
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
        try {
            board.move(source, target);
            chessService.save(source, target, board.getPiece(target).getName(), board.getPiece(target).getColor().name());
        } catch (final Exception e) {
            throw e;
        }
    }

    public void recover() {
        final MovementDao movementDao = new MovementDao();
        final List<Movement> all = movementDao.findAll();
        for (final Movement movement : all) {
            board.move(movement.sourceFile(), movement.sourceRank(), movement.targetFile(), movement.targetRank());
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
