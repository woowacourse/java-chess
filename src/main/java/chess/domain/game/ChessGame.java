package chess.domain.game;

import static chess.domain.player.type.TeamColor.WHITE;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.board.Board;
import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.Players;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import chess.domain.position.cache.PositionsCache;
import java.util.List;

public class ChessGame {
    private final Players players;
    private final Board board;
    private boolean isStarted = false;
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

    public void start() {
        isStarted = true;
    }

    public void move(CommandRequestDTO commandRequestDTO) {
        validateIsGameStarted();
        MoveRoute moveRoute = new MoveRoute(commandRequestDTO);
        board.validateRoute(moveRoute, currentTurnTeamColor);
        updatePiecesOfPlayers(moveRoute);
        board.move(moveRoute);
    }

    private void validateIsGameStarted() {
        if (!isStarted) {
            throw new IllegalStateException("게임을 먼저 시작해 주세요.");
        }
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

    public BoardStatusResponseDTO boardStatus() {
        validateIsGameStarted();
        return board.status();
    }

    public ScoresResponseDTO getScores() {
        validateIsGameStarted();
        return new ScoresResponseDTO(players.blackPlayerScore(), players.whitePlayerScore());
    }

    public String winnerTeamColorName() {
        TeamColor teamColor = board.winnerTeamColor();
        return teamColor.getName();
    }

    public void changeCurrentTurnTeamColorToOpposite() {
        currentTurnTeamColor = currentTurnTeamColor.oppositeTeamColor();
    }
}
