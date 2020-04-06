package chess.domain.dto;

import chess.domain.Side;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.Arrays;

public enum PieceNameConverter {
	KING("\u265A", "\u2654", King.class),
	QUEEN("\u265B", "\u2655", Queen.class),
	ROOK("\u265C", "\u2656", Rook.class),
	BISHOP("\u265D", "\u2657", Bishop.class),
	KNIGHT("\u265E", "\u2658", Knight.class),
	PAWN("\u265F", "\u2659", Pawn.class);

	private String blackName;
	private String whiteName;
	private Class<? extends Piece> pieceClass;

	PieceNameConverter(String blackName, String whiteName, Class<? extends Piece> pieceClass) {
		this.blackName = blackName;
		this.whiteName = whiteName;
		this.pieceClass = pieceClass;
	}

	public static String toName(Piece piece) {
		return Arrays.stream(values())
				.filter(pieceInfo -> pieceInfo.check(piece))
				.map(pieceInfo -> pieceInfo.getName(piece))
				.findAny()
				.orElseThrow(AssertionError::new);
	}

	public static Piece toPiece(PieceDto pieceDto) {
		return toPiece(pieceDto.getName(), pieceDto.getCol(), pieceDto.getRow());
	}

	public static Piece toPiece(String name, int col, int row) {
		return Arrays.stream(values())
				.filter(pieceInfo -> pieceInfo.blackName.equals(name))
				.findAny()
				.map(pieceInfo -> pieceInfo.createPiece(Side.BLACK, col, row))
				.orElseGet(() -> convertWhiteNameToPiece(name, col, row));
	}

	private static Piece convertWhiteNameToPiece(String name, int col, int row) {
		return Arrays.stream(values())
				.filter(pieceInfo -> pieceInfo.whiteName.equals(name))
				.findAny()
				.map(pieceInfo -> pieceInfo.createPiece(Side.WHITE, col, row))
				.orElseThrow(() -> new IllegalArgumentException("잘못된 문자가 입력되었습니다."));
	}

	private Piece createPiece(Side side, int col, int row) {
		try {
			return pieceClass.getDeclaredConstructor(Side.class, Position.class).newInstance(side, new Position(col, row));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public String getName(Piece piece) {
		if (piece.isSameSide(Side.BLACK)) {
			return blackName;
		}
		return whiteName;
	}

	private boolean check(Piece piece) {
		return piece.getClass() == pieceClass;
	}
}
