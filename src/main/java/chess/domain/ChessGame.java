package chess.domain;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.dto.BoardMapDto;
import chess.dto.ChessmenDto;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private static final String TURN_EXCEPTION_MESSAGE = "턴은 백색 말부터 시작해 한번씩 움직일 수 있습니다.";
    private static final String FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE = "이동하려는 위치에 아군 말이 있습니다.";
    private static final String PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE = "가는 길목에 다른 말이 있어 이동할 수 없습니다.";

    private boolean forceEndFlag = false;
    private int gameId;

    private final PieceDao pieceDao = new PieceDao();
    private final GameDao gameDao = new GameDao();

    private ChessGame(Pieces chessmen) {
        gameId = gameDao.create();
        pieceDao.saveAll(chessmen.getPieces(), gameId);
    }

    private ChessGame() {
    }

    public static ChessGame of(Pieces chessmen) {
        return new ChessGame(chessmen);
    }

    public static ChessGame of() {
        return new ChessGame();
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Pieces chessmen = pieceDao.findAll();

        Piece sourcePiece = chessmen.extractPiece(Position.of(dto.getSource()));
        Position toPosition = Position.of(dto.getTarget());

        checkMovable(sourcePiece, toPosition);

        moveOrKill(sourcePiece, toPosition);

        changeTurn();
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        validateTurn(sourcePiece);
        Pieces chessmen = pieceDao.findAll();
        if (chessmen.isOccupied(toPosition)) {
            checkOccupiedByFriendly(sourcePiece, toPosition);
        }
        checkPath(sourcePiece, toPosition);
    }

    private void validateTurn(Piece sourcePiece) {
        if (sourcePiece.isSameColor(gameDao.findTurnById(gameId))) {
            throw new IllegalArgumentException(TURN_EXCEPTION_MESSAGE);
        }
    }

    private void changeTurn() {
        Color turn = gameDao.findTurnById(gameId);
        gameDao.updateTurnById(gameId, turn.nextTurn());
    }


    private void checkOccupiedByFriendly(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = pieceDao.findByPosition(toPosition.getPosition());
        if (sourcePiece.isFriendly(targetPiece)) {
            throw new IllegalArgumentException(FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE);
        }
    }

    private void checkPath(Piece sourcePiece, Position toPosition) {
        Pieces chessmen = pieceDao.findAll();
        List<Position> positions = sourcePiece.getPositionsInPath(toPosition);
        if (chessmen.isAnyPieceExistInPositions(positions)) {
            throw new IllegalArgumentException(PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE);
        }
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        Pieces chessmen = pieceDao.findAll();
        if (chessmen.isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition);
            return;
        }
        Position sourcePosition = sourcePiece.getPosition();
        sourcePiece.move(toPosition);
        pieceDao.updateByPosition(sourcePosition.getPosition(), toPosition.getPosition());
    }

    private void killEnemy(Piece sourcePiece, Position toPosition) {
        Position sourcePosition = sourcePiece.getPosition();
        Piece targetPiece = pieceDao.findByPosition(toPosition.getPosition());
        sourcePiece.kill(targetPiece);

        pieceDao.deleteByPosition(toPosition.getPosition());
        pieceDao.updateByPosition(sourcePosition.getPosition(), toPosition.getPosition());
    }

    public boolean isEnd() {
        Pieces chessmen = pieceDao.findAll();
        return chessmen.hasLessThanTotalKingCount() || forceEndFlag;
    }

    public void forceEnd() {
        forceEndFlag = true;
    }

    public GameResultDto calculateGameResult() {
        Pieces chessmen = pieceDao.findAll();
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        Color winner = findWinner();
        double whiteScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.WHITE));
        double blackScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.BLACK));

        return new GameResultDto(winner, whiteScore, blackScore);
    }

    public BoardMapDto toBoard() {
        Pieces chessmen = pieceDao.findAll();
        Map<String, Object> model = new HashMap<>();

        for (Piece piece : chessmen.getPieces()) {
            ChessmenDto chessmenDto = new ChessmenDto(piece.getPosition(), piece.getName(), piece.getColor());
            model.put(chessmenDto.getPosition(), chessmenDto);
        }
        return new BoardMapDto(model);
    }

    private Color findWinner() {
        Pieces chessmen = pieceDao.findAll();
        return chessmen.findKingWinner();
    }

    public Pieces getChessmen() {
        return pieceDao.findAll();
    }

    public void clean() {
        pieceDao.deleteAll();
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + pieceDao.findAll() + '}';
    }

}
