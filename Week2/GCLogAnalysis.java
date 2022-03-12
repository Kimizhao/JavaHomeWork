import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/*
��ʾGC��־��������
*/
public class GCLogAnalysis {
    // �����; �ǵ���������������������;
    private static Random random = new Random();

    public static void main(String[] args) {
        // ��ǰ����ʱ���
        long startMillis = System.currentTimeMillis();
        // �������к�����; �ɸ�����Ҫ�����޸�
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);
        // ����ʱ���
        long endMillis = startMillis + timeoutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("����ִ��...");
        // ����һ���ֶ���; ���������
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];
        // �ڴ�ʱ�䷶Χ��,����ѭ��
        while (System.currentTimeMillis() < endMillis) {
            // ������������
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("ִ�н���!�����ɶ������:" + counter.longValue());
    }

    // ���ɶ���
    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
