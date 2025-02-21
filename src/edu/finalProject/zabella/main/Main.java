package edu.finalProject.zabella.main;

import edu.finalProject.zabella.controller.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        String request = "ADD\ntitle=Энциклопедия\ncontent=Мир вод\ndate=2020-10-15";
        String response = controller.doAction(request);
        System.out.println(response);

        request = "ADD\nid=44\ntitle=Книга\ncontent=Туманность Андромеды 2.0\ndate=2023-08-08";
        response = controller.doAction(request);
        System.out.println(response);

        request = "UPDATE\nid=44\ntitle=Учебник\ncontent=Туманность Андромеды 2.0\ndate=2023-08-08";
        response = controller.doAction(request);
        System.out.println(response);

        request = "ADD\ntitle=Карты географические\ncontent=Карта Мира\ndate=2025-02-01";
        response = controller.doAction(request);
        System.out.println(response);

        request = "FINDBYDATE\ndate=2023-08-08";
        response = controller.doAction(request);
        System.out.println(response);

        request = "FINDBYID\nid=2";
        response = controller.doAction(request);
        System.out.println(response);

        request = "FINDBYTEXT\ntext=андромеды";
        response = controller.doAction(request);
        System.out.println(response);

    }
}

