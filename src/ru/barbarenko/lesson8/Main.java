package ru.barbarenko.lesson8;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Alex Dl.
 *
 */
public class Main {

    /**
     * Главный метод
     *
     * @param args
     * @throws InterruptedException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws InterruptedException,
            InvocationTargetException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {

        // установка темы Nimbus
        // если для данной версии JDK эта тема не доступна
        // будет установлена тема по умолчанию
        setNimbusLookAndFeel();

        // запуск приложения
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                // создание главного окна
                GameFrame wnd = new GameFrame();
                wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                wnd.setTitle("Крестики-нолики");
                wnd.setLocation(100, 100);
                wnd.setResizable(false);
                wnd.setVisible(true);
            }
        });

    }

    // установка внешнего вида приложения Nimbus
    private static void setNimbusLookAndFeel() {
        //Nimbus, если доступно
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}