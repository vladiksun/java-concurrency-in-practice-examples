package org.rkoubsky.chapter3.sharingobjects.immutability.volatileholder;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * Caching the last result using a volatile reference to an immutable holder object
 *
 *
 */
@ThreadSafe
public class VolatileCachedFactorizerServlet extends GenericServlet implements Servlet {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(final ServletRequest req, final ServletResponse resp) {
        final BigInteger i = this.extractFromRequest(req);
        BigInteger[] factors = this.cache.getFactors(i);
        if (factors == null) {
            factors = this.factor(i);
            /**
             * When a thread sets the volatile "cache" field to reference
             * a new "OneValueCache", the new cached data becomes immediately to other
             * threads.
             *
             * Remember the part about volatile variables from a memory visibility perspective:
             *
             *  - writing a volatile variable is like exiting a synchronized block
             *  - reading a volatile is like entering a synchronized block
             *
             *
             *  When thread A writes to "cache", and thread B subsequently reads it,
             *  it will see the updated value of "cache" from thread A
             *
             */
            this.cache = new OneValueCache(i, factors);
        }
        this.encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(final ServletResponse resp, final BigInteger[] factors) {
    }

    BigInteger extractFromRequest(final ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(final BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
