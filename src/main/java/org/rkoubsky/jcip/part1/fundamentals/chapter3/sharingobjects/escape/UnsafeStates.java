package org.rkoubsky.jcip.part1.fundamentals.chapter3.sharingobjects.escape;

import org.rkoubsky.jcip.annotations.DoNotDoThis;

/**
 * Allowing internal mutable state to escape. Don't do this.
 */
@DoNotDoThis
public class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return this.states;
    }
}
