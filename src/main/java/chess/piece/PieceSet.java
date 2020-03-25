// package chess.piece;
//
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Map;
// import java.util.Queue;
// import java.util.stream.Collectors;
//
// import chess.board.Location;
//
// public class PieceSet {
// 	private final boolean isBlack;
// 	private final Map<Location, Piece> pieceSet;
//
// 	public PieceSet(boolean isBlack) {
// 		this.isBlack = isBlack;
// 		this.pieceSet = makePieceSet();
// 	}
//
// 	// 16개의 한팀의 피스를 만든다.
// 	private List<Piece> createPieceSet2() {
// 		List<Piece> list = new ArrayList<>();
// 		Map<Class, Integer> map = SizeInformation.getMap();
// 		for (Class className : map.keySet()) {
// 			int size = map.get(className);
// 			for (int i = 0; i < size; i++) {
// 				list.add());
// 			}
// 		}
// 		addPiece(list, Pawn.class, 8);
// 		addPiece(list, Pawn.class, 2);
// 		addPiece(list, Pawn.class, 2);
// 		addPiece(list, Pawn.class, 2);
// 		addPiece(list, Pawn.class, 1);
// 		addPiece(list, Pawn.class, 1);
// 		return list;
// 	}
//
// 	private void addPiece(List<Piece> list, Class<Piece> pawnClass, int size) {
// 		for (int i = 0 ; i<size; i ++) {
// 			list.add(new pawnClass());
// 		}
// 	}
//
// 	// 팀별 위치, 피스를 갖는 체스세트를 만든다.
// 	private Map<Location, Piece> makePieceSet() {
// 		Map<Location, Piece> pieceSet = new HashMap<>();
// 		if (isBlack) {
// 			for (PieceType pieceType : PieceType.values()) {
// 				Queue<Location> locations = new LinkedList<>(pieceType.reverseInitialLocation());
// 				putPieceLocations(pieceSet, pieceType, locations);
// 			}
// 			return pieceSet;
// 		}
//
// 		for (PieceType pieceType : PieceType.values()) {
// 			Queue<Location> locations = new LinkedList<>(pieceType.getInitialLocation());
// 			putPieceLocations(pieceSet, pieceType, locations);
// 		}
// 		return pieceSet;
// 	}
//
// 	private void putPieceLocations(Map<Location, Piece> pieceSet, PieceType pieceType, Queue<Location> locations) {
// 		for (Piece piece : find(pieceType)) {
// 			pieceSet.put(locations.poll(), piece);
// 		}
// 	}
//
// 	private List<Piece> find(PieceType pieceType) {
// 		return makePieces().stream()
// 			.filter(piece -> piece.is(pieceType))
// 			.collect(Collectors.toList());
// 	}
//
// 	private List<Piece> makePieces() {
// 		List<Piece> pieces = new ArrayList<>();
//
// 		for (PieceType pieceType : PieceType.values()) {
// 			addPieces(pieces, pieceType);
// 		}
// 		return Collections.unmodifiableList(pieces);
// 	}
//
// 	private void addPieces(List<Piece> pieces, PieceType pieceType) {
// 		for (int i = 0; i < pieceType.getSize(); i++) {
// 			pieces.add(Piece.of(pieceType, isBlack));
// 		}
// 	}
//
// 	public Map<Location, Piece> getPieceSet() {
// 		return pieceSet;
// 	}
//
// 	@Override
// 	public String toString() {
// 		return "PieceSet{" +
// 			"isBlack=" + isBlack +
// 			", pieceSet=" + pieceSet.values().stream().map(piece -> piece.toString()).collect(Collectors.joining(",")) +
// 			'}';
// 	}
// }
