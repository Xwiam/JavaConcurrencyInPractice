package com.concurrency.fundamental.buildingblocks;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author xwiam
 * @create 2018/05/27 18:12
 * @desc factorizing servlet that caches results using Memorizer
 **/
public class Factorizer extends GenericServlet  implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c =
            new Computable<BigInteger, BigInteger[]>(){
                public BigInteger[] compute(BigInteger arg) {
                    return factor(arg);
                }
            };

    private final Computable<BigInteger, BigInteger[]> cache = new Memorizer<BigInteger, BigInteger[]>(c);

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {

    }

    void encodeError(ServletResponse resp, String errorString) {

    }

    BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        //Doesn't really factor
        return new BigInteger[]{i};
    }
}
