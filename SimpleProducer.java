import java.util.concurrent.Semaphore;

public class SimpleProducer implements Runnable {
	private Semaphore s0;
    private Semaphore s1;
    
	private int[] shared_variable;

	public void run() {
		for (;;) {
			// have to make producer wait for the consumer to consume the item

			try {
				s1.acquire(); //aquire()

			} catch(InterruptedException ie){}

			shared_variable[0]++;

			s0.release(); //aquire()

		}//for
	}
	public SimpleProducer(Semaphore s0, Semaphore s1, int[] sv) {
		this.s0 = s0;
		this.s1 = s1;

		shared_variable = sv;
	}

}