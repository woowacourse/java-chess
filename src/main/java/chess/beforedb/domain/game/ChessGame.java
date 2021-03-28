package chess.beforedb.domain.game;

import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.domain.board.Board;
import chess.beforedb.domain.board.setting.BoardCustomSetting;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.domain.piece.Piece;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.player.Players;
import chess.beforedb.domain.player.Scores;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.MoveRoute;
import chess.beforedb.domain.position.Position;
import chess.beforedb.domain.position.cache.PositionsCache;
import java.util.List;

public class ChessGame {
    private final Players players;
    private final Board board;
    private TeamColor currentTurnTeamColor = WHITE;

    public ChessGame(BoardSetting boardSetting) {
        validate(boardSetting);
        players = new Players();
        board = new Board();
        setPieces(boardSetting);
    }

    private void validate(BoardSetting boardSetting) {
        if (!(boardSetting instanceof BoardDefaultSetting
            || boardSetting instanceof BoardCustomSetting)) {
            throw new IllegalArgumentException("유효하지 않은 보드 세팅 객체 타입 입니다.");
        }
    }

    private void setPieces(BoardSetting boardSetting) {
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            Position position = PositionsCache.get(index);
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            setPiece(pieceWithColorType, position);
        }
    }

    private void setPiece(PieceWithColorType pieceWithColorType, Position position) {
        Piece piece = null;
        if (pieceWithColorType != null) {
            piece = Piece.of(pieceWithColorType);
            players.add(piece, position);
        }
        board.setPiece(position, piece);
    }

    public void move(MoveRequestDTO moveRequestDTO) {
        MoveRoute moveRoute = new MoveRoute(moveRequestDTO);
        board.validateRoute(moveRoute, currentTurnTeamColor);
        updatePiecesOfPlayers(moveRoute);
        board.move(moveRoute);
    }

    private void updatePiecesOfPlayers(MoveRoute moveRoute) {
        Piece movingPiece = board.findPiece(moveRoute.startPosition());
        players.remove(movingPiece, moveRoute.startPosition());
        players.add(movingPiece, moveRoute.destination());
        if (board.isAnyPieceExistsInCell(moveRoute.destination())) {
            Piece deadPiece = board.findPiece(moveRoute.destination());
            players.remove(deadPiece, moveRoute.destination());
        }
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public List<String> boardCellsStatus() {
        return board.status();
    }

    public Scores getScores() {
        return new Scores(players.blackPlayerScore(), players.whitePlayerScore());
    }

    public void changeToNextTurn() {
        currentTurnTeamColor = currentTurnTeamColor.oppositeTeamColor();
    }

    public String currentTurnTeamName() {
        return currentTurnTeamColor.getName();
    }

    public String beforeTurnTeamName() {
        TeamColor beforeTurnTeamColor = currentTurnTeamColor.oppositeTeamColor();
        return beforeTurnTeamColor.getName();
    }
}