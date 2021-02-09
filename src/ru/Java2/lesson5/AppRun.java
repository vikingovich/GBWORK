

public class AppRun {

    static long time;

    public static void main(String[] args) {

        Hole hole = new Hole();

        time = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {

            synchronized (hole) {

                try {
                    hole.wait();
                } catch (InterruptedException ignored) {
                }

                while (true) {

                    if (hole.length >= 5967) {
                        //System.out.println("Все ребят хватит вы вырали " + hole.length + " метровую яму");
                        hole.isContinue = false;
                        hole.notifyAll();
                        break;
                    }

                    //System.out.println("Ребят роете дальше осталось еще " + (5967 - hole.length));

                    hole.notifyAll();

                    try {
                        hole.wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (hole) {

                while (hole.isContinue) {

                    hole.length++;

                    if (hole.length % 3 == 0) {
                        //System.out.println("Мы вырыли отрезок, пожалуйста измерь Петрович");
                        hole.notifyAll();
                        try {
                            hole.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
        //t2.setDaemon(true);

        //System.out.println((System.currentTimeMillis() - time) + "main");

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - time));
    }

}

class Hole {
    int length = 0;
    boolean isContinue = true;
}



