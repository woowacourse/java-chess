package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.position.PositionUtil.FILES_TOTAL_SIZE;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessGame {

    private static final int TOTAL_KING_COUNT = 2;

    private final List<Piece> chessmen;

    private ChessGame(List<Piece> chessmen) {
        this.chessmen = new ArrayList<>(chessmen);
    }

    public static ChessGame of(List<Piece> chessmen) {
        return new ChessGame(chessmen);
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = extractPiece(Position.of(dto.source()));
        Position toPosition = Position.of(dto.target());

        checkMovable(sourcePiece, toPosition);

        moveOrKill(sourcePiece, toPosition);
    }

    private Piece extractPiece(Position position) {
        return chessmen.stream()
            .filter(piece -> piece.isAt(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당위치에는 말이 없습니다."));
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        sourcePiece.checkReachable(toPosition);

        if (isOccupied(toPosition)) {
            checkOccupiedByFriendly(sourcePiece, toPosition);
        }

        checkPath(sourcePiece, toPosition);
    }

    private boolean isOccupied(Position position) {
        return chessmen.stream()
            .anyMatch(piece -> piece.isAt(position));
    }

    private void checkOccupiedByFriendly(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = extractPiece(toPosition);
        if (sourcePiece.isFriendly(targetPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 아군 말이 있습니다.");
        }
    }


    private void checkPath(Piece sourcePiece, Position toPosition) {
        List<Position> positions = sourcePiece.getPositionsInPath(toPosition);
        if (isAnyPieceExistInPath(positions)) {
            throw new IllegalArgumentException("가는 길목에 다른 말이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isAnyPieceExistInPath(List<Position> positions) {
        for (Position position : positions) {
            return chessmen.stream()
                .anyMatch(piece -> piece.isAt(position));
        }
        return false;
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        if (isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition);
            return;
        }
        sourcePiece.move(toPosition);
    }

    private void killEnemy(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = extractPiece(toPosition);
        sourcePiece.kill(targetPiece);
        chessmen.remove(targetPiece);
    }

    public boolean isEnd() {
        int kingCount = (int) chessmen.stream()
            .filter(Piece::isKing)
            .count();

        return kingCount < TOTAL_KING_COUNT;
    }

    public GameResultDto calculateGameResult() {
        double whiteScore = calculateScore(WHITE);
        double blackScore = calculateScore(BLACK);

        return new GameResultDto(whiteScore, blackScore);
    }

    private double calculateScore(Color color) {
        List<Piece> sameColorPieces = extractPiecesOf(color);
        List<Position> pawnPositions = extractPawnPositions(sameColorPieces);

        double defaultScore = sameColorPieces.stream()
            .map(Piece::score)
            .reduce(0.0, Double::sum);

        return defaultScore - (calculateSameFilePawnCount(pawnPositions) / 2);
    }

    private double calculateSameFilePawnCount(List<Position> pawnPositions) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
            .map(fileIdx -> extractSameFilePositionCount(pawnPositions, fileIdx))
            .filter(count -> count > 1)
            .reduce(0, Integer::sum);
    }

    private List<Piece> extractPiecesOf(Color color) {
        return chessmen.stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> extractPawnPositions(List<Piece> sameColorPieces) {
        return sameColorPieces.stream()
            .filter(Piece::isPawn)
            .map(Piece::getPosition)
            .collect(Collectors.toUnmodifiableList());
    }

    private int extractSameFilePositionCount(List<Position> positions, int fileIdx) {
        return (int) positions.stream()
            .filter(position -> position.hasSameFileIdx(fileIdx))
            .count();
    }

    public List<Piece> getChessmen() {
        return chessmen;
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + chessmen + '}';
    }

}
