package garage.model.refs;

public enum StubEnum {

	STUB_JAVA(7), STUB_UML(6), SQL(4);

	private final int coef;

	StubEnum(int coef) {
		this.coef = coef;
	}

	public int getCoef() {
		return coef;
	}
	
	@Override
	public String toString() {
		return "" + "";
	}

}
