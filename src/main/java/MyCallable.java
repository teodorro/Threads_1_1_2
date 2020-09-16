import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private String name;
    private int numberPrints;
    private int numberRounds = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberRounds(int numberRounds) {
        this.numberRounds = numberRounds;
    }

    public MyCallable(String name, int numberRounds) {
        this.name = name;
        this.numberRounds = numberRounds;
    }

    @Override
    public Integer call() throws Exception {
        numberPrints = 0;
        print();
        return numberPrints;
    }

    private void print() {
        try {
            for (int i = 0; i < numberRounds; i++) {
                Thread.sleep(1000);
                System.out.printf("Я %s. Всем привет!\n", getName());
                numberPrints++;
            }
        } catch (InterruptedException err) {

        } finally {
            System.out.printf("%s завершен\n", getName());
        }
    }
}
