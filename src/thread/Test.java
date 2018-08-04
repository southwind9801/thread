package thread;

import java.util.concurrent.ExecutorService;

public class Test {
	public static void main(String[] args) {
		daemon();
	}
	
	/**
	 * 线程等待
	 */
	public static void join() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("数据保存");
			}
			
		});
		th.start();
		try {
			//th线程独占CPU资源，th结束，允许其他线程执行
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------");
	}
	
	/**
	 * 线程中断
	 */
	public static void interrupt() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println(Thread.currentThread().getName());
						break;
					}
					
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//抛出异常后会清除interrupt标记，所以需要重新设置
						Thread.currentThread().interrupt();
					}
					
					System.out.println(123);
				}
			}
		});
		th.start();
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		th.interrupt();
	}
	
	/**
	 * 优先级
	 */
	public static void priority() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName()+"----------------");
				}
			}
		},"a");
		th.setPriority(Thread.MAX_PRIORITY);
		Thread th2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i++) {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+Thread.currentThread().getName());
				}
			}
		},"b");
		th2.setPriority(Thread.MIN_PRIORITY);
		th.start();
		th2.start();	
	}
	
	/**
	 * 守护线程
	 * 如GC，垃圾回收机制，定时自动执行
	 * 当主线程中没有其他用户线程执行时，守护线程自动关闭，当存在用户线程时，守护线程开启
	 * 餐厅没有人吃饭，服务员可以休息
	 */
	public static void daemon() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 10; i++) {
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("----------------------");
				}
			}
		});
		//开启守护线程
		th.setDaemon(true);
		th.start();
		
		Thread th2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 10; i++) {
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("----------333------------");
				}
			}
		});
		th2.start();
	}
	
	/**
	 * 多线程
	 * 1.原子性
	 * 2.可见性
	 * 3.有序性
	 */
	public static void MoneyThread() {
		
	}
}
