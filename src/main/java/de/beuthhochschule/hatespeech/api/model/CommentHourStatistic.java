package de.beuthhochschule.hatespeech.api.model;

import java.util.Objects;

public class CommentHourStatistic {

    private int hour;
    private long nbComments;

    public CommentHourStatistic() {
    }

    public CommentHourStatistic(int hour, long nbComments) {
        this.hour = hour;
        this.nbComments = nbComments;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public long getNbComments() {
        return nbComments;
    }

    public void setNbComments(long nbComments) {
        this.nbComments = nbComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentHourStatistic that = (CommentHourStatistic) o;
        return hour == that.hour &&
                nbComments == that.nbComments;
    }

    @Override
    public int hashCode() {

        return Objects.hash(hour, nbComments);
    }
}
