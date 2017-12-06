package com.clavardage.tasks;

public class Notify
{
    // TODO: Broadcast? Or centralized way to achieve that (i.e. public list updated whenever a user logs in or logs out)
	// TODO: choose standard port (6666/6667?)

	public class Send implements Runnable
	{
		@Override
		public void run()
		{
			for (;;) {
				// TODO: Send broadcast notification "Hi! I'm xxx"
				
				// Sleep 10s
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Listen implements Runnable
	{
		@Override
		public void run()
		{
			for (;;) {
				// TODO: listen for UDP broadcasts
				
				// Update user list
				//User.addActiveUser(user);
			}
		}
	}
}
