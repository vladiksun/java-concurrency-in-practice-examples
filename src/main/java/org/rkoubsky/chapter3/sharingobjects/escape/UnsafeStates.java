package org.rkoubsky.chapter3.visibility.escape;

/**
 * Allowing internal mutable state to escape. Don't do this.
 */
public class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return this.states;
    }
}
