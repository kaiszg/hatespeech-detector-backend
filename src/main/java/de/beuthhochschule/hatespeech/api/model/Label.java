package de.beuthhochschule.hatespeech.api.model;

import java.util.Objects;

public class Label {

    private String label;

    private int nbComments;

    public Label() {
    }

    public Label(String label, int nbComments) {
        this.label = label;
        this.nbComments = nbComments;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label1 = (Label) o;
        return nbComments == label1.nbComments &&
                Objects.equals(label, label1.label);
    }

    @Override
    public int hashCode() {

        return Objects.hash(label, nbComments);
    }
}
