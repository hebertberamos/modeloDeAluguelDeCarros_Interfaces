package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;


public class Program {

	public static void main(String[] args) throws ParseException{
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("entre com as informações: ");
		System.out.print("Modelo do carro: ");
		String model = sc.nextLine();
		System.out.print("data que o carro será pego (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("data que o carro será entregue (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("informe o valor do aluguel por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("informe o valor do aluguel por dia: ");
		double pricePerDay = sc.nextDouble();
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rs.processInvoice(cr);
		
		System.out.println("FATURA:");
		System.out.println("Valor básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Valor total: " + String.format("%.2f", cr.getInvoice().totalPayment()));
	
		sc.close();
	}

}
