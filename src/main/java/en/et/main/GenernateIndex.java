package en.et.main;

import java.util.Timer;

public class GenernateIndex {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//task��ǰִ�е�����  delaymain�������ж�ú�ִ�д�����  period�ٹ����ִ����һ��
		timer.schedule(new MyTimerTask(), 500, 2000);
	}
}
