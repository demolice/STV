package montecarlocircle;

import java.util.Random;

public class HitCalculator implements Runnable
{
  private final Thread thread;
  private final int count;

  private int hits;
  
  private static int computeHits(int count)
  {
    Random random = new Random();
    
    int hits = 0;
    
    for (int index = 0; index < count; index++)
    {
      double x = random.nextDouble() * 2;
      double y = random.nextDouble() * 2;
      
      double d = (x - 1) * (x - 1) + (y - 1) * (y - 1);
      
      if (d <= 1)
        hits++;
    }

    return hits;
  }
  
  public HitCalculator(int count)
  {
    this.thread = new Thread(this);
    this.count = count;
  }

  public void run()
  {
    hits = computeHits(count);
  }
  
  public void start()
  {
    thread.start();
  }
  
  public void join() throws InterruptedException 
  {
    thread.join();
  }
  
  public int getHits()
  {
    return hits;
  }
}
