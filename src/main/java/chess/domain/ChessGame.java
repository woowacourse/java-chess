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
    private static final String GAME_END_EXCEPTION_MESSAGE = "게임이 끝난 후에는 경기를 더 진행할 수 없습니다.";


    private final PieceDao pieceDao = new PieceDao();
    private final GameDao gameDao = new GameDao();

    private boolean forceEndFlag;
    private String gameId;

    private ChessGame(String gameId) {
        if (gameDao.findById(gameId)) {
            forceEndFlag = gameDao.findForceEndFlagById(gameId);
            this.gameId = gameId;
            return;
        }
        new ChessGame(new Pieces(List.of()), gameId);
    }

    private ChessGame(Pieces chessmen, String gameId) {
        forceEndFlag = gameDao.create(gameId);
        this.gameId = gameId;
        pieceDao.saveAll(chessmen.getPieces(), gameId);
    }

    private ChessGame() {
    }

    public static ChessGame createOrGet(String gameId) {
        return new ChessGame(gameId);
    }

    public static ChessGame of(Pieces chessmen, String gameId) {
        return new ChessGame(chessmen, gameId);
    }

    public static ChessGame of() {
        return new ChessGame();
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Pieces chessmen = pieceDao.findAll(gameId);

        Piece sourcePiece = chessmen.extractPiece(Position.of(dto.getSource()));
        Position toPosition = Position.of(dto.getTarget());

        checkEnd();
        checkMovable(sourcePiece, toPosition);
        moveOrKill(sourcePiece, toPosition);

        changeTurn();
    }

    private void checkEnd() {
        if (isEnd()) {
            throw new IllegalArgumentException(GAME_END_EXCEPTION_MESSAGE);
        }
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        validateTurn(sourcePiece);
        Pieces chessmen = pieceDao.findAll(gameId);
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
        Piece targetPiece = pieceDao.findByPosition(toPosition.getPosition(), gameId);
        if (sourcePiece.isFriendly(targetPiece)) {
            throw new IllegalArgumentException(FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE);
        }
    }

    private void checkPath(Piece sourcePiece, Position toPosition) {
        Pieces chessmen = pieceDao.findAll(gameId);
        List<Position> positions = sourcePiece.getPositionsInPath(toPosition);
        if (chessmen.isAnyPieceExistInPositions(positions)) {
            throw new IllegalArgumentException(PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE);
        }
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        Pieces chessmen = pieceDao.findAll(gameId);
        if (chessmen.isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition);
            return;
        }
        Position sourcePosition = sourcePiece.getPosition();
        sourcePiece.move(toPosition);
        pieceDao.updateByPosition(sourcePosition.getPosition(), toPosition.getPosition(), gameId);
    }

    private void killEnemy(Piece sourcePiece, Position toPosition) {
        Position sourcePosition = sourcePiece.getPosition();
        Piece targetPiece = pieceDao.findByPosition(toPosition.getPosition(), gameId);
        sourcePiece.kill(targetPiece);

        pieceDao.deleteByPosition(toPosition.getPosition(), gameId);
        pieceDao.updateByPosition(sourcePosition.getPosition(), toPosition.getPosition(), gameId);
    }

    public boolean isEnd() {
        Pieces chessmen = pieceDao.findAll(gameId);
        return forceEndFlag || chessmen.hasLessThanTotalKingCount();
    }

    public void forceEnd(String gameId) {
        gameDao.updateForceEndFlagById(gameId);
        forceEndFlag = true;
    }

    public GameResultDto calculateGameResult() {
        Pieces chessmen = pieceDao.findAll(gameId);
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        Color winner = findWinner();
        double whiteScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.WHITE));
        double blackScore = scoreCalculator.calculate(chessmen.extractPiecesOf(Color.BLACK));

        return new GameResultDto(winner, whiteScore, blackScore);
    }

    public BoardMapDto toBoard(String gameId) {
        Pieces chessmen = pieceDao.findAll(gameId);
        Map<String, Object> model = new HashMap<>();

        for (Piece piece : chessmen.getPieces()) {
            ChessmenDto chessmenDto = new ChessmenDto(piece.getPosition(), piece.getName(), piece.getColor());
            model.put(chessmenDto.getPosition(), chessmenDto);
        }
        return new BoardMapDto(model);
    }

    private Color findWinner() {
        Pieces chessmen = pieceDao.findAll(gameId);
        return chessmen.findKingWinner();
    }

    public Pieces getChessmen() {
        return pieceDao.findAll(gameId);
    }

    public void clean(String gameId) {
        pieceDao.deleteAll(gameId);
        gameDao.deleteById(gameId);
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + pieceDao.findAll(gameId) + '}';
    }

}
