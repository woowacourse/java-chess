package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Color turn;
    private GameStatus status;

    private ChessGame(Board board, Color turn, GameStatus status) {
        this.board = board;
        this.turn = turn;
        this.status = status;
    }

    public static ChessGame create() {
        Board newBoard = Board.create();
        Color startTurn = Color.WHITE;
        GameStatus startStatus = GameStatus.START;

        return new ChessGame(newBoard, startTurn, startStatus);
    }

    public static ChessGame load(Board board, Color turn, GameStatus status) {
        return new ChessGame(board, turn, status);
    }

    public void start() {
        if (status != GameStatus.START) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }
        status = GameStatus.MOVE;
    }
    
    public void move(final List<String> arguments) {
        if (status != GameStatus.MOVE) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
        Position source = Position.from(arguments.get(0));
        Position destination = Position.from(arguments.get(1));

        checkPieceCanMove(source, destination);
        checkCatchKing(destination);
        board.replace(source, destination);
        turn = turn.reverse();
    }

    private void checkPieceCanMove(final Position source, final Position destination) {
        board.validateSourcePiece(source, turn);
        Piece sourcePiece = board.getPieceAtPosition(source);

        sourcePiece.canMove(source, destination);
        board.checkSameColor(destination, turn);
        checkRoute(source, destination, sourcePiece);
        checkPawnMove(source, destination, sourcePiece);
    }

    private void checkCatchKing(Position destination) {
        if (board.getPieceAtPosition(destination).getType() == PieceType.KING) {
            status = GameStatus.CATCH;
        }
    }
    
    private void checkPawnMove(final Position source, final Position destination, final Piece sourcePiece) {
        if (sourcePiece.getType() == PieceType.PAWN) {
            board.checkRestrictionForPawn(source, destination, turn);
        }
    }
    
    private void checkRoute(final Position source, final Position destination, final Piece sourcePiece) {
        if (!(sourcePiece.getType() == PieceType.KNIGHT)) {
            board.checkBetweenRoute(source, destination);
        }
    }

    public ScoreBoard status() {
        if (status == GameStatus.START) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
        return board.getScoreBoard();
    }

    public void end() {
        if (status != GameStatus.MOVE) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
        status = GameStatus.END;
    }

    public boolean isEnd() {
        return status == GameStatus.END;
    }

    public boolean isCatch() {
        return status == GameStatus.CATCH;
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return turn;
    }

    public GameStatus getStatus() {
        return status;
    }
}

