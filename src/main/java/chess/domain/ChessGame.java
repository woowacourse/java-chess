package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.ScoreCalculator;
import chess.domain.piece.piece.Pieces;
import chess.domain.piece.position.Position;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import java.util.List;

public class ChessGame {

    private static final String FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE = "이동하려는 위치에 아군 말이 있습니다.";
    private static final String PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE = "가는 길목에 다른 말이 있어 이동할 수 없습니다.";

    private final Pieces chessmen;

    private ChessGame(Pieces chessmen) {
        this.chessmen = chessmen;
    }

    public static ChessGame of(Pieces chessmen) {
        return new ChessGame(chessmen);
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = chessmen.extractPiece(Position.of(dto.source()));
        Position toPosition = Position.of(dto.target());

        checkMovable(sourcePiece, toPosition);

        moveOrKill(sourcePiece, toPosition);
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        if (chessmen.isOccupied(toPosition)) {
            checkOccupiedByFriendly(sourcePiece, toPosition);
        }

        checkPath(sourcePiece, toPosition);
    }

    private void checkOccupiedByFriendly(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = chessmen.extractPiece(toPosition);
        if (sourcePiece.isFriendly(targetPiece)) {
            throw new IllegalArgumentException(FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE);
        }
    }

    private void checkPath(Piece sourcePiece, Position toPosition) {
        List<Position> positions = sourcePiece.getPositionsInPath(toPosition);
        if (chessmen.isAnyPieceExistInPositions(positions)) {
            throw new IllegalArgumentException(PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE);
        }
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        if (chessmen.isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition);
            return;
        }
        sourcePiece.move(toPosition);
    }

    private void killEnemy(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = chessmen.extractPiece(toPosition);
        sourcePiece.kill(targetPiece);
        chessmen.remove(targetPiece);
    }

    public boolean isEnd() {
        return chessmen.hasLessThanTotalKingCount();
    }

    public GameResultDto calculateGameResult() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        double whiteScore = scoreCalculator.calculate(chessmen.extractPiecesOf(WHITE));
        double blackScore = scoreCalculator.calculate(chessmen.extractPiecesOf(BLACK));

        return new GameResultDto(whiteScore, blackScore);
    }

    public Pieces getChessmen() {
        return chessmen;
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + chessmen + '}';
    }

}
