package en.et.main;

import java.util.Timer;

public class GenernateIndex {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//task当前执行的任务  delaymain方法运行多久后执行此任务  period再过多久执行下一次
		timer.schedule(new MyTimerTask(), 500, 2000);
	}
}
