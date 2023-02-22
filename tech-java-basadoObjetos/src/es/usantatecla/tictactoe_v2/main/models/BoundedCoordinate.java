package es.usantatecla.tictactoe_v2.main.models;

import es.usantatecla.tictactoe_v2.utils.Coordinate;
import es.usantatecla.tictactoe_v2.utils.Direction;

public class BoundedCoordinate {

	private Coordinate coordinate;
	private static final int DIMENSION = 3;

	public BoundedCoordinate() {
		this.coordinate = new Coordinate();
	}

	public BoundedCoordinate(int row, int column) {
		this.coordinate = new Coordinate(row, column);
	}

	public static int getDimension() {
		return BoundedCoordinate.DIMENSION;
	}

	public String getErrorMessage() {
		return Error.WRONG_COORDINATES.toString();
	}

	public Direction getDirection(BoundedCoordinate boundedCoordinate) {
		assert boundedCoordinate != null;

		if (this.equals(boundedCoordinate)) {
			return Direction.NULL;
		}
		if (this.inHorizontal(boundedCoordinate)) {
			return Direction.HORIZONTAL;
		}
		if (this.inVertical(boundedCoordinate)) {
			return Direction.VERTICAL;
		}
		if (this.inMainDiagonal() && boundedCoordinate.inMainDiagonal()) {
			return Direction.MAIN_DIAGONAL;
		}
		if (this.inInverseDiagonal() && boundedCoordinate.inInverseDiagonal()) {
			return Direction.INVERSE_DIAGONAL;
		}
		return Direction.NULL;
	}

	private boolean inHorizontal(BoundedCoordinate boundedCoordinate) {
		if (boundedCoordinate == null) {
			return false;
		}
		return this.coordinate.getRow() == boundedCoordinate.getRow();
	}

	private boolean inVertical(BoundedCoordinate boundedCoordinate) {
		if (boundedCoordinate == null) {
			return false;
		}
		return this.coordinate.getColumn() == boundedCoordinate.getColumn();
	}

	private boolean inMainDiagonal() {
		return this.coordinate.getRow() - this.coordinate.getColumn() == 0;
	}

	private boolean inInverseDiagonal() {
		return this.coordinate.getRow() + this.coordinate.getColumn() == BoundedCoordinate.DIMENSION - 1;
	}

	public int getRow() {
		return this.coordinate.getRow();
	}

	public int getColumn() {
		return this.coordinate.getColumn();
	}

	public void setRow(int row){
		this.coordinate.setRow(row);
	}

	public void setColumn(int column){
		this.coordinate.setColumn(column);
	}
}