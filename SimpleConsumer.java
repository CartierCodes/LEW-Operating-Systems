import java.util.concurrent.Semaphore;

public class SimpleConsumer implements Runnable {
	private Semaphore s0;
	private Semaphore s1;

	private int[] shared_variable;

	public void run() {
		for (;;) {
			try {
				s0.acquire(); //acquire();
			}catch (InterruptedException ie){}

			System.out.println(shared_variable[0]);
			//notify the producer


			try {
				Thread.currentThread().sleep(2 * 100);
			} catch(InterruptedException ie) {}

			s1.release(); //release() maybe add try catch block


		}//for
	}

	public SimpleConsumer(Semaphore s0, Semaphore s1, int[] sv) {
		this.s0 = s0;
		this.s1 = s1;
		shared_variable = sv;
    }

    public static void main(String[] args) {
        Semaphore s0 = new Semaphore(0);
        Semaphore s1 = new Semaphore(1);
    
        int[] sv = {0};
    
        Thread simpleProducer = new Thread(new SimpleProducer(s0, s1, sv));
        Thread simpleConsumer= new Thread(new SimpleConsumer(s0, s1, sv));
    
        simpleConsumer.start();
        simpleProducer.start();
    
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException ie) {}
    
        s0.release();
    
    }
}