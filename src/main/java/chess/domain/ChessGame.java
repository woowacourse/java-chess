package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.position.PositionUtil.FILES_TOTAL_SIZE;
import static chess.domain.piece.position.PositionUtil.VALID_FILES;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.position.Position;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessGame {

    private static final int TOTAL_KING_COUNT = 2;

    private final List<Piece> chessmen = new ArrayList<>(32);

    public ChessGame() {
        chessmen.addAll(initBlackStrongMen());
        chessmen.addAll(initBlackPawns());
        chessmen.addAll(initWhitePawns());
        chessmen.addAll(initWhiteStrongMen());
    }

    private List<Piece> initBlackStrongMen() {
        return List.of(
            new Rook(BLACK, Position.of("a8")),
            new Knight(BLACK, Position.of("b8")),
            new Bishop(BLACK, Position.of("c8")),
            new Queen(BLACK, Position.of("d8")),
            new King(BLACK, Position.of("e8")),
            new Bishop(BLACK, Position.of("f8")),
            new Knight(BLACK, Position.of("g8")),
            new Rook(BLACK, Position.of("h8")));
    }

    private List<Piece> initBlackPawns() {
        return VALID_FILES.chars()
            .mapToObj(rank -> (char) rank + "" + Pawn.BLACK_INIT_RANK)
            .map(positionKey -> new Pawn(BLACK, Position.of(positionKey)))
            .collect(Collectors.toList());
    }

    private List<Piece> initWhitePawns() {
        return VALID_FILES.chars()
            .mapToObj(rank -> (char) rank + "" + Pawn.WHITE_INIT_RANK)
            .map(positionKey -> new Pawn(WHITE, Position.of(positionKey)))
            .collect(Collectors.toList());
    }

    private List<Piece> initWhiteStrongMen() {
        return List.of(
            new Rook(WHITE, Position.of("a1")),
            new Knight(WHITE, Position.of("b1")),
            new Bishop(WHITE, Position.of("c1")),
            new Queen(WHITE, Position.of("d1")),
            new King(WHITE, Position.of("e1")),
            new Bishop(WHITE, Position.of("f1")),
            new Knight(WHITE, Position.of("g1")),
            new Rook(WHITE, Position.of("h1")));
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = extractPiece(Position.of(dto.source()));
        Position toPosition = Position.of(dto.target());

        checkMovable(sourcePiece, toPosition);

        moveOrKill(sourcePiece, toPosition);
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        if (isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition);
            return;
        }
        sourcePiece.move(toPosition);
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        sourcePiece.checkReachable(toPosition);

        if (isOccupied(toPosition)) {
            checkOccupiedByFriendly(sourcePiece, toPosition);
        }

        checkPath(sourcePiece, toPosition);
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
                .anyMatch(piece -> piece.getPosition() == position);
        }
        return false;
    }

    private void killEnemy(Piece sourcePiece, Position toPosition) {
        Piece targetPiece = extractPiece(toPosition);
        sourcePiece.kill(targetPiece);
        chessmen.remove(targetPiece);
    }

    private Piece extractPiece(Position position) {
        return chessmen.stream()
            .filter(piece -> piece.getPosition() == position)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당위치에는 말이 없습니다."));
    }

    private boolean isOccupied(Position position) {
        return chessmen.stream()
            //TODO getter
            .anyMatch(piece -> piece.getPosition() == position);
    }

    public boolean isEnd() {
        int kingCount = (int) chessmen.stream()
            .filter(Piece::isKing)
            .count();

        return kingCount < TOTAL_KING_COUNT;
    }

    public GameResultDto getGameResult() {
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
            .filter(piece -> piece.getColor() == color)
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
