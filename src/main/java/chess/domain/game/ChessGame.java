package chess.domain.game;

import chess.dao.ChessGameDao;
import chess.dao.PiecesDao;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.controller.GameStatus;
import chess.domain.piece.AllPiecesGenerator;
import chess.domain.piece.Pieces;
import chess.domain.piece.Side;
import chess.domain.piece.type.Piece;
import java.util.List;

public class ChessGame {

    private static final double PAWN_DEDUCTION_SCORE = 0.5;

    private Board board;
    private final PiecesDao piecesDao;
    private final ChessGameDao chessGameDao;
    private Side turnToMove = Side.INIT;
    private GameStatus gameStatus;

    public ChessGame(final PiecesDao piecesDao,
                     final ChessGameDao chessGameDao,
                     final GameStatus gameStatus) {
        this.piecesDao = piecesDao;
        this.chessGameDao = chessGameDao;
        this.gameStatus = gameStatus;
    }

    public void startNewGame() {
        checkGameAlreadyStart(gameStatus);
        final Pieces allPieces = new Pieces(new AllPiecesGenerator());
        this.board = new Board(allPieces);
        gameStatus = GameStatus.START;
        this.turnToMove = Side.WHITE;

        chessGameDao.delete();
        chessGameDao.insertTurn(turnToMove);
        piecesDao.delete();
        piecesDao.insertAll(board.getPieces());
    }

    private void checkGameAlreadyStart(final GameStatus gameStatus) {
        if (gameStatus == GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 플레이 중에는 다시 시작할 수 없습니다.");
        }
    }

    public void load() {
        checkGameAlreadyStart(gameStatus);
        final List<Piece> findPieces = piecesDao.findAll();
        if (findPieces.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 저장된 게임 정보가 없습니다. 새로운 게임을 시작해주세요.");
        }
        final Pieces loadedPieces = new Pieces(findPieces);
        this.board = new Board(loadedPieces);
        this.turnToMove = chessGameDao.selectTurn();
        gameStatus = GameStatus.START;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        checkGameStatus();
        final Side sourcePieceSide = board.getPieceSide(sourcePosition);
        checkTurnToMoveBySide(sourcePieceSide);
        board.movePiece(sourcePosition, targetPosition);
        changeTurnToMove();
        chessGameDao.updateTurn(turnToMove);
        checkKingAlive();
    }

    private void checkGameStatus() {
        if (gameStatus != GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 말을 이동할 수 없는 단계입니다.");
        }
    }

    private void checkTurnToMoveBySide(final Side sourcePieceSide) {
        if (sourcePieceSide != turnToMove) {
            throw new IllegalArgumentException("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
        }
    }

    private void changeTurnToMove() {
        if (turnToMove == Side.BLACK) {
            turnToMove = Side.WHITE;
            return;
        }
        turnToMove = Side.BLACK;
    }

    private void checkKingAlive() {
        if (board.isKingExist(turnToMove)) {
            return;
        }
        gameStatus = GameStatus.KING_DEAD;
        piecesDao.delete();
    }

    public void exit() {
        gameStatus = GameStatus.END;
        piecesDao.delete();
        piecesDao.insertAll(board.getPieces());
    }

    public double calculateScoreBySide(Side side) {
        double scoreBySide = board.getScoreBySide(side);
        final long exceedingPawnCount = board.getCountOfPawnsOnSameFileBySide(side);
        scoreBySide = scoreBySide - exceedingPawnCount * PAWN_DEDUCTION_SCORE;
        return scoreBySide;
    }

    public Side getWinner() {
        if (GameStatus.KING_DEAD != gameStatus) {
            throw new IllegalArgumentException("[ERROR] 킹이 죽지 않아 승자가 가려지지 않았습니다..");
        }
        if (turnToMove == Side.BLACK) {
            return Side.WHITE;
        }
        return Side.BLACK;
    }

    public GameStatus status() {
        return this.gameStatus;
    }

    public Board getBoard() {
        return this.board;
    }
}
