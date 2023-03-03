package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame implements ActionListener {

    static JFrame frame;
    static JTextField result;
    static String a = "", b = "", operator = "";




    public static void main(String[] args) {
        Main listen = new Main();  //метод нашего класса, отвечает за наши события

        frame = new JFrame("Calculator");  //создаем окно

        result = new JTextField(16); //создаем поле для ввода
        result.setEditable(false); //запрещаем его редактировать
        result.setText("");


        ArrayList<JButton> btns = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            JButton jTmpButton = new JButton(Integer.toString(i));
            jTmpButton.addActionListener(listen);
            btns.add(jTmpButton);
        }

        List<String> operations = Arrays.asList("+", "-", "*", "/", "=", "c");

        JPanel buttons = new JPanel();
        btns.forEach(buttons::add);

        operations.forEach(it -> {
            JButton jTmpButton = new JButton(it);
            jTmpButton.addActionListener(listen);
            buttons.add(jTmpButton);
        });

        GridLayout nums_and_oper = new GridLayout(4, 4);
        buttons.setLayout(nums_and_oper);


        JPanel mainpanel = new JPanel();
        mainpanel.add(result);
        mainpanel.add(buttons);



        frame.add(mainpanel);
        frame.setSize(500, 500); //регулируем размер окна
        frame.setVisible(true); //делаем окно видимым
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand()); //вывод на экран того, что было нажато

        String s = e.getActionCommand(); //обработчик событий нажатия на кнопки

        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {

            if (operator.equals("")) {
                a = a + s;
            } else
                b = b + s;
            result.setText(a + operator + b);

        }else if (s.charAt(0) == 'c') {
            a = operator = b = "";
            result.setText(a + operator + b);

        } else if (s.charAt(0) == '=') {
            int rslt = switch (operator) {
                case "+" -> Integer.parseInt(a) + Integer.parseInt(b);
                case "-" -> Integer.parseInt(a) - Integer.parseInt(b);
                case "/" -> Integer.parseInt(a) / Integer.parseInt(b);
                default -> Integer.parseInt(a) * Integer.parseInt(b);
            };
            a = String.valueOf(rslt);
            result.setText(a);
            operator = b = "";
        } else{
            if (operator.equals("")) {
                operator = s;
            }
            result.setText(a + operator + b);
        }



        }
    }
