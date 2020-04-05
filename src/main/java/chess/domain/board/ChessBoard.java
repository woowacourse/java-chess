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

    private static final MoveStateChecker BEFORE_MOVE_CHECKER
        = new MoveStateChecker(new MoveStateBefore());
    private static final MoveStateChecker AFTER_MOVE_CHECKER
        = new MoveStateChecker(new MoveStateAfter());
    private static final MoveStateChecker PROMOTION_MOVE_CHECKER
        = new MoveStateChecker(new MoveStatePromotion());

    private Map<BoardSquare, Piece> chessBoard;
    private Set<CastlingSetting> castlingElements;
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

    public void initialize() {
        this.chessBoard = new BoardInitialDefault().getInitialize();
        this.gameTurn = Color.WHITE;
        this.castlingElements = CastlingSetting.getCastlingElements();
    }

    public static boolean isInitialPoint(BoardSquare boardSquare, Piece piece) {
        return (piece instanceof Pawn)
            && (boardSquare.isSameRank(Rank.SEVENTH) || boardSquare.isSameRank(Rank.SECOND));
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public MoveState movePieceWhenCanMove(MoveSquare moveSquare) {
        MoveState moveState = BEFORE_MOVE_CHECKER.check(this, moveSquare);
        moveState = moveIfReady(moveSquare, moveState);
        gameTurn = moveState.turnTeam(gameTurn);
        return moveState;
    }

    private MoveState moveIfReady(MoveSquare moveSquare, MoveState moveState) {
        if (moveState.isReady()) {
            movePiece(moveSquare);
            moveState = AFTER_MOVE_CHECKER.check(this, moveSquare);
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
        MoveState moveState = PROMOTION_MOVE_CHECKER.check(this);
        if (moveState == MoveState.NEEDS_PROMOTION) {
            chessBoard.put(getFinishPawnBoard(), getHopePiece(hopeType));
            moveState = MoveState.SUCCESS_PROMOTION;
            gameTurn = moveState.turnTeam(gameTurn);
        }
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
        chessBoard.put(moveSquareAfter, currentPiece);

        if (CastlingSetting.canCastling(castlingElements, moveSquare)) {
            castlingRook(moveSquare);
        }
        castlingElements.removeAll(castlingElements.stream()
            .filter(element -> element.isContains(moveSquareBefore))
            .collect(Collectors.toSet()));
    }

    private void castlingRook(MoveSquare moveSquare) {
        Set<CastlingSetting> removeCastlingElements = castlingElements.stream()
            .filter(
                castlingElement -> castlingElement.isEqualSquare(moveSquare.get(MoveOrder.BEFORE)))
            .collect(Collectors.toSet());
        if (castlingElements.removeAll(removeCastlingElements)) {
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