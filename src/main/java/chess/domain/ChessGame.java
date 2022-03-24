package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.Pawn.BLACK_PAWN_INIT_RANK;
import static chess.domain.piece.Pawn.WHITE_PAWN_INIT_RANK;
import static chess.domain.piece.position.PositionUtil.FILES_TOTAL_SIZE;

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
    private static final int BLACK_FIRST_RANK = 7;
    private static final int WHITE_FIRST_RANK = 0;

    private final List<Piece> chessmen = new ArrayList<>(32);

    public ChessGame() {
        chessmen.addAll(initStrongMen(BLACK, BLACK_FIRST_RANK));
        chessmen.addAll(initPawns(BLACK, BLACK_PAWN_INIT_RANK));
        chessmen.addAll(initPawns(WHITE, WHITE_PAWN_INIT_RANK));
        chessmen.addAll(initStrongMen(WHITE, WHITE_FIRST_RANK));
    }

    private List<Piece> initPawns(Color color, int initRankIdx)  {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Position.of(fileIdx, initRankIdx))
                .map(position -> new Pawn(color, position))
                .collect(Collectors.toList());
    }

    private List<Piece> initStrongMen(Color color, int initRankIdx) {
        return List.of(
                new Rook(color, Position.of(0, initRankIdx)),
                new Knight(color, Position.of(1, initRankIdx)),
                new Bishop(color, Position.of(2, initRankIdx)),
                new Queen(color, Position.of(3, initRankIdx)),
                new King(color, Position.of(4, initRankIdx)),
                new Bishop(color, Position.of(5, initRankIdx)),
                new Knight(color, Position.of(6, initRankIdx)),
                new Rook(color, Position.of(7, initRankIdx)));
    }

    public void moveChessmen(MovePositionCommandDto dto) {
        Piece sourcePiece = extractPiece(Position.of(dto.source()));
        Position toPosition = Position.of(dto.target());

        if (checkOccupied(toPosition)) {
            Piece targetPiece = extractPiece(toPosition);
            sourcePiece.kill(targetPiece);
            chessmen.remove(targetPiece);
            return;
        }

        sourcePiece.move(toPosition);
    }

    private Piece extractPiece(Position position) {
        return chessmen.stream()
                .filter(piece -> piece.getPosition() == position)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    private boolean checkOccupied(Position position) {
        return chessmen.stream()
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
