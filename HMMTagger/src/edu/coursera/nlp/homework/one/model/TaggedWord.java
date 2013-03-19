package edu.coursera.nlp.homework.one.model;

public class TaggedWord {

	private final String tag;

	private final String word;

	public TaggedWord(final String word, final String tag) {
		this.tag = tag;
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return word + " " + tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaggedWord other = (TaggedWord) obj;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

}
