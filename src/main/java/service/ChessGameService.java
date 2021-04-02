package service;

import dao.ChessGameDao;
import domain.chessgame.ChessGame;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import dto.request.ChessGameRequestDto;
import dto.request.PiecesRequestDto;
import dto.request.ScoreRequestDto;
import dto.response.ChessGameResponseDto;
import dto.response.PieceResponseDto;
import dto.response.PiecesResponseDto;
import dto.response.ScoreResponseDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    public ChessGameResponseDto putChessGame(ChessGameRequestDto chessGameRequestDto)
        throws IOException, SQLException {
        ChessGame chessGame = chessGameDao.selectByGameId(chessGameRequestDto.getGameId());
        chessGame.operate(chessGameRequestDto.isRestart(), chessGameRequestDto.isPlaying());

        chessGame = chessGameDao.updateChessGameByGameId(chessGameRequestDto.getGameId(), chessGame);
        List<PieceResponseDto> pieceResponseDtos = pieceResponseDtos(chessGame);
        return new ChessGameResponseDto(chessGame.isPlaying(), pieceResponseDtos);
    }

    public List<PieceResponseDto> pieceResponseDtos(ChessGame chessGame) {
        List<PieceResponseDto> pieceResponseDtos = new ArrayList<>();
        Map<Position, Piece> pieces = chessGame.pieces();
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            pieceResponseDtos.add(new PieceResponseDto(entry.getValue(), entry.getKey()));
        }
        return pieceResponseDtos;
    }

    public PiecesResponseDto putPiece(PiecesRequestDto piecesRequestDto)
        throws IOException, SQLException {
        ChessGame chessGame = chessGameDao.selectByGameId(piecesRequestDto.getGameId());
        try {
            chessGame.move(new Position(piecesRequestDto.getSource()),
                new Position(piecesRequestDto.getTarget()));
        } catch (Exception e) {
            return new PiecesResponseDto(chessGame, pieceResponseDtos(chessGame), e.getMessage());
        }
        chessGame = chessGameDao.updateChessGameByGameId(piecesRequestDto.getGameId(), chessGame);
        return new PiecesResponseDto(chessGame, pieceResponseDtos(chessGame));
    }

    public ScoreResponseDto getScore(ScoreRequestDto scoreRequestDto)
        throws IOException, SQLException {
        System.out.println(scoreRequestDto.getGameId());
        ChessGame chessGame = chessGameDao.selectByGameId(scoreRequestDto.getGameId());
        System.out.println();
        return new ScoreResponseDto(chessGame.score(Color.BLACK), chessGame.score(Color.WHITE));
    }

}
