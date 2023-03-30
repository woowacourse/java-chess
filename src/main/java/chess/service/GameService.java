package chess.service;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dao.TurnDaoImpl;
import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.Position;
import chess.domain.factory.PlayersFactory;

import java.math.BigInteger;
import java.util.List;

public class GameService {

    private final Players players;
    private final PieceDao pieceDao;

    public GameService() {
        this.players = PlayersFactory.createChessBoard();
        this.pieceDao = new PieceDaoImpl();
    }

    public void movePiece(final String inputMovablePiece, final String inputTargetPosition) {
        Position findPosition = players.findPositionByInputPoint(inputMovablePiece);
        Position targetPosition = Position.from(inputTargetPosition);
        players.validatePosition(inputMovablePiece);
        move(findPosition, targetPosition);
        changeTurn();
    }

    private void move(final Position sourcePosition, final Position targetPosition) {
        validateMovingRoute(sourcePosition, targetPosition);

        Player findPlayer = players.findPlayerByPosition(sourcePosition);
        Piece changedPiece = findPlayer.movePiece(players.getAllPosition(), sourcePosition, targetPosition);

        pieceDao.updatePosition(changedPiece, sourcePosition);

        Player anotherPlayer = players.getAnotherPlayer(findPlayer);
        anotherPlayer.removePiece(targetPosition)
                .ifPresent(piece -> pieceDao.deletePieceByColor(piece, anotherPlayer.getColor()));
    }

    private void validateMovingRoute(final Position fromPosition, final Position toPosition) {
        List<Integer> directionVector = calculateDirectionVector(fromPosition, toPosition);
        validateEachPositions(fromPosition, toPosition, directionVector);
    }

    private List<Integer> calculateDirectionVector(final Position fromPosition, final Position toPosition) {
        int diffFile = toPosition.calculateFileDistance(fromPosition);
        int diffRank = toPosition.calculateRankDistance(fromPosition);

        BigInteger rankAndFileGcd = BigInteger.valueOf(Math.abs(diffFile)).gcd(BigInteger.valueOf(Math.abs(diffRank)));
        int fileDirection = diffFile / rankAndFileGcd.intValue();
        int rankDirection = diffRank / rankAndFileGcd.intValue();
        return List.of(fileDirection, rankDirection);
    }

    private void validateEachPositions(final Position fromPosition, final Position toPosition, final List<Integer> directionVector) {
        Integer fileDirection = directionVector.get(0);
        Integer rankDirection = directionVector.get(1);
        Position tempPosition = fromPosition.move(fileDirection, rankDirection);

        while (!tempPosition.equals(toPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (players.isAlreadyExistPiece(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private void changeTurn() {
        players.updateTurn();
        new TurnDaoImpl().update(players.getCurrent());
    }

    public boolean everyKingAlive() {
        return players.everyKingAlive();
    }

    public String getWinnerPlayerColor() {
        return players.getWinnerColorName();
    }

    public List<Piece> getPiecesByColor(Color color) {
        return players.getPiecesByColor(color);
    }

    public double getScore() {
        return players.calculateScore();
    }

    public void updateTurn(Color color) {
        pieceDao.deleteAll();
        new TurnDaoImpl().update(color);
    }
}
