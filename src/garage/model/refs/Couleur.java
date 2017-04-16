package garage.model.refs;

public enum Couleur {

	ROUGE("rouge"), VERT("vert"), BLEU("bleu");

	private final String couleur;

	Couleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCouleur() {
		return couleur;
	}

	@Override
	public String toString() {
		return "" + "";
	}
}
