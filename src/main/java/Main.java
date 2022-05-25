import controller.Calculator;
import controller.DataJsonConverter;
import entity.Ticket;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataJsonConverter converter = new DataJsonConverter(args[0]);
        List<Ticket> tickets = converter.convertData().getTickets();
        tickets.forEach(System.out::println);
        Calculator calculator = new Calculator(tickets);
        System.out.printf("Среднее время полета между городами Владивосток " +
                "и Тель-Авив составляет %d минуты.\n", calculator.getAverageTime());
        System.out.printf("90-й процентиль времени полета между городами " +
                "Владивосток и Тель-Авив составляет %d минуты.\n", calculator.getProcentil(90.0));
    }
}
