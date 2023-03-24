package domain.game;

import static domain.game.Side.BLACK;
import static domain.game.Side.WHITE;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piece.PieceCategory;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Game {
    private static final double PAWN_ON_SAME_FILE_PENALTY = 0.5;
    private static final int LOWER_LIMIT_PAWN_PENALTY = 2;
    private static final int NO_PENALTY = 0;

    private final Map<Position, Piece> chessBoard;
    private Side sideOfTurn;

    public Game(Map<Position, Piece> chessBoard, Side sideOfTurn) {
        this.chessBoard = chessBoard;
        this.sideOfTurn = sideOfTurn;
    }

    public static Game create() {
        return new Game(new ChessBoardGenerator().generate(), WHITE);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        validateMoving(sourcePosition, targetPosition, sourcePiece);
        changeTurn();
        movePiece(sourcePosition, targetPosition, sourcePiece);
    }

    private void changeTurn() {
        if (this.sideOfTurn.equals(WHITE)) {
            this.sideOfTurn = BLACK;
            return;
        }
        if (this.sideOfTurn.equals(BLACK)) {
            this.sideOfTurn = WHITE;
            return;
        }
        throw new IllegalStateException("서버 내부 에러 - Game의 sideOfTurn은 WHITE나 BLACK이어야 합니다.");
    }

    private void validateMoving(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        validateExistPieceOnSourcePosition(sourcePiece);
        validateTurn(sourcePiece);
        validateIsMovable(sourcePosition, targetPosition);
        validatePathIncludeAnyPiece(sourcePosition, targetPosition, sourcePiece);
    }

    private void validateExistPieceOnSourcePosition(Piece sourcePiece) {
        if (sourcePiece.isEmptyPiece()) {
            throw new IllegalArgumentException("source위치에 말이 없습니다.");
        }
    }

    private void validateTurn(Piece sourcePiece) {
        if (sourcePiece.isOpponentOf(sideOfTurn)) {
            throw new IllegalArgumentException("다른 진영의 말은 움직일 수 없습니다.");
        }
    }

    private void validateIsMovable(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = this.chessBoard.get(sourcePosition);
        Piece targetPiece = this.chessBoard.get(targetPosition);
        if (!sourcePiece.isMovable(targetPiece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("올바른 움직임이 아닙니다.");
        }
    }

    private void validatePathIncludeAnyPiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        List<Position> path = sourcePiece.collectPath(sourcePosition, targetPosition);
        for (Position position : path) {
            checkIsExistAnyPieceOn(position);
        }
    }

    private void checkIsExistAnyPieceOn(Position position) {
        if (!this.chessBoard.get(position).isEmptyPiece()) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private void movePiece(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        this.chessBoard.put(sourcePosition, new EmptyPiece());
        this.chessBoard.put(targetPosition, sourcePiece);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public Side getSideOfTurn() {
        return this.sideOfTurn;
    }

    public boolean isEnd() {
        boolean isWhiteKingDead = this.chessBoard.values().stream()
                .noneMatch(piece -> piece.getCategory().equals(PieceCategory.WHITE_KING));
        boolean isBlackKingDead = this.chessBoard.values().stream()
                .noneMatch(piece -> piece.getCategory().equals(PieceCategory.BLACK_KING));
        return isWhiteKingDead || isBlackKingDead;
    }

    public Score calculateWhiteScore() {
        return calculateScoreOf(WHITE);
    }

    public Score calculateBlackScore() {
        return calculateScoreOf(BLACK);
    }

    private Score calculateScoreOf(Side side) {
        Score scoreBeforeCheckPawnsOnSameFile = calculateAllPiecesScoreOf(side);
        Score scoreToSubtractForSameFilePawnsOf = calculateScoreToSubtractForSameFilePawnsOf(side);
        return Score.subtract(scoreBeforeCheckPawnsOnSameFile, scoreToSubtractForSameFilePawnsOf);
    }

    private Score calculateAllPiecesScoreOf(Side side) {
        return this.chessBoard.values().stream()
                .filter(piece -> !piece.isEmptyPiece() && piece.isSameSideOf(side))
                .map(Piece::getScore)
                .reduce((Score::add))
                .orElseThrow(() -> new IllegalStateException("서버 내부 에러 - 점수를 계산할 수 없습니다."));
    }

    private Score calculateScoreToSubtractForSameFilePawnsOf(Side side) {
        List<Position> positionsOfPawns = collectPawnsPositionsOf(side);
        int countOfPawnsHaveSameFile = Arrays.stream(File.values())
                .mapToInt(file -> getCountOfPawnsOnOneFile(positionsOfPawns, file))
                .sum();
        return new Score(PAWN_ON_SAME_FILE_PENALTY * countOfPawnsHaveSameFile);
    }

    private List<Position> collectPawnsPositionsOf(Side side) {
        PieceCategory pawnCategoryBySide = getPawnCategoryOf(side);
        return this.chessBoard.entrySet().stream()
                .filter(positionPieceEntry -> positionPieceEntry.getValue().getCategory()
                        .equals(pawnCategoryBySide))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    private int getCountOfPawnsOnOneFile(List<Position> positionsOfPawns, File file) {
        int countOfPawnsOnThisFile = (int) positionsOfPawns.stream()
                .filter(position -> position.hasFileOf(file))
                .count();
        if (countOfPawnsOnThisFile >= LOWER_LIMIT_PAWN_PENALTY) {
            return countOfPawnsOnThisFile;
        }
        return NO_PENALTY;
    }

    private PieceCategory getPawnCategoryOf(Side side) {
        if (side.equals(WHITE)) {
            return PieceCategory.WHITE_PAWN;
        }
        if (side.equals(BLACK)) {
            return PieceCategory.BLACK_PAWN;
        }
        throw new IllegalStateException("서버 내부 에러 - Pawn은 White 또는 Black 진영이어야 합니다.");
    }
}

