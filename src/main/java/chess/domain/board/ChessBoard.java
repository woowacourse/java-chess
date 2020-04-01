package chess.domain.board;

import chess.domain.move.MoveStateAfter;
import chess.domain.move.MoveStateBefore;
import chess.domain.move.MoveStateChecker;
import chess.domain.move.MoveStatePromotion;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.state.MoveOrder;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import util.NullChecker;

public class ChessBoard {

    private final Map<BoardSquare, Piece> chessBoard;
    private final Set<CastlingSetting> castlingElements;
    private Color gameTurn;

    public ChessBoard() {
        this(new BoardInitialDefault(), Color.WHITE, CastlingSetting.getCastlingElements());
    }

    public ChessBoard(BoardInitialization chessBoard, Color gameTurn,
        Set<CastlingSetting> castlingElements) {
        NullChecker.validateNotNull(chessBoard, gameTurn, castlingElements);
        this.chessBoard = chessBoard.getInitialize();
        this.gameTurn = gameTurn;
        this.castlingElements = castlingElements;
    }

    public static boolean isInitialPoint(BoardSquare boardSquare, Piece piece) {
        return (piece instanceof Pawn)
            && (boardSquare.isSameRank(Rank.SEVENTH) || boardSquare.isSameRank(Rank.SECOND));
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public MoveState movePieceWhenCanMove(MoveSquare moveSquare) {
        MoveStateChecker moveChecker = new MoveStateChecker(this);
        MoveState moveState = moveChecker.check(new MoveStateBefore(moveSquare));
        moveState = moveIfReady(moveSquare, moveChecker, moveState);
        gameTurn = moveState.turnTeam(gameTurn);
        return moveState;
    }

    private MoveState moveIfReady(MoveSquare moveSquare, MoveStateChecker moveChecker,
        MoveState moveState) {
        if (moveState == MoveState.READY) {
            movePiece(moveSquare);
            moveState = moveChecker.check(new MoveStateAfter());
        }
        return moveState;
    }

    private BoardSquare getFinishPawnBoard() {
        return chessBoard.keySet().stream()
            .filter(boardSquare -> chessBoard.get(boardSquare) instanceof Pawn)
            .filter(BoardSquare::isLastRank)
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
    }

    public boolean isNeedPromotion() {
        return chessBoard.keySet().stream()
            .filter(boardSquare -> chessBoard.get(boardSquare) instanceof Pawn)
            .anyMatch(BoardSquare::isLastRank);
    }

    public MoveState promotion(Type hopeType) {
        MoveState moveState = new MoveStateChecker(this).check(new MoveStatePromotion());
        if (moveState == MoveState.SUCCESS) {
            chessBoard.put(getFinishPawnBoard(), getHopePiece(hopeType));
        }
        gameTurn = moveState.turnTeam(gameTurn);
        return moveState;
    }

    private Piece getHopePiece(Type hopeType) {
        return Arrays.stream(CastlingSetting.values())
            .map(CastlingSetting::getPiece)
            .filter(piece -> piece.isSameType(hopeType))
            .filter(piece -> piece.isSameColor(gameTurn))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean canMove(MoveSquare moveSquare) {
        BoardSquare moveSquareBefore = moveSquare.get(MoveOrder.BEFORE);
        BoardSquare moveSquareAfter = moveSquare.get(MoveOrder.AFTER);
        Piece movePieceBefore = chessBoard.get(moveSquareBefore);
        if (!chessBoard.containsKey(moveSquareBefore) || !movePieceBefore.isSameColor(gameTurn)) {
            return false;
        }
        return movePieceBefore.getCheatSheet(moveSquareBefore, chessBoard, castlingElements)
            .contains(moveSquareAfter);
    }

    private void movePiece(MoveSquare moveSquare) {
        BoardSquare moveSquareBefore = moveSquare.get(MoveOrder.BEFORE);
        BoardSquare moveSquareAfter = moveSquare.get(MoveOrder.AFTER);
        Piece currentPiece = chessBoard.remove(moveSquareBefore);
        boolean castlingElement = castlingElements.stream()
            .anyMatch(
                chessInitialSetting -> chessInitialSetting.isContains(moveSquareBefore));
        if (castlingElement) {
            castlingElements.remove(castlingElements.stream()
                .filter(
                    chessInitialSetting -> chessInitialSetting.isContains(moveSquareBefore))
                .findFirst().orElseThrow(IllegalAccessError::new));
        }
        chessBoard.put(moveSquareAfter, currentPiece);
        moveIfCastlingRook(moveSquare);
    }

    private void moveIfCastlingRook(MoveSquare moveSquare) {
        Set<CastlingSetting> removeCastlingElements = castlingElements.stream()
            .filter(
                castlingElement -> castlingElement.isSameSquare(moveSquare.get(MoveOrder.BEFORE)))
            .collect(Collectors.toSet());
        if (!castlingElements.isEmpty() && castlingElements.removeAll(removeCastlingElements)
            && moveSquare.isJumpFile()) {
            MoveSquare moveSquareRook = CastlingSetting.getMoveCastlingRook(moveSquare);
            Piece currentPiece = chessBoard.remove(moveSquareRook.get(MoveOrder.BEFORE));
            chessBoard.put(moveSquareRook.get(MoveOrder.AFTER), currentPiece);
        }
    }

    public boolean isKingCaptured() {
        return chessBoard.values().stream()
            .filter(piece -> piece instanceof King)
            .count() != Color.values().length;
    }

    public TeamScore getTeamScore() {
        return new TeamScore(chessBoard.values(), countPawnSameFileByColor());
    }

    public Color getWinnerTurn() {
        return gameTurn.previousTurnIfEmptyMySelf();
    }

    private Map<Color, Integer> countPawnSameFileByColor() {
        Map<Color, Integer> pawnSameFileCountByColor = new HashMap<>();
        for (Color color : Color.values()) {
            List<BoardSquare> pawnSquare = getSquareIfSameColorPawn(color);
            pawnSameFileCountByColor.put(color, getCountSameFile(pawnSquare));
        }
        return pawnSameFileCountByColor;
    }

    private int getCountSameFile(List<BoardSquare> pawnSquare) {
        int count = 0;
        for (BoardSquare boardSquare : pawnSquare) {
            count += pawnSquare.stream()
                .filter(square -> boardSquare.isSameFile(square) && boardSquare != square)
                .count();
        }
        return count;
    }

    private List<BoardSquare> getSquareIfSameColorPawn(Color color) {
        return chessBoard.keySet().stream()
            .filter(square -> chessBoard.get(square) == Pawn.getPieceInstance(color))
            .collect(Collectors.toList());
    }

    public boolean isNoPiece(MoveSquare moveSquare) {
        return !chessBoard.containsKey(moveSquare.get(MoveOrder.BEFORE));
    }

    public Color getGameTurn() {
        return gameTurn;
    }

    public boolean isNotMyTurn(MoveSquare moveSquare) {
        if (isNoPiece(moveSquare)) {
            return true;
        }
        return !chessBoard.get(moveSquare.get(MoveOrder.BEFORE)).isSameColor(gameTurn);
    }
}