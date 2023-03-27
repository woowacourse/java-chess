package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.PieceDao;

public class ChessGame {

    private int id;
    private State state;
    private Turn turn;
    private final Board board;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;


    public ChessGame(final Board board, final Turn turn, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.id = 0;
        this.state = State.RUN;
        this.turn = turn;
        this.board = board;
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public void movePiece(final Position source, final Position target) {
        checkPlayable();
        checkTurn(source);

        board.move(source, target);
        pieceDao.move(id, source, target);

        changeTurn();
        chessGameDao.update(this);

        checkKingCaptured();
    }

    private void checkPlayable() {
        if (!state.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void checkTurn(final Position position) {
        final Side side = board.findSideByPosition(position);
        if (!turn.isTurnValid(side)) {
            throw new IllegalArgumentException("다음 턴에 움직일 수 있습니다.");
        }
    }

    private void changeTurn() {
        turn = turn.change();
    }

    private void checkKingCaptured() {
        if (board.isKingCaptured()) {
            end();
        }
    }

    public Status getStatus() {
        return Status.from(board);
    }

    public void start() {
        this.state = State.START;
    }

    public void end() {
        this.state = State.END;
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public boolean isStart() {
        return state.isStart();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Turn getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }
}
