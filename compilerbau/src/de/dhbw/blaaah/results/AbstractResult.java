package de.dhbw.blaaah.results;

import de.dhbw.blaaah.Result;

import java.util.Date;

/**
 * User: alexander
 * Date: 25.11.13
 * Time: 10:45
 */
public abstract class AbstractResult implements Result {
    protected Result next;
    protected Date timestamp;

    public AbstractResult() {
        this.timestamp = new Date();
    }

    public void setNext(Result next) {
        this.next = next;
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public Result next() {
        return this.next;
    }
}
