package integration;

/**
 * Created by mschmidt on 1/20/16.
 */
public interface Integrator<T, P> {

    T call(P p);

}
